#Download the sparks latest Distribution which comes with Hadoop

#Navigate to spark/ec2 folder
cd spark/ec2
export AWS_ACCESS_KEY_ID=your AccessKey
export AWS_SECRET_ACCESS_KEY=your Secretkey

#configure spark to run on Ec2
./spark-ec2 -k "name of your pem file" -i "location of your pem file" -s 1 -t d2.xlarge --spot-price=0.10 launch Spark-master

./spark-ec2 -k "name of your pem file" -i "location of your pem file" login Spark-master

# Mount a Disk



cd /
sudo su
mke2fs -F -t ext3 /dev/xvdc
mkdir /vinit
mount /dev/xvdc /vinit
exit
sudo chmod 777 vinit

#Navigate to Spark Configuration folder in EC2 spark cluster

cd spark/conf/

#move the gensort script to Ec2 using filezilla
#generate dataset using gensort
#navigate to/64 directory and generate data 

cd /root/
chmod +x install-gensort.sh
./install-gensort.sh
cd /root/64
./gensort -a 10000000 inputfile.txt


#put file into Hdfs
cd /root/ephemeral-hdfs/bin/
./hadoop fs -put /root/inputfile.txt /root/ephemeral-hdfs/bin/inputfile.txt

# open the spark shell
#transfer the sort.scala to root directory on ec2 the follow following
 cd /root/spark/bin/
./spark-shell -i /root/sort.scala 


