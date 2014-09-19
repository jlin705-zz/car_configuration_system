package Utility;

import java.io.*;

public class Deserialize {
	public Object deserial(String filename){
		System.out.println("Deserializing.....");
		Object oj = null;
		try{
			FileInputStream fs= new FileInputStream(filename);	// make FileInputStream
			ObjectInputStream is= new ObjectInputStream(fs);	// set ObjectInputStream
			oj=(Object) is.readObject();		// read Object
			is.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("Deserialization finished!");
		return oj;
	}
}
