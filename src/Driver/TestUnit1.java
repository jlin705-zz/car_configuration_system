package Driver;

import java.util.Iterator;

import Exception.BlankFileException;
import Model.*;
import Utility.*;

public class TestUnit1 {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args){
		Automotive auto= new Automotive();
		OptionSet os= new OptionSet();
		Fileio read=new Fileio();
		
		/*
		 * Read data from car_info.txt
		 */
		try {
			auto= read.readFile("Focus_Wagon_ZTW.txt", auto);
		} catch (BlankFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * Print data in objects
		 */
		
		for (Iterator itr= auto.getOptionSetNamesIterator();itr.hasNext();){
			os=auto.getOptionSet((String)itr.next());
			int count= os.counter();
			os.printOption();
			for(int j=0;j<count;j++){
				os.getelement(j).printOption();
			}
			System.out.println("\n");
		}
		/*
		 * serialization auto and os into 2 ser file
		 */
		Serialize seri =new Serialize();
		seri.serial("auto.ser", auto);
		/*
		 * Deserialization  
		 */
		Deserialize deseri=new Deserialize();	
		Automotive autoRestore = (Automotive) deseri.deserial("auto.ser");
		
		/*
		 * Print out restored object
		 */
		OptionSet osRestore=new OptionSet();
		for (Iterator itr= autoRestore.getOptionSetNamesIterator();itr.hasNext();){
			osRestore=autoRestore.getOptionSet((String)itr.next());
			int count= osRestore.counter();
			osRestore.printOption();
			for(int j=0;j<count;j++){
				osRestore.getelement(j).printOption();
			}
			System.out.println("\n");
		}
		
	}
}