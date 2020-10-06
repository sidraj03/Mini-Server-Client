package mini_clienserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {

	//define server socket
	ServerSocket ss;
	Socket s;
	boolean shouldRun=true;
	
	static int i=0;
	
	static ArrayList<ServerConnection> connections=new ArrayList<>();
	public static void main(String[]args) {
		new Server();
	}
		
	public Server() {
	     try {
	    	ss=new ServerSocket(8080);
	    	
	    	while(true) {
				s=ss.accept();
				
				//get input and output streams
				DataInputStream	dinput=new DataInputStream(s.getInputStream());
				DataOutputStream doutput=new DataOutputStream(s.getOutputStream());
		    	
				System.out.println("New client request received :"+s);
				
				//to handle multiple requests at a given time we create different threads to  deal with them
				ServerConnection sc=new ServerConnection(s,"client "+i,dinput,doutput);
				System.out.println("Adding this client to active list");
				
				connections.add(sc);
				
				Thread t1=new Thread(sc);
				t1.start();
				
				//for new client
				i++;
	    	}
	    	
	    	
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	}
}
		
	