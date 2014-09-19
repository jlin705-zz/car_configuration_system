package Driver;

import Model.*;
import Adapter.*;
import Exception.MissingBasicInfoExcepiton;

public class TestUnit2 {
	public static void main(String[] args){
		proxyAutomotive myAuto= new BuildAuto();
		/*
		 * Read data from car_info.txt
		 */
		myAuto.readFile("Prius.txt");
		
		myAuto.addAutoMap(myAuto.getModel(), myAuto.getA1());
		try {
			myAuto.print();
		} catch (MissingBasicInfoExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		myAuto.seri("auto.ser");
		
		/*Serialize seri =new Serialize();
		seri.serial("auto.ser", myAuto.getA1());
		/*
		 * Deserialization  
		 */
		Automotive autoRestore=myAuto.deseri("auto.ser");
		
		/*
		 * Print out restored object
		 */
		myAuto.setA1(autoRestore);
		try {
			myAuto.print();
		} catch (MissingBasicInfoExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}