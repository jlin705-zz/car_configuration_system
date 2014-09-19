package Adapter;

import Exception.InvalidOptionException;
import Model.*;
import Model.OptionSet.Option;

public class EditOptions {
	
	BuildAuto auto =new BuildAuto();
	
	public synchronized void addOpt(String OptSet_Name, Option opt){
		OptionSet ops= auto.getOptionSet(OptSet_Name);
		ops.addOptlist(opt);
		
	}
	public synchronized  void delOpt(String OptSet_Name, String opt_Name){
		OptionSet ops=auto.getOptionSet(OptSet_Name);
		ops.removeOption(opt_Name);
	}
	public  synchronized void changeOptName(String optSetName ,String oldName, String newName) throws InvalidOptionException{
		auto.getOptionSet(optSetName).getOption(oldName).setName(newName);	
	}
	public synchronized  void changeOptPrice(String optSetName,String optName, int newPrice) throws InvalidOptionException{
		auto.getOptionSet(optSetName).getOption(optName).setPrice(newPrice);
	}
	
	public synchronized  void addOptSet( String Option_Name, OptionSet optSet){
		auto.addOptionSet(Option_Name, optSet);;
		
	}
	public  synchronized void delOptSet(String Option_Name){
		auto.removeOptionSet(Option_Name);;
	}
	public synchronized  void changeOptSetName(String old_name, String new_name){
		auto.getOptionSet(old_name).setName_set(new_name);	
	}
	
	

}
