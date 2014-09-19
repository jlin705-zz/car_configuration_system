package Driver;

import Adapter.BuildAuto;
import Adapter.proxyAutomotive;
import Exception.MissingBasicInfoExcepiton;


public class TestUnit3 {
	
	public static void main(String [] args){
		proxyAutomotive auto=new BuildAuto();
		auto.readFile("Civic.txt");
		try {
			auto.print();
		} catch (MissingBasicInfoExcepiton e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
		Thread_1 test1 = new Thread_1();
		Thread_2 test2 = new Thread_2();
	
		Thread one = new Thread(test1);
		Thread two = new Thread(test2);
		one.start();
		try {
			one.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		two.start();
		}
	}
}

