package com.client;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//invokes UDP client
public class UdpClient implements Runnable {
	int size;
	String fileName;
	PropertiesController propertiesController = new PropertiesController();

	public UdpClient(String fileName, int size) {
		this.size = size;
		this.fileName = fileName;
	}

	//sends the data to the server based on the address and port of server by embedding it into data gram packet.
	@Override
	public void run() {
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			byte[] bs = new byte[size];
			byte[] bs1 = new byte[size];
			Arrays.fill(bs, (byte) 'c');
			long s, d, roundTrip;
			double diff;
			InetAddress address = InetAddress.getByName(propertiesController.getProperty("ipaddress"));
			for (int i = 0; i < 25000; i++) {
				DatagramPacket datagramPacket = new DatagramPacket(bs, bs.length, address,
						Integer.parseInt(propertiesController.getProperty("port")));
				DatagramPacket datagramPacket2 = new DatagramPacket(bs1, bs1.length);
				s = System.nanoTime();
				datagramSocket.send(datagramPacket);
				datagramSocket.receive(datagramPacket2);
				d = System.nanoTime();
				roundTrip = (d - s) / 2;
				diff = roundTrip / 1e6;
				writeLog("Udp_" + fileName, diff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void writeLog(String fileName, double diff) {
		ClientLog.infoLog(fileName, diff);

	}

}
