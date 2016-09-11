cd /
sudo apt-get install mdadm
sudo mdadm --create --verbose /dev/md0 --level=0 --raid-devices=3  /dev/xvdb /dev/xvdc /dev/xvdd
sudo mkfs.ext4 /dev/md0
sudo mke2fs -F  -t  ext4 /dev/md0
sudo mkdir /vinit
sudo mount /dev/md0  /vinit
sudo chmod 777 /vinit
cd /vinit


