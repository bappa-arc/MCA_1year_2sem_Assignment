#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

sem_t resource;
pthread_mutex_t mutex;

int readCount = 0;

void *reader(void *arg)
{
    int id = *(int *)arg;

    pthread_mutex_lock(&mutex);

    readCount++;
    if(readCount == 1)
        sem_wait(&resource);

    pthread_mutex_unlock(&mutex);

    printf("Reader %d is reading\n", id);
    sleep(1);
    printf("Reader %d finished reading\n", id);

    pthread_mutex_lock(&mutex);

    readCount--;
    if(readCount == 0)
        sem_post(&resource);

    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

void *writer(void *arg)
{
    int id = *(int *)arg;

    sem_wait(&resource);

    printf("Writer %d is writing\n", id);
    sleep(2);
    printf("Writer %d finished writing\n", id);

    sem_post(&resource);

    pthread_exit(NULL);
}

int main()
{
    pthread_t r[3], w[2];

    int rid[3]={1,2,3};
    int wid[2]={1,2};

    sem_init(&resource,0,1);
    pthread_mutex_init(&mutex,NULL);

    pthread_create(&r[0],NULL,reader,&rid[0]);
    sleep(1);

    pthread_create(&r[1],NULL,reader,&rid[1]);
    sleep(1);

    pthread_create(&w[0],NULL,writer,&wid[0]);
    sleep(1);

    pthread_create(&r[2],NULL,reader,&rid[2]);
    sleep(1);

    pthread_create(&w[1],NULL,writer,&wid[1]);

    for(int i=0;i<3;i++)
        pthread_join(r[i],NULL);

    for(int i=0;i<2;i++)
        pthread_join(w[i],NULL);

    sem_destroy(&resource);
    pthread_mutex_destroy(&mutex);

    return 0;
}