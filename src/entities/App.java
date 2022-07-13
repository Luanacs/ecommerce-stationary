package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	private Client client;

	List<Product> clientProducts=new ArrayList();
	
	
	Inventory inv =new Inventory();
	Scanner sc = new Scanner(System.in);

	public App(Client client) {
		this.client = client;
	}

	public void opening() {
		System.out.println("********************************************");
		System.out.println("*                                          *");
		System.out.println("*    Welcome to L'Unité Stationary Store   *");
		System.out.println("*                                          *");
		System.out.println("********************************************");
		System.out.println("|         This are ours products:          |");
		System.out.println("********************************************");
		System.out.println("Type:            Name:         Code:   Price");
		
	}

	public void addItem(Product product) {
		this.clientProducts.add(product);
	}
	
	
	public void initialClient() {
		inv.listProducts();
	
		System.out.println("\nWould you like to add an item to your shopping list? \n1-Yes \n2-No, another informations \n3-Exit");
		int answer=sc.nextInt();
		sc.nextLine();
		if (answer==1) {
			
			System.out.println("\nEnter the product code you want to add:");
			int code=sc.nextInt();
			boolean productExists=false;
			for (Product product : inv.getListProduct()) {
				if(code==product.getCode()) {
					productExists=true;
					break;
				}
			}
			if (productExists) {
				System.out.println("Product add to your list");
			}
			else {
				System.out.println("Product does not exist, please select again");
			}
		}
		else if(answer==2) {
			System.out.println("How could we help you?");
			System.out.println("1-Contact us");
			System.out.println("2-Informations about my shopping");
			System.out.println("3-Sugestions and coments");
			System.out.println("4-Exit");
			int answer2=sc.nextInt();
			switch(answer2) {
			case 1:
				System.out.println("You may contact us on the phone (00)0000-0000 or you may send an email in email@email.com, we will be returning in 5 days.");
				break;
			case 2:
				System.out.println("What you would like to know about your shopping? \n1-The paymente has already been aproved? \n2-Location of the package \n3-Switch \n4-Cashback");
				break;
			
			case 3:
				System.out.println("Leave your suggestion/comment below:");
				break;
			case 4:
				System.out.println("Do you really want to leave the site?\n1-Yes \n2-No");
				break;
			default: 
				System.out.println("Sorry, this is not a valid answer, please type again.");
			}
			
		}
		else if(answer==3) {
			System.out.println("Thank you for visiting, come back always!");
		}
		
	}

}
