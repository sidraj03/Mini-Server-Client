package mini_clienserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	Socket s;
	DataInputStream dinput;
	DataOutputStream doutput;
	
	public static void main(String[]args) {
		new Client();
	}
	
	public Client() {
		try {
		s=new Socket("localhost",8080);
		dinput=new DataInputStream(s.getInputStream());
		doutput=new DataOutputStream(s.getOutputStream());
		
		listenForInput();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public void listenForInput() {
		Scanner console=new Scanner(System.in);
		//for client and console interaction
		while(true) {
			//wait for input from console
			while(!console.hasNextLine()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		String input=console.nextLine();
		if(input.toLowerCase().equals("quit")) {
			break;
		}
		
		try {
			doutput.writeUTF(input);
			doutput.flush();
			//check for reply from server
			while(dinput.available()==0) {
			  
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}   	
			  
			}
			//display the reply on console
			String reply=dinput.readUTF();
			System.out.println(reply);
		}
		
		catch (IOException e) {
			e.printStackTrace();
			break;
		}
			
	}
		
		//closing connection and streams
		
		try {
			s.close();
			doutput.close();
			dinput.close();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
}
	
}

