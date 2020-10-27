#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define NUM_THREADS 3

struct thread_data {
    int task_id;
    char *name;
};

struct thread_data thread_data_array[NUM_THREADS];

void *print_hello(void *threadarg) {
    struct thread_data *my_data;
    long taskid;
    char *name;
    my_data = (struct thread_data *)threadarg;
    taskid = my_data->task_id;
    name = my_data->name;
    printf("Hello World! It's me, thread #%ld, my name is %s!\n", taskid, name);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    pthread_t threads[NUM_THREADS];
    char *thread_names[NUM_THREADS];
    thread_names[0] = "Alice";
    thread_names[1] = "Bob";
    thread_names[2] = "Tom";

    int rc;
    long t;

    for (t = 0; t < NUM_THREADS; t++) {
        thread_data_array[t].task_id = t;
        thread_data_array[t].name = thread_names[t];
        printf("In main: creating thread #%ld\n", t);
        rc = pthread_create(&threads[t], NULL, print_hello,
                            (void *)&thread_data_array[t]);
        if (rc) {
            printf("ERROR; return code from pthread_create() is %d\n", rc);
            exit(-1);
        }
    }

    /* Last thing that main() should do */
    pthread_exit(NULL);
}