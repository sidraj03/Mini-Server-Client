package mini_clienserver;

import java.io.*;
import java.net.*;

public class Server {

	//define server socket
	ServerSocket ss;
	Socket s;
	DataInputStream dinput;
	DataOutputStream doutput;
	
	public static void main(String[]args) {
		new Server();
	}
	
		public Server() {
	     try {
	    	 
			ss=new ServerSocket(8080);
			s=ss.accept();
			dinput=new DataInputStream(s.getInputStream());
			doutput=new DataOutputStream(s.getOutputStream());
			
			listenForData();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	     
	}
		
	public void listenForData() {
		
		while(true) {
			try {
				
				while(dinput.available()==0) {
				
				try {
					Thread.sleep(1);
				}
				catch(Exception ex) {
				ex.printStackTrace();
				}
			   }
				
				String dataIn=dinput.readUTF();
				doutput.writeUTF(dataIn);
			}
			catch(Exception ex) {
				ex.printStackTrace();
				break;
			}
		}
		
		try {
			dinput.close();
			doutput.close();
			ss.close();
			s.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	

}
