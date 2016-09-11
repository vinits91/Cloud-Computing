#include<pthread.h>
#include<stdio.h>
#include<time.h>
void iops();
void flops();
void computeIntegerIops(int counter);
void computeFloatingFlops(int counter);
void FopsPerSecond();
void secondSamplesFloat();
void * FlopsPerSec(void *counter);
void IopsPerSecond();
void secondSamplesInt();
void * samplesPerSecInt(void *counter);
void reset();
unsigned long diff;
clock_t s, e;
FILE *fPointer;
float cal;  
long i;
pthread_t thread1,thread2,thread3,thread4;
pthread_t threadFlop[4];
pthread_t threadIop[4];
long flopOperations[4];
long IopsOperations[4];

int main()
{
	int counter=0;
	fPointer= fopen( "cpuLog.txt", "ab" );
	printf("\nComputing GFLOPS : Check cpu.txt file for GFLOPS\n");
	computeFloatingFlops(counter);
	counter=0;
	printf("\nComputing GIOPS : Check cpu.txt file for GIOPS\n");
	computeIntegerIops(counter);
	printf("\n Floating Operation done by 4 threads in 10min : Check FlopsSamples.txt file for GFLOPS\n");
	FopsPerSecond();
	printf("\nInteger Operation done by 4 threads in 10min : Check FlopsSamples.txt file for GIOPS\n");
	IopsPerSecond();
	return 0;
}

//Computes data for 1sec samples for 10minutes for integer operations
void IopsPerSecond(){
	
	FILE *IopsPointer= fopen( "IopsSamples.txt", "ab" );
	int totalTime=0;
	clock_t s,e;
	double usedTime;
	long numberOfOperations;
	s=clock();
	secondSamplesInt();
	//checks the timer value till it reaches 600 sec.
	do{
		e= clock();
		usedTime = ((double) (e - s)) / CLOCKS_PER_SEC;
		if(usedTime >= 1){
				numberOfOperations=IopsOperations[0]+IopsOperations[1]+IopsOperations[2]+IopsOperations[3];
				fprintf(IopsPointer,"%ld\n", numberOfOperations);
				reset();
				totalTime++;
				s= clock();
		}

	}while(totalTime<600);
	fclose(IopsPointer);
	
}

//creates threads for sampling for 10min
void secondSamplesInt(){
	int i=0;
	for(i=0;i<=3;i++){
		pthread_create(&threadIop[i], NULL, samplesPerSecInt, &i);
	}
}

//integer operations computation
void * samplesPerSecInt(void *counter){
	int c= *(int *)counter;
	int t;
	while(1){
		74+3474;
		475+647;
		36+147;
		432+421;
		147+147;
		747+456;
		245+978;
		745+365;
		147+748;
		745+165;
		147+749;
		478-654;
		657-724;
		748+47;
		47-974;
		147-78;
		47-974;
		147-78;
		IopsOperations[c]=IopsOperations[c]+18;
	}
	
}

//floating point samples computation for 10min
void FopsPerSecond(){
	
	FILE *flopsPointer= fopen( "FlopsSamples.txt", "ab" );
	int totalTime=0;
	clock_t s,e;
	double usedTime;
	long numberOfOperations;
	s=clock();
	secondSamplesFloat();
	//takes the sample at 1sec till 10min
	do{
		e= clock();
		usedTime = ((double) (e - s)) / CLOCKS_PER_SEC;
		if(usedTime >= 1){
				numberOfOperations=flopOperations[0]+flopOperations[1]+flopOperations[2]+flopOperations[3];
				fprintf(flopsPointer,"%ld\n", numberOfOperations);
				reset();
				totalTime++;
				s= clock();
		}

	}while(totalTime<600);
	fclose(flopsPointer);
	
		
}

void reset(){
	flopOperations[0]=flopOperations[1]=flopOperations[2]=flopOperations[3]=0;
	IopsOperations[0]=IopsOperations[1]=IopsOperations[2]=IopsOperations[3]=0;
	
}

//floating point operation computation
void * FlopsPerSec(void *counter){
	int c= *(int *)counter;
	long t;
	while(1){
		174.1+148.14;
		3.254-47.78;
		47.14+76.34;
		1.74-68.65;
		4.98+7.78;
		3.754-98.67;
		4.85+36.84;
		3.4+5.4;
		2.2+5.6;
		3.8+7.23;
		78.12+4.78;
		4.47+7.58;
		47.14+14.89;
		7.47+149.25;
		74.43-743.78;
		36.45-47.647;
		75.47-21.4;
		47.47-367.57;
		4.784-7.456;
		36.45-47.647;
		75.47-21.4;
		47.47-367.57;
		4.784-7.456;
		flopOperations[c]=flopOperations[c]+22;
	}
	
}
//creates threads for floating point sampling operations
void secondSamplesFloat(){
	int i=0;
	for(i=0;i<=3;i++){
		pthread_create(&threadFlop[i], NULL, FlopsPerSec, &i);
	}
}
//floating point
void computeFloatingFlops(int counter){
	//1 thread cpu
		if(counter==0){ 
		pthread_create(&thread1,NULL,(void *)flops,NULL);
		pthread_join(thread1,NULL);
		counter++;
		fprintf(fPointer,"\n1 Thread GFLOPS: %f",(cal/1000000000));
		}
//2 thread cpu
	if(counter==1){

		pthread_create(&thread1,NULL,(void *)flops,NULL);
		pthread_create(&thread2,NULL,(void *)flops,NULL);
		pthread_join(thread1,NULL);
		pthread_join(thread2,NULL);
		counter++;
		fprintf(fPointer, "\n2 Thread GFLOPS: %f",(2*cal/1000000000));
	}
//4 thread cpu
	if(counter==2){

		pthread_create(&thread1,NULL,(void *)flops,NULL);
		pthread_create(&thread2,NULL,(void *)flops,NULL);
		pthread_create(&thread3,NULL,(void *)flops,NULL);
		pthread_create(&thread4,NULL,(void *)flops,NULL);
		pthread_join(thread1,NULL);
		pthread_join(thread2,NULL);
		pthread_join(thread3,NULL);
		pthread_join(thread4,NULL);
		fprintf(fPointer, "\n4 Thread GFLOPS : %f",4*(cal/1000000000));
	}
}
//integer operations
void computeIntegerIops(int counter){
	////1 thread cpu
	if(counter==0){ 
		pthread_create(&thread1,NULL,(void *)iops,NULL);
		pthread_join(thread1,NULL);
		counter++;
		fprintf(fPointer,"\n1 Thread GIOPS %f",(cal/1000000000));
	}
//2 thread cpu
		if(counter==1){

			pthread_create(&thread1,NULL,(void *)iops,NULL);
			pthread_create(&thread2,NULL,(void *)iops,NULL);
			pthread_join(thread1,NULL);
			pthread_join(thread2,NULL);
			counter++;
			fprintf(fPointer, "\n2 Thread GIOPS: %f",(2*cal/1000000000));
		}

		//4 thread cpu
		if(counter==2){

			pthread_create(&thread1,NULL,(void *)iops,NULL);
			pthread_create(&thread2,NULL,(void *)iops,NULL);
			pthread_create(&thread3,NULL,(void *)iops,NULL);
			pthread_create(&thread4,NULL,(void *)iops,NULL);
			pthread_join(thread1,NULL);
			pthread_join(thread2,NULL);
			pthread_join(thread3,NULL);
			pthread_join(thread4,NULL);
			fprintf(fPointer, "\n4 Thread GIOPS: %f",4*(cal/1000000000));
		}
	
}


void iops(){
	long i=0;
	s = clock();
	  for(i=0;i<1000000000;i++){
		74+3474;
		475+647;
		36+147;
		432+421;
		147+147;
		747+456;
		245+978;
		745+365;
		147+748;
		745+165;
		147+749;
		478-654;
		657-724;
		748+47;
		47-974;
		147-78;
		47-974;
		147-78;
		}
	 e=clock();
	diff = (long)((e - s))/ CLOCKS_PER_SEC;
	cal=(18*1000000000ul)/((diff));
}



void flops()
{
  
long i=0;
s = clock();
for(i=0;i<1000000000;i++){
		174.1+148.14;
		3.254-47.78;
		47.14+76.34;
		1.74-68.65;
		4.98+7.78;
		3.754-98.67;
		4.85+36.84;
		3.4+5.4;
		2.2+5.6;
		3.8+7.23;
		78.12+4.78;
		4.47+7.58;
		47.14+14.89;
		7.47+149.25;
		74.43-743.78;
		47.47-367.57;
		4.784-7.456;
}
e=clock();
diff = (long)(e - s) / CLOCKS_PER_SEC;
cal=(16*1000000000ul)/((diff));
}


