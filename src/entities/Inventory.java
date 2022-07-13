package entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private Product product1 = new Product("Notebook","BeKind",001, 29.0);
	private Product product2 = new Product("Notebook","Sweet",002, 25.0);
	private Product product3 = new Product("Notebook","Flowers",003, 27.0);
	private List<Product> listProducts = new ArrayList<>();
	
	public Inventory() {
		
	}
	
	public Product getProduct1() {
		return product1;
	}

	public Product getProduct2() {
		return product2;
	}

	public Product getProduct3() {
		return product3;
	}


	public void listProducts() {
		this.listProducts.add(product1);
		this.listProducts.add(product2);
		this.listProducts.add(product3);
	}
	public List<Product> getListProduct(){
		return listProducts;
	}

	public void printInventory() {
		listProducts();
		for (Product product : listProducts) {
			System.out.println(product.getType()+"\t"+ product.getName() +"\t\t" +product.getCode()  +"\t"+ product.getPrice());
		}
	}


}
