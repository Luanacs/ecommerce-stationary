package shopping;

import entities.AppStationary;
import entities.Client;
import entities.Inventory;

public class Main {

	public static void main(String[] args) {
		
		Client client =new Client();
		AppStationary shopp=new AppStationary(client);
		
		shopp.opening();
		
		Inventory menu = new Inventory();
		menu.printInventory();

	}

}
