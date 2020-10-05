package mini_clienserver;

import java.net.Socket;
import java.io.*;

public class ServerConnection extends Thread {

	Socket socket;
	Server server;
	DataInputStream  dinput;
	DataOutputStream doutput;
	boolean shouldRun=true;
 
 public ServerConnection(Socket socket,Server server) {
	 super("ServerconThread");
	 this.socket=socket;
	 this.server=server;
 }
 
 public void sendStringToClient(String input) {
 	 try {
		doutput.writeUTF(input);
		doutput.flush();
	} catch (IOException e) {
		e.printStackTrace();
	}
 }
 
 public void sendStringToAllClients(String input) {
	  for(int index=0;index<server.connections.size();index++) {
		  ServerConnection sc=server.connections.get(index);
		  sc.sendStringToClient(input);
	  }
 }

 public void run() {
	 
	     try {
			dinput=new DataInputStream(socket.getInputStream());
			 doutput=new DataOutputStream(socket.getOutputStream());
	     
	     while(shouldRun) {
	    	 while(dinput.available()==0) {
	    		 try {
	    			 Thread.sleep(0);
	    		 }
	    		 catch(Exception ex) {
	    			 ex.printStackTrace();
	    		 }
	    		 
	    	 }
	     
	     String input=dinput.readUTF();
	     sendStringToAllClients(input);
	     }
	     dinput.close();
	     doutput.close();
	     socket.close();
	     
	     }
	     
	     catch (IOException e) {
				e.printStackTrace();
			}
		     
 }

 
}
