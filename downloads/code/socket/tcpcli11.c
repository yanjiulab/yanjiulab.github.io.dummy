#include <arpa/inet.h>
#include <errno.h>
#include <netinet/in.h>
#include <netinet/tcp.h>
#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/select.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <unistd.h>

#define MAXBUF 256
#define SERV_PORT 9877
#define NUM_THREADS 50
#define NUM_MSGS 10

void error(char *msg) {
    perror(msg);
    exit(0);
}

// A normal C function that is executed as a thread
// when its name is specified in pthread_create()
void *thr_func(void *vargp) {
    char msg[MAXBUF];
    struct sockaddr_in addr = {0};
    int sockfd;
    int n;

    /* Create socket and connect to server */
    sockfd = socket(AF_INET, SOCK_STREAM, 0);
    addr.sin_family = AF_INET;
    addr.sin_port = htons(SERV_PORT);
    addr.sin_addr.s_addr = inet_addr("127.0.0.1");

    connect(sockfd, (struct sockaddr *)&addr, sizeof(addr));
    sprintf(msg, "Message from client %ld", (long)vargp);
    for (int i = 0; i < NUM_MSGS; i++) {
        /* write: send the message line to the server */
        n = write(sockfd, msg, strlen(msg));
        if (n < 0) error("ERROR writing to socket");

        /* read: print the server's reply */
        bzero(msg, MAXBUF);
        n = read(sockfd, msg, MAXBUF);
        if (n < 0) error("ERROR reading from socket");
        // printf("Echo from server: %s\n", msg);
    }
    close(sockfd);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];

    int rc;
    long t;
    struct timeval start, end;
    long secs_used, micros_used;

    gettimeofday(&start, NULL);
    for (t = 0; t < NUM_THREADS; t++) {
        printf("In main: creating thread #%ld\n", t);
        rc = pthread_create(&threads[t], NULL, thr_func, (void *)t);
        if (rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }

    /* Last thing that main() should do */
    for (t = 0; t < NUM_THREADS; t++) {
        pthread_join(threads[t], NULL);
    }
    gettimeofday(&end, NULL);
    secs_used =
        (end.tv_sec - start.tv_sec);  // avoid overflow by subtracting first
    micros_used = ((secs_used * 1000000) + end.tv_usec) - (start.tv_usec);

    printf("%d client send %d*%d messages: %ld micros used\n", NUM_THREADS,
           NUM_THREADS, NUM_MSGS, micros_used);
    pthread_exit(NULL);
}