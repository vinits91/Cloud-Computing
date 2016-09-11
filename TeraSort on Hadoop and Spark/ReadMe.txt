Test Bed Environment: Linux EC2, Amazon Web Service

Shared memory:

Execute the "Basic_Install.sh" to install basic requirement for shared memory

Execute "install-gensort.sh" to generate dataset of reuired size 

Transfer the source code to Instance with build.xml

now run following command:

ant run

Hadoop:

For Hadoop Configuration 1st install and execute "Install_Hadoop_Ec2.sh" script to have a necessary hadoop dependencies installed.
Now As mentioned in the Configuration steps file, follow the steps.

Necessary Files to be changed to set up a cluster is given in the "HadoopFiles Required to be changed" folder.

Now to execute the hadoop sort, transfer and execute the "Hadoop_Sort.jar".



Spark:

To install and run spark, first download the spark distribution with hadoop 2.6 into local,

then download the Accesss Key and Secret key from AWS for your account, and Follow the steps as mentioned in the 
sparkScripts.sh

Now to execute the spark sort, execute the sort.scala with the steps as mentioned in the sparkScripts.sh

 