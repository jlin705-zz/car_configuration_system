package Socket;
import java.net.*;
import java.util.Iterator;
import java.io.*;

import Adapter.BuildAuto;
import Adapter.proxyAutomotive;
import Adapter.proxyAutomotive.SocketClient;
import Adapter.proxyAutomotive.SocketClientConstants;
import Exception.EmptyChoiceException;
import Exception.MissingBasicInfoExcepiton;
import Model.Automotive;
import Utility.Serialize;

public  class ClientClass extends Thread implements SocketClient,SocketClientConstants{
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock;
	private static String strHost="127.0.0.1";
	private static int iPort=4242;
	public void run(){
		while(true){
			try {
				sleep((long)200);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(openConnection()){
				BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Connected to Server");
				handleSession();
				closeSession();
				System.out.println("Client: Do you want to continue add or configure car model?(y/n)");
				
				try {
					if (stdIn.readLine().equals("n"))
						break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	private void setHost(String strHost) {
		this.strHost=strHost;
	}
	private void setPort(int iPort) {
		this.iPort=iPort;
	}
	public Boolean openConnection() {
		try{		
			sock = new Socket(strHost,iPort);
		}catch(IOException socketexception){
			if (DEBUG) System.err.println("Unable to connect to "+strHost);
			return false;
		}
		try{
			reader= new BufferedReader(new InputStreamReader(sock.getInputStream()));
			writer= new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
		}catch(Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream to/from "+strHost);
			return false;
		}
		return true;
	}
	public void handleSession() {
		try{
			BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
			ObjectOutputStream oos= new ObjectOutputStream(sock.getOutputStream());
			DataOutputStream dos=new DataOutputStream(sock.getOutputStream());
			ObjectInputStream ois =new ObjectInputStream(sock.getInputStream());
			proxyAutomotive auto=new BuildAuto();
			String inputLine,outputLine;
			System.out.println("Do you want to: a)Upload a Car model. b)Configure a Car.\nPlease Enter a/b: ");
			outputLine=stdIn.readLine();
			writer.println(outputLine);
			writer.flush();
			if (outputLine.equals("a")){// Upload
				
				
			System.out.println("Client: Please Enter the filename to Read from and to Upload:");
			String filename=stdIn.readLine();
			dos.writeUTF(filename);
			oos.flush();
			System.out.println("Client: Uploading...");
			while(true){
				if((inputLine=reader.readLine())==null){
					break;
				}
				System.out.println("Server: "+inputLine);	
			}
			
			
		}
			else if (outputLine.equals("b")){//configure
				while(!(inputLine=reader.readLine()).equals("")){
					
					System.out.println("Server: "+inputLine);
					if (inputLine.equals(""))
						break;
					}
					System.out.println("Client: Please Enter the Model of Car to Choose:");
					outputLine=stdIn.readLine();
					writer.println(outputLine);
					writer.flush();
					System.out.println("Client: Sending Request...");
					auto.setA1( (Automotive) ois.readObject());
					System.out.println("Client: Model Recieved.");
					auto.print();
					System.out.println("Client: Enter Any Key to Continoue to Build Your Own Car");
					stdIn.readLine();
					setupChoice(auto);
				
			}
			}catch(IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MissingBasicInfoExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		}
	public void closeSession() {
		try{
			writer.close();;
			reader.close();;
			sock.close();
		}catch (IOException ioe){
			if(DEBUG) System.err.println("Error closing socket to "+strHost);
		}		
	}


public void setupChoice(proxyAutomotive auto){
	try {			
		BufferedReader stdIn=new BufferedReader(new InputStreamReader(System.in));
		for(Iterator itr=auto.getA1().getOptionSetNamesIterator();itr.hasNext();){
			String OptSetName=(String) itr.next();
			System.out.println("Client: Please choose the "+OptSetName);
			for(int i=0;i<auto.getOptionSet(OptSetName).counter();i++)
				System.out.println((i+1)+". "+auto.getOptionSet(OptSetName).getelement(i).getName()+"\tPrice is "+"$"+auto.getOptionSet(OptSetName).getelement(i).getPrice());
			int j=Integer.parseInt(stdIn.readLine());
			auto.getOptionSet(OptSetName).setChoice(auto.getOptionSet(OptSetName).getelement(j-1));
		}		
		////////////////////////print the choice and total price
		System.out.println("Client: Your Choices have been made. Here is your model:");
		System.out.println(auto.getMake()+" "+auto.getModel()+" Base Price is $"+auto.getBasePrice());
		for(Iterator itr=auto.getA1().getOptionSetNamesIterator();itr.hasNext();){
			String OptSetName=(String) itr.next();
			System.out.println(OptSetName+": "+auto.getOptionChoice(OptSetName)+" $"+auto.getOptionChoicePrice(OptSetName));	
		}
		System.out.println("Total Price is $"+auto.getTotalPrice());
		
		
		
		
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (EmptyChoiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}