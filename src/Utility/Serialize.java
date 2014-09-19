package Utility;

import java.io.*;

public class Serialize {
	public void serial(String filename,Object o){
		System.out.println("Serializing.....");
		try{
			FileOutputStream fs= new FileOutputStream(filename);	//make a FileOutput Stream
			ObjectOutputStream os= new ObjectOutputStream(fs);		//set ObejectOputStream to write
			os.writeObject(o);			// writeObject
			os.close();					//close
		} catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("Serialization finished.");
	}
	
}
