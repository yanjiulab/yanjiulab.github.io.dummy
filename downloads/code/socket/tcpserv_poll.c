#include <arpa/inet.h>
#include <errno.h>
#include <poll.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>

#define MAXBUF 1500
#define SERV_PORT 9877
#define OPEN_MAX 256

void error(char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char **argv) {
    int i, maxi, listenfd, connfd, sockfd;
    int nready;
    ssize_t n;
    char buf[MAXBUF];
    socklen_t clilen;
    struct pollfd client[OPEN_MAX];
    struct sockaddr_in cliaddr, servaddr;

    listenfd = socket(AF_INET, SOCK_STREAM, 0);

    bzero(&servaddr, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
    servaddr.sin_port = htons(SERV_PORT);

    bind(listenfd, (struct sockaddr *)&servaddr, sizeof(servaddr));
    listen(listenfd, 5);

    client[0].fd = listenfd;
    client[0].events = POLLRDNORM;
    for (i = 1; i < OPEN_MAX; i++) {
        client[i].fd = -1; /* -1 indicates available entry */
    }
    maxi = 0; /* max index into client[] array */

    for (;;) {
        nready = poll(client, maxi + 1, -1);

        if (client[0].revents & POLLRDNORM) { /* new client connection */
            clilen = sizeof(cliaddr);
            connfd = accept(listenfd, (struct sockaddr *)&cliaddr, &clilen);
            printf("client [%s:%d] connected\n", inet_ntoa(cliaddr.sin_addr),
                   ntohs(((struct sockaddr_in *)&cliaddr)->sin_port));

            for (i = 1; i < OPEN_MAX; i++)
                if (client[i].fd < 0) {
                    client[i].fd = connfd; /* save descriptor */
                    break;
                }
            if (i == OPEN_MAX) error("too many clients");

            client[i].events = POLLRDNORM;
            if (i > maxi) maxi = i; /* max index in client[] array */

            if (--nready <= 0) continue; /* no more readable descriptors */
        }

        for (i = 1; i <= maxi; i++) { /* check all clients for data */
            if ((sockfd = client[i].fd) < 0) continue;
            if (client[i].revents & (POLLRDNORM | POLLERR)) {
                if ((n = read(sockfd, buf, MAXBUF)) < 0) {
                    if (errno == ECONNRESET) { /*4connection reset by client */
                        printf("client[%d] aborted connection\n", i);
                        close(sockfd);
                        client[i].fd = -1;
                    } else {
                        error("read error");
                    }

                } else if (n == 0) { /*4connection closed by client */
                    printf("client[%d] closed connection\n", i);
                    close(sockfd);
                    client[i].fd = -1;
                } else {
                    write(sockfd, buf, n);
                }
                if (--nready <= 0) break; /* no more readable descriptors */
            }
        }
    }
}
