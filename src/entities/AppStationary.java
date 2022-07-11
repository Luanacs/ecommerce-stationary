package entities;

public class AppStationary {
	
	private Client client;
	private Payment payment;
	private double finalTotal=0;
	private Inventory inventory;
	
	public AppStationary(Client client) {
		this.client=client;
	}
	
	
	
	public void opening() {
		System.out.println("***************************************");
		System.out.println("* Welcome to L'Unité Stationary Store *");
		System.out.println("***************************************");
	}
	


}
