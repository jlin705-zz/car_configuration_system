package Driver;

import Adapter.BuildAuto;
import Adapter.EditOptions;
import Adapter.proxyAutomotive;
import Exception.InvalidOptionException;
import Exception.MissingBasicInfoExcepiton;

public class Thread_1 implements Runnable{
	EditOptions edit= new EditOptions();
	proxyAutomotive auto=new BuildAuto();
	public void run(){
		try {
			edit.changeOptPrice("Power_Moonroof", "Present", 500);
		} catch (InvalidOptionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			auto.print();
		} catch (MissingBasicInfoExcepiton e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
