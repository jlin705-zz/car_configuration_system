package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Exception.InvalidOptionException;

@SuppressWarnings("serial")
public class OptionSet implements Serializable{ //inner class OS
	private String Name_set;
	private ArrayList<Option> optList = new ArrayList<Option>();// initiate a ArrayList to Store Options
	private int count;
	private Option choice;
	
	public OptionSet(){
	}
	public OptionSet(String name_set){
		this.Name_set=name_set;
	}
	public OptionSet(String name_set, int count){
		this.Name_set=name_set;
	}	
	public String getName_set() {						// getter for OptionSet Name
		return Name_set;
	}
	public void setName_set(String name_set) {			//setter for OptionSet Name
		Name_set = name_set;
	}	
	public ArrayList<Option> getOptList() {				//getter for ArrayList
		return optList;
	}
	public void setOptList(ArrayList<Option> optList) {	//setter for ArrayList
		this.optList = optList;
	}
	public void addOptlist(Option a){					// add Options to ArrayList
		optList.add(a);
	}
	public Option getelement(int i){					//pop Options from Arraylist
		return optList.get(i);
	}
	public int counter(){								//return the size of ArrayList		
		return optList.size();
	}
	public void printOption(){							// print OptionSet Name
		System.out.println(getName_set());
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public Option getOption(String optName) throws InvalidOptionException {			// get Option from ArrayList based on Option Name
		Option opt=null;
		for(int i=0;i<optList.size();i++){
			if (optList.get(i).getName().equalsIgnoreCase(optName))
				opt=optList.get(i);
		}
		if(opt==null)
			throw new InvalidOptionException();
		return opt;
	}
	public void removeOption(String Option_Name){
		Option opt=null;
		try {
			opt = getOption(Option_Name);
		} catch (InvalidOptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		optList.remove(opt);
		
	}
	
	public Option getChoice() {							//getter for Choice
		return choice;
	}
	public void setChoice(Option choice) {				//setter for Choice
		this.choice = choice;
	}

	////////////////////////////// Inner Class Opiton //////////////////////////////////////////////////////
	public class Option implements Serializable{ 			
		private int Price;
		private String Name;
		public Option(String name, int price){
			this.Name=name;
			this.Price=price;
		}
		public Option(){
			
		}
		public String getName(){						//getter for Option Name
			return Name;
		}
		public void setName(String a){					//setter for Option Name
			Name=a;
		}
		public int getPrice(){							//getter for price
			return Price;
		}
		public void setPrice(int i){					//setter for price
			Price= i;
		}
		public void printOption(){						//print Option name and price
			System.out.println(getName()+", Price is "+getPrice());
		}
	}	
}
