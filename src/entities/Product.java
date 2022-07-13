package entities;

public class Product {

	private String name;
	private String type;
	private int code;
	private double price;

	public Product( String type,String name, int code, double price) {
		super();
		this.name = name;
		this.type=type;
		this.code = code;
		this.price = price;
	}

	public Product() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String toString() {
		return type +"\t"+ name + "\t\t" + code + "\t\t" + price;
	}

}
