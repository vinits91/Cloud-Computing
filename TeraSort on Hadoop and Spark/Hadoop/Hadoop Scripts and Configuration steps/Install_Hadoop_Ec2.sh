sudo apt-get  update
sudo apt-get install openjdk-7-jdk -y
sudo apt-get install ssh -y
eval "$(ssh-agent)"
wget archive.apache.org/dist/hadoop/common/hadoop-2.7.2/hadoop-2.7.2.tar.gz
tar -xzvf hadoop-2.7.2.tar.gz
chmod 400 cloud.pem
chmod 777 hadoop-2.7.2
ssh-add cloud.pem
