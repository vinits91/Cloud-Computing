package com.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//Invokes Threaded TCP client
public class NetworkClientTcp implements Runnable {
	Socket clientSocket = null;
	int size;
	String fileName;
	PropertiesController propertiesController = new PropertiesController();

	public NetworkClientTcp(String fileName, int size) {
		this.size = size;
		this.fileName = fileName;
	}

	@Override
	public void run() {
		try {
			//checks for TCP and creates the TCP connection with server on specified port and address, also sends data as a stream of bytes
			if (propertiesController.getProperty("tcporudp").equals("tcp")) {
				clientSocket = new Socket(propertiesController.getProperty("ipaddress"),
						Integer.parseInt(propertiesController.getProperty("port")));
				byte[] bs = new byte[size];
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
				Arrays.fill(bs, (byte) 1);
				long roundTrip, d, s;
				try {
					double diff;
					for (int i = 0; i < 1000; i++) {
						s = System.nanoTime();
						objectOutputStream.writeObject(bs);
						bs = (byte[]) objectInputStream.readObject();
						d = System.nanoTime();
						roundTrip = (d - s) / 2;
						diff = roundTrip / 1e6;
						writeLog("Tcp_" + fileName, diff);
					}

				} catch (Exception exception) {
					exception.printStackTrace();
					ClientLog.error("Exception : NetworkClient " + exception.getMessage());
				}

			}

		} catch (

		UnknownHostException e)

		{
			ClientLog.error("Error While Creating Client Socket : UnknownHostException" + "\n" + e.getMessage());
		} catch (

		IOException e)

		{
			e.printStackTrace();
			ClientLog.error("Error While Creating Client Socket : IOException" + "\n" + e.getMessage());
		}

	}

	public void writeLog(String fileName, double diff) {
		ClientLog.infoLog(fileName, diff);

	}

}
