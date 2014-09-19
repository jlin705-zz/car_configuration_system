package Adapter;

import java.util.Iterator;
import java.util.LinkedHashMap;

import Exception.BlankFileException;
import Exception.EmptyChoiceException;
import Exception.InvalidOptionException;
import Exception.InvalidOptionSetException;
import Exception.MissingBasicInfoExcepiton;
import Model.*;
import Model.OptionSet.Option;
import Utility.Deserialize;
import Utility.Fileio;
import Utility.Serialize;

public abstract class proxyAutomotive {
	private static Automotive a1=new Automotive();
	private LinkedHashMap<String,Automotive> AutoMap = new LinkedHashMap<String,Automotive>(); //initiate a Map to store Automotive
	
	public void readFile(String filename){
		try {
			Fileio read=new Fileio();
			a1= read.readFile(filename, a1);
		} catch (BlankFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void seri(String filename){
		Serialize seri =new Serialize();
		seri.serial(filename, a1);
	}
	public Automotive deseri(String filename){
		Deserialize deseri=new Deserialize();	
		Automotive autoRestore = (Automotive) deseri.deserial(filename);
		return autoRestore;
	}
	public void setMake(String make){
		a1.setMake(make);
	}
	public void setModel(String model){
		a1.setModel(model);
	}
	public void setBasePrice(int price){
		a1.setBasePrice(price);
	}
	public int getBasePrice(){
		return a1.getBasePrice();
	}
	public String getModel(){
		return a1.getModel();
	}
	public String getMake(){
		return a1.getMake();
	}
	public void setA1(Automotive a1) {
		proxyAutomotive.a1 = a1;
	}
	public void addOptionSet(String setName, OptionSet o){
		a1.addOSlist(setName, o);
	}
	public void removeOptionSet(String OptionSet_Name){
		a1.delOS(OptionSet_Name);
	}
	public Option getOption(String setName,String OptionName) throws InvalidOptionException, InvalidOptionSetException{
		return a1.getOption(setName, OptionName);
	}
	public void setOption(String setName,int index,String optionName, int price) throws Exception{
		a1.setOption(setName, index, optionName, price);
	}
	
	@SuppressWarnings("rawtypes")
	public Iterator getOptionSetNamesIterator(){
		return a1.getOptionSetNamesIterator();
	}
	
	public String getOptionChoice(String setName) throws EmptyChoiceException{
		return a1.getOptionChoice(setName);
	}
	
	public int getOptionChoicePrice(String setName) throws EmptyChoiceException{
		return a1.getOptionChoicePrice(setName);
	}
	
	public void setOptionChoice(String setName, String optName) throws InvalidOptionException{
		a1.setOptionChoice(setName, optName);
	}
	
	public int getTotalPrice(){
		return a1.getTotalPrice();
	}
	/*
	 * print info of automotive
	 */
	@SuppressWarnings("rawtypes")
	public void print() throws MissingBasicInfoExcepiton{
		if (a1.getMake().equals(""))
			throw new MissingBasicInfoExcepiton();
		if(a1.getModel().equals(""))
			throw new MissingBasicInfoExcepiton();
		if (a1.getBasePrice()==0)
			throw new MissingBasicInfoExcepiton();

		System.out.println(a1.getMake()+"\t"+a1.getModel()+"\t Base Price is "+a1.getBasePrice()+"\n");
		for(Iterator itr= a1.getOptionSetNamesIterator();itr.hasNext();){
			String a= (String) itr.next();
			OptionSet os=a1.getOptionSet(a);
			int count= os.counter();
			os.printOption();
			for(int j=0;j<count;j++){
				os.getelement(j).printOption();
			}
			System.out.println("\n");
		}
	}
	public Automotive getA1() {
		// TODO Auto-generated method stub
		return a1;
	}
	public OptionSet getOptionSet(String setName){
		return  a1.getOptionSet(setName);
	}
	public LinkedHashMap<String,Automotive> getAutoMap() {
		return AutoMap;
	}
	public void setAutoMap(LinkedHashMap<String,Automotive> autoMap) {
		AutoMap = autoMap;
	}
	public void addAutoMap(String ModelName, Automotive auto){
		AutoMap.put(ModelName, auto);
	}
	public Automotive getAutomotiveInMap(String ModelName){
		return AutoMap.get(ModelName);
	}
	public int CounterOfMap(){
		return AutoMap.size();
	}
	@SuppressWarnings("rawtypes")
	public Iterator getModelNamesIterator(){		//Setup Iterator
		Iterator itr= AutoMap.keySet().iterator();
		return itr;
	}
//////////////////////////////////////////////////////////internal interface
	public interface SocketClient{
		Boolean openConnection();
		void handleSession();
		void closeSession();		
	}
	public interface SocketClientConstants{
		int iECHO_PORT=7;
		int iDAYTIME_PORT=13;
		int iSMTP_PORT=25;
		Boolean DEBUG=true;
	}
}
