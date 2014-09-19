package Utility;

import java.io.*;
import java.util.StringTokenizer;
import Exception.BlankFileException;
import Model.*;


public class Fileio {
	
	public Automotive readFile(String filename,Automotive auto) throws BlankFileException{
		try{
			FileReader fileReader =new FileReader(filename);					//Read file from txt
			BufferedReader reader = new BufferedReader(fileReader);				
			String line= null;
			int j=0;
			String name;
			int price;
			line=reader.readLine();
			if (line.equals(null)&&j==0){
				reader.close();
				throw new BlankFileException();
				}
			StringTokenizer basic= new StringTokenizer(line);
			auto.setMake(basic.nextToken());
			auto.setModel(basic.nextToken());
			basic.nextToken();
			auto.setBasePrice(Integer.parseInt(basic.nextToken()));
			reader.readLine();
			while (!(line=reader.readLine()).equals("")){
				OptionSet os=new OptionSet();
				os.setName_set(line);	// Set OptionSet Name	
				while(!(line= reader.readLine()).equals("")){
					StringTokenizer st= new StringTokenizer(line);
					name=st.nextToken(); 								//read Option name
					price= Integer.parseInt(st.nextToken());			// read price
					OptionSet.Option opt= os.new Option(name, price); // set Option name and price
					opt.setName(name);
					opt.setPrice(price);
					os.addOptlist(opt);		// Store Options into ArrayList in os
					}	//close while			
				String a= os.getName_set();

				auto.addOSlist(a,os);		// Store OptionSets into Array List in auto

				j++;
				}//close for loop
			reader.close();
			
		}	 catch (IOException e){
			e.printStackTrace();
			}//close catch
		return auto;
		}	//close readFile
}	

