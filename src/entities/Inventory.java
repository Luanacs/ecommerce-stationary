package entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private Products product1 = new Products("BeKind Notebook","001", 29.0);
	private Products product2 = new Products("SweetMoon Notebook","002", 25.0);
	private Products product3 = new Products("Flowers Notebook","003", 27.0);
	private List<Products> listProducts = new ArrayList<>();

	public Products getProduct1() {
		return product1;
	}

	public Products getProduct2() {
		return product2;
	}

	public Products getProduct3() {
		return product3;
	}

	public void listProducts() {
		this.listProducts.add(product1);
		this.listProducts.add(product2);
		this.listProducts.add(product3);
	}

	public void printInventory() {
		listProducts();
		for (Products listProducts : listProducts) {
			System.out.println(listProducts.getName() + "\t\t" + listProducts.getCode() + "\t" + listProducts.getPrice());
		}
	}

}
