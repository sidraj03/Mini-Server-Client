package mini_clienserver;

import java.net.Socket;
import java.util.StringTokenizer;
import java.io.*;

public class ServerConnection implements Runnable {

	Socket socket;
	Server server;
	DataInputStream  dinput;
	DataOutputStream doutput;
	String name;
	boolean shouldRun=true;
 
 public ServerConnection(Socket socket,String name,DataInputStream dinput,DataOutputStream doutput) {

	 this.socket=socket;
	 this.name=name;
	 this.dinput=dinput;
	 this.doutput=doutput;
	 
	 System.out.println(name);
 }
 
 public void run() {
	 
	     try {
	     while(true) {     
	    
	    	 String input=dinput.readUTF();
	    
	        if(input.equals("logout")) {
	       //we need to exit
	    	 shouldRun=false;
	    	 socket.close();
	    	 break;
	         }
	     
	     //break string into message and recipient part
	     StringTokenizer st=new StringTokenizer(input,"#");
	     
	     String message=st.nextToken();
	     String client=st.nextToken();
	     
	     for(ServerConnection sc:server.connections) {
			  if(sc.name.equals(client) && shouldRun==true) {
				  try {
					sc.doutput.writeUTF(this.name+" : "+message);
					sc.doutput.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				  
			  }
		  }
	     
	     }
	     this.dinput.close();
	     this.doutput.close();	     
	     }
	     
	     catch (IOException e) {
				e.printStackTrace();
			}
		     
 }

 
}
