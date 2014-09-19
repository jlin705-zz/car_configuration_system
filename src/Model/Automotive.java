package Model;

import java.io.*;
import java.util.*;

import Exception.*;
import Model.OptionSet.Option;

@SuppressWarnings("serial") 
public class Automotive implements Serializable {
	private LinkedHashMap<String,OptionSet> os = new LinkedHashMap<String,OptionSet>(); //initiate a ArrayList to store Operation Sets
	private int basePrice=0;
	private String make=null;
	private String model=null;
	public Automotive() {
	}
	
	
	public LinkedHashMap<String, OptionSet> getOS() {			// Getter for Map
		return os;
	}
	public void setOS(LinkedHashMap<String,OptionSet> os) {	//Setter for Map
		this.os = os;
	}
	public void addOSlist(String osName,OptionSet o){				// add element(OptionSet) to Map	
		os.put(osName,o);
	}
	public void delOS(String osName){
		os.remove(osName);
	}
	public OptionSet getOptionSet(String osName){				// pop element from the Map	
		return os.get(osName);
	}
	public int counter(){							// count the size of Map
		return os.size();
				
	}
	@SuppressWarnings("rawtypes")
	public Iterator getOptionSetNamesIterator(){		//Setup Iterator
		Iterator itr= os.keySet().iterator();
		return itr;
	}
	public String getMake() {					//getter for Make
		return make;
	}
	public void setMake(String make) {			//setter for Make
		this.make = make;
	}
	public String getModel() {					//getter for Model
		return model;
	}
	public void setModel(String model) {		//setter for Model
		this.model = model;
	}
	public int getBasePrice() {					//getter for Base Price
		return basePrice;
	}
	public void setBasePrice(int basePrice) {	//setter for Base Price
		this.basePrice = basePrice;
	}
	public OptionSet addOptionSet(String s,int i){				//add Option Set to Map
		OptionSet optSet= new OptionSet();
		optSet.setName_set(s);
		optSet.setCount(i);
		return optSet;
	}
	public void setOption(String optSet_Name, int index, String opt_Name, int price) throws Exception{
		Option opt;
		if (index>os.size())
			throw new OverSizeException();
		opt = getOption(optSet_Name, opt_Name);
	
		opt.setName(opt_Name);
		opt.setPrice(price);
		
			
		
	}
	public Option getOption(String optSet_Name, String opt_Name) throws InvalidOptionSetException, InvalidOptionException{
		
		Option opt=getOptionSet(optSet_Name).getOption(opt_Name);
		if (opt==null)
			throw new InvalidOptionSetException();
		return opt;
	}
	public void setOptionChoice(String optSet_Name, String opt_Name) throws InvalidOptionException{
		
		getOptionSet(optSet_Name).setChoice(getOptionSet(optSet_Name).getOption(opt_Name));
		
	}
	public String getOptionChoice(String optSet_Name) throws EmptyChoiceException{
		OptionSet optSet= getOptionSet(optSet_Name);
		Option Choice=optSet.getChoice();
		if(optSet.getChoice()==null)					//if there is no choice, throw exception when trying to get Choice
			throw new EmptyChoiceException();
		String ChoiceName=Choice.getName();
		return ChoiceName;
	}
	public int getOptionChoicePrice(String optSet_Name) throws EmptyChoiceException{		
		OptionSet optSet=getOptionSet(optSet_Name);
		Option opt= optSet.getChoice();
		if(optSet.getChoice()==null)					//if there is no choice, throw exception when trying to get Choice Price
			throw new EmptyChoiceException();
		return opt.getPrice();		
	}
	public int getTotalPrice(){							//Calculate Total price
		int total=getBasePrice();
		for(@SuppressWarnings("rawtypes")
		Iterator itr= getOptionSetNamesIterator();itr.hasNext();){
			String a= (String) itr.next();
			try {
				total=total+getOptionChoicePrice(a);
			} catch (EmptyChoiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return total;
	}
}
