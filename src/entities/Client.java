package entities;

public class Client extends Person {

	private Endereco endereco;

	public Client() {

	}

	public Client(Endereco endereco) {
		super();
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
