#include <arpa/inet.h>
#include <errno.h>
#include <netinet/in.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>
#include <wait.h>

#define MAXBUF 1500
#define SERV_PORT 9877

int main() {
    char buffer[MAXBUF];
    int nready, client[FD_SETSIZE];
    int i, maxi, maxfd, listenfd, connfd, sockfd;
    ssize_t n;
    fd_set rset, allset;
    socklen_t addrlen, peerlen;
    struct sockaddr_in servaddr, cliaddr, peeraddr;

    listenfd = socket(AF_INET, SOCK_STREAM, 0);

    memset(&servaddr, 0, sizeof(servaddr));
    servaddr.sin_family = AF_INET;
    servaddr.sin_addr.s_addr = INADDR_ANY;
    servaddr.sin_port = htons(SERV_PORT);

    bind(listenfd, (struct sockaddr *)&servaddr, sizeof(servaddr));

    listen(listenfd, 5);

    maxfd = listenfd;
    maxi = -1;
    for (i = 0; i < FD_SETSIZE; i++) {
        client[i] = -1;
    }
    FD_ZERO(&allset);
    FD_SET(listenfd, &allset);

    for (;;) {
        rset = allset;
        nready = select(maxfd + 1, &rset, NULL, NULL, NULL);

        if (FD_ISSET(listenfd, &rset)) {
            addrlen = sizeof(cliaddr);
            connfd = accept(listenfd, (struct sockaddr *)&cliaddr, &addrlen);
            printf("client [%s:%d] connected\n", inet_ntoa(cliaddr.sin_addr),
                   ntohs(((struct sockaddr_in *)&cliaddr)->sin_port));
            for (i = 0; i < FD_SETSIZE; i++) {  // save descriptor
                if (client[i] < 0) {
                    client[i] = connfd;
                    break;
                }
            }

            if (i == FD_SETSIZE) {
                perror("too many clients.");
                exit(1);
            }

            FD_SET(connfd, &allset);  // add new descriptor to set
            if (connfd > maxfd) {
                maxfd = connfd;  // for select
            }
            if (i > maxi) {
                maxi = i;  // max index in client[] array
            }

            if (--nready <= 0) {  // no more readable descriptors
                continue;
            }
        }

        for (i = 0; i <= maxi; i++) {  // check all clients for data
            if ((sockfd = client[i]) < 0) {
                continue;
            }
            if (FD_ISSET(sockfd, &rset)) {
                peerlen = sizeof(struct sockaddr_in);
                getpeername(sockfd, (struct sockaddr *)&peeraddr, &peerlen);
                if ((n = read(sockfd, buffer, MAXBUF)) == 0) {
                    // connection closed by client
                    printf("client [%s:%d] closed\n",
                           inet_ntoa(peeraddr.sin_addr),
                           ntohs(((struct sockaddr_in *)&peeraddr)->sin_port));

                    close(sockfd);
                    FD_CLR(sockfd, &allset);
                    client[i] = -1;
                } else {
                    printf("client [%s:%d] send %ld bytes -> %s\n",
                           inet_ntoa(peeraddr.sin_addr),
                           ntohs(((struct sockaddr_in *)&peeraddr)->sin_port),
                           n, buffer);
                    write(sockfd, buffer, n);
                }
            }
            if (--nready <= 0) {
                break;
            }
        }
    }
    return 0;
}