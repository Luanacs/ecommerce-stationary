package shopping;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.App;
import entities.Client;
import entities.Inventory;
import entities.Product;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Client client =new Client();
		App shopp=new App(client);
				
		
		shopp.opening();
		
		Inventory menu = new Inventory();
		menu.printInventory();
		
		shopp.initialClient();
		
	
		

	}

}
