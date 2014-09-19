package Socket;

import java.io.*;
import java.net.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Adapter.*;
import Exception.BlankFileException;
import Exception.MissingBasicInfoExcepiton;
import Model.Automotive;
import Utility.Fileio;

public class Server extends Thread{
	private static int iPort1=4242;
	static LinkedHashMap<String,Automotive> AutoMap = new LinkedHashMap<String,Automotive>();
	public void setPort1(int iPort){
		this.iPort1=iPort;
	}
	public  void run(){
		
		ServerSocket server = null;
		try {
			server = new ServerSocket(iPort1);
		}catch(IOException e){
			System.err.println("Could not listen on port: "+iPort1);
			System.exit(1);
		}
		try{
			Socket inSocket;
			while(true){
			inSocket = server.accept();
			handleConnection(inSocket);		
			inSocket.close();}
		} catch (IOException e) {
			e.printStackTrace();
			}				
	}	
	private static void handleConnection(Socket inSocket) {
		try {
			String outputLine,inputLine;
			PrintWriter writer=new PrintWriter(inSocket.getOutputStream());
			ObjectInputStream ois =new ObjectInputStream(inSocket.getInputStream());
			DataInputStream dis = new DataInputStream(inSocket.getInputStream());
			BufferedReader reader= new BufferedReader(new InputStreamReader(inSocket.getInputStream()));
			ObjectOutputStream oos = new ObjectOutputStream(inSocket.getOutputStream());
			
			Fileio read=new Fileio();
			//PreLoad Exsiting Car Model
			Automotive a1=new Automotive();
			a1=read.readFile("Focus_Wagon_ZTW.txt", a1);
			AutoMap.put(a1.getModel(), a1);
			Automotive a2=new Automotive();
			a2=read.readFile("Civic.txt",a2);
			AutoMap.put(a2.getModel(), a2);
			
			while((inputLine=reader.readLine())!=null){
				break;
				}
			if(inputLine.equals("a")){
				Automotive newAuto=new Automotive();
				newAuto=read.readFile(dis.readUTF(), newAuto);
				AutoMap.put(newAuto.getModel(), newAuto);
				outputLine="Server: File Recieved";
				writer.println(outputLine);
				System.out.println(outputLine);
				outputLine="Server: Creating Automotive Object...";
				writer.println(outputLine);
				writer.flush();
				System.out.println(outputLine);
				outputLine="Server: Object Created";
				writer.println(outputLine);
				writer.flush();
				System.out.println(outputLine);	
			
				}
			else if(inputLine.equals("b")){
				for(@SuppressWarnings("rawtypes")
				Iterator itr=AutoMap.keySet().iterator();itr.hasNext();){
				String model=(String) itr.next();
				Automotive a=AutoMap.get(model);
				writer.println(a.getMake()+" "+a.getModel()+"\t"+"Base Price is "+a.getBasePrice());
				writer.flush();	
				}	
				writer.println("");
				writer.flush();
				while((inputLine=reader.readLine())!=null){
					System.out.println(inputLine);
					oos.writeObject(AutoMap.get(inputLine));
					oos.flush();	
					break;
					}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BlankFileException e) {
			e.printStackTrace();
			}					
	}	
}
	