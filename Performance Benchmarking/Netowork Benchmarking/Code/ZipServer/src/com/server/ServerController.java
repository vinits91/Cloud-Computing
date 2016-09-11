package com.server;

import java.util.Arrays;
import java.util.List;

public class ServerController {

	static PropertiesController propertiesController = new PropertiesController();
	static List<Integer> bufferSize = Arrays.asList(1, 1024, 1048576);

	//main controller which regulates the flow of control for disk and Network
	public static void main(String args[]) {
		int eval = Integer.parseInt(propertiesController.getProperty("evaluation"));
		switch (eval) {
		case 1:
			CpuEvaluation cpuEvaluation = new CpuEvaluation();
			cpuEvaluation.runCpuEvaluation();
			break;
		case 2:
			DiskEvalution diskEvalution = new DiskEvalution(bufferSize.get(0));
			diskEvalution.runDiskEvaluation();
			diskEvalution = new DiskEvalution(bufferSize.get(1));
			diskEvalution.runDiskEvaluation();
			diskEvalution = new DiskEvalution(bufferSize.get(2));
			diskEvalution.runDiskEvaluation();

			break;
		case 3:
			if (propertiesController.getProperty("tcporudp").equals("tcp")) {
				TcpServer tcpServer = new TcpServer();
				tcpServer.runTcpServer();
			} else if (propertiesController.getProperty("tcporudp").equals("udp")) {
				UdpServer udpServer = new UdpServer();
				udpServer.runUdpServer();
			}
			break;
		case 4:
			CpuConcurrentThreads cpuConcurrentThreads = new CpuConcurrentThreads();
			cpuConcurrentThreads.runCpuConcurrent();
			break;
		}
	}
}
