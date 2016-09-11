package com.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import com.logging.ServerLog;

public class TcpNetworkServer implements Runnable {

	Socket server = null;
	BufferedReader bufferedReader = null;
	String input;
	DataOutputStream dataOutputStream;
	List<Long> acceptTime = new ArrayList<Long>();

	public TcpNetworkServer(Socket server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(server.getInputStream());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(server.getOutputStream());
			while (true) {
				byte[] b = (byte[]) objectInputStream.readObject();
				objectOutputStream.writeObject(b);
				b = null;
			}

		} catch (IOException | ClassNotFoundException e) {
			ServerLog.error("Exception while Reading data from client in server.: IOException" + "\n" + e.getMessage());
		}

	}

}
