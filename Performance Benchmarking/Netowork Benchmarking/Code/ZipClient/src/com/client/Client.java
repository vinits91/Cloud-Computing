package com.client;

import java.util.Arrays;
import java.util.List;

public class Client {

	static List<Integer> bufferSize = Arrays.asList(1, 1024, 65500);
	static NetworkClientTcp networkClient;
	static int i = 1;
	
	
	//based on confuguration initialtes the TCP or UDP operations with different buffer size as specified.
	public static void main(String args[]) {
		PropertiesController propertiesController = new PropertiesController();
		if (propertiesController.getProperty("tcporudp").equals("tcp")) {
			if (propertiesController.getProperty("numberofthreads").equals("1")) {
				System.out.println("threads");
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(0) + "Bytes", bufferSize.get(0));
				Thread thread1 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(1) + "Bytes", bufferSize.get(1));
				Thread thread2 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(2) + "Bytes", bufferSize.get(2));
				Thread thread3 = new Thread(networkClient);
				thread1.start();
				thread2.start();
				thread3.start();
			} else if (propertiesController.getProperty("numberofthreads").equals("2")) {
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(0) + "Bytes", bufferSize.get(0));
				Thread thread1 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(1) + "Bytes", bufferSize.get(1));
				Thread thread2 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(2) + "Bytes", bufferSize.get(2));
				Thread thread3 = new Thread(networkClient);
				i++;
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(0) + "Bytes", bufferSize.get(0));
				Thread thread4 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(1) + "Bytes", bufferSize.get(1));
				Thread thread5 = new Thread(networkClient);
				networkClient = new NetworkClientTcp(i + "Thread" + bufferSize.get(2) + "Bytes", bufferSize.get(2));
				Thread thread6 = new Thread(networkClient);
				thread1.start();
				thread2.start();
				thread3.start();
				thread4.start();
				thread5.start();
				thread6.start();
			}
		} else if (propertiesController.getProperty("tcporudp").equals("udp")) {
			int size = Integer.parseInt(propertiesController.getProperty("sizeudp"));
			if (propertiesController.getProperty("numberofthreads").equals("1")) {
				UdpClient udpClient = new UdpClient(i + "Thread" + size + "Bytes", size);
				Thread thread = new Thread(udpClient);
				thread.start();
			}
			if (propertiesController.getProperty("numberofthreads").equals("2")) {
				UdpClient udpClient = new UdpClient(i + "Thread" + size + "Bytes", size);
				Thread thread1 = new Thread(udpClient);
				i++;
				udpClient = new UdpClient(i + "Thread" + size + "Bytes", size);
				Thread thread2 = new Thread(udpClient);
				thread1.start();
				thread2.start();
			}
		}
	}
}
