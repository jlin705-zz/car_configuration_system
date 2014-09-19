package Driver;

import Socket.ClientClass;
import Socket.Server;

public class TestUnit4 {
	public static void main(String [] args){
	
	Server server = new Server();
	ClientClass client = new ClientClass();

	Thread one = new Thread(server);
	Thread two = new Thread(client);
	one.start();
	two.start();
	}

}
