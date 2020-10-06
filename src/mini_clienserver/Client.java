package mini_clienserver;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client{
	
	Socket s;
	DataInputStream dinput;
	DataOutputStream doutput;
	Scanner con=new Scanner(System.in);
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
		
		Scanner con=new Scanner(System.in);
		//lambda for creation of thread
		//for client and console interaction
		Thread sendMes=new Thread(new Runnable(){
			
		public void run() {
			while(true) {	
			String input=con.nextLine();
			if(input.toLowerCase().equals("Logout")) {
				break;
			}
			
			try {
				doutput.writeUTF(input);
				doutput.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			}	
			}
		});
			
		Thread recieve=new Thread(new Runnable() {

		public void run() {
			//check for reply from server
		while(true) {
		try {	
			//display the reply on console
			String reply=dinput.readUTF();
			System.out.println(reply);
		}
		
		catch (IOException e) {
			e.printStackTrace();
			break;
		}
		
		}
		}
		
		});

		sendMes.start();
		recieve.start();		
}
	
}

