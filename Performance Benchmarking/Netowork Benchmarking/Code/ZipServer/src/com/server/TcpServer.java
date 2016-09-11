package com.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import com.logging.ServerLog;

public class TcpServer {

	int portNumber;
	ServerSocket serverSocket = null;
	Socket server = null;
	PropertiesController propertiesController = new PropertiesController();

	//accepts the connection from client and creates the server thread to handle the request
	public void runTcpServer() {
		try {

			portNumber = Integer.parseInt(propertiesController.getProperty("port"));
			serverSocket = new ServerSocket(portNumber);
			while (true) {
				System.out.println("awaiting client...");
				server = serverSocket.accept();
				System.out.println(server.getLocalAddress());
				TcpNetworkServer networkServer = new TcpNetworkServer(server);
				Thread thread = new Thread(networkServer);
				thread.start();
			}
		} catch (IOException e) {
			ServerLog.error("Error While Creating the Server Socket. : IOException" + "\n" + e.getMessage());
		} finally {
			try {
				server.close();
				serverSocket.close();
			} catch (IOException e) {
				ServerLog.error("Error While Closing the  Server Socket. : IOException" + "\n" + e.getMessage());
			}

		}
	}

}
