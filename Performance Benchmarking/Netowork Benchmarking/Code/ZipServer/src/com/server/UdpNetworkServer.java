package com.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpNetworkServer implements Runnable {
	DatagramPacket datagramPacket = null;
	DatagramSocket datagramSocket = null;
	PropertiesController controller = new PropertiesController();

	public UdpNetworkServer(DatagramPacket datagramPacket, DatagramSocket datagramSocket) {
		this.datagramPacket = datagramPacket;
		this.datagramSocket = datagramSocket;
	}

	@Override
	public void run() {

		
	}

}
