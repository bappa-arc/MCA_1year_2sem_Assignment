#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

sem_t resource;
sem_t readTry;
pthread_mutex_t rmutex;

int readcount = 0;

void *reader(void *arg){
    int id = *(int *)arg;

    sem_wait(&readTry);
    pthread_mutex_lock(&rmutex);
    readcount++;
    
    if(readcount == 1)
        sem_wait(&resource);

    pthread_mutex_unlock(&rmutex);
    sem_post(&readTry);

    printf("Reader %d is reading\n", id);
    sleep(1);
    pthread_mutex_lock(&rmutex);
    readcount--;

    if(readcount == 0)
        sem_post(&resource);
    pthread_mutex_unlock(&rmutex);
    printf("Reader %d finished reading\n",id);
    pthread_exit(NULL);
}

void *writer(void *arg){
    int id = *(int *)arg;

    sem_wait(&readTry);
    sem_wait(&resource);

    printf("Writer %d is writing\n",id);
    sleep(2);
    printf("Writer %d finished writing\n", id);

    sem_post(&resource);
    sem_post(&readTry);

    pthread_exit(NULL);
}

int main(){
    pthread_t r[5], w[1];
    int rid[5] = {1,2,3,4,5};
    int wid[1] = {1};

    sem_init(&resource, 0, 1);
    sem_init(&readTry, 0, 1);
    pthread_mutex_init(&rmutex, NULL);

    for(int i=0; i<5; i++){
        pthread_create(&r[i], NULL, reader, &rid[i]);
    }
    pthread_create(&w[0], NULL, writer, &wid[0]);

    for(int i=0; i<5; i++){
        pthread_join(r[i], NULL);
    }
    pthread_join(w[0], NULL);

    sem_destroy(&resource);
    sem_destroy(&readTry);

    pthread_mutex_destroy(&rmutex);
    return 0;
}