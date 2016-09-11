package com.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Properties;

import com.logging.ServerLog;

public class UdpServer {
	static int portNumber;
	static int count;
	PropertiesController propertiesController = new PropertiesController();
	DatagramSocket datagramSocket;

	//handles the UDP request from client and sends the same size of datagram packet back to the client.
	public void runUdpServer() {

		try {
			portNumber = Integer.parseInt(propertiesController.getProperty("port"));
			datagramSocket = new DatagramSocket(portNumber);
			byte[] recievedPacket = new byte[Integer.parseInt(propertiesController.getProperty("sizeudp"))];
			DatagramPacket datagramPacket = new DatagramPacket(recievedPacket, recievedPacket.length);
			while (true) {
				System.out.println("UDP Sevrver is Waiting...");
				datagramSocket.receive(datagramPacket);
				byte[] bs = datagramPacket.getData();
				System.out.println(bs.length);
				datagramSocket
						.send(new DatagramPacket(bs, bs.length, datagramPacket.getAddress(), datagramPacket.getPort()));
			}

		} catch (SocketException e) {
			ServerLog.error("Exception in UdpServer :: runUdpServer method " + e.getMessage());

		} catch (IOException e) {
			ServerLog.error("Exception in UdpServer :: runUdpServer method " + e.getMessage());
		} finally {
			datagramSocket.close();
		}

	}

}
