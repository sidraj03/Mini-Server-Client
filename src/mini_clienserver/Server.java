package mini_clienserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

	//define server socket
	ServerSocket ss;
	Socket s;
	DataInputStream dinput;
	DataOutputStream doutput;
	boolean shouldRun=true;
	
	ArrayList<ServerConnection> connections=new ArrayList();
	public static void main(String[]args) {
		new Server();
	}
	
		public Server() {
	     try {
	    	ss=new ServerSocket(8080);
	    	while(shouldRun) {
				s=ss.accept();
				ServerConnection sc=new ServerConnection(s,this);
				sc.start();
				connections.add(sc);
	    	}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	}
}
		
	