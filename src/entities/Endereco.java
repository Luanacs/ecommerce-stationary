package entities;

public class Endereco {

	private String cep;
	private String rua;
	private int numero;
	private String cidade;

	public Endereco() {

	}

	public Endereco(String cep, String rua, int numero, String cidade) {
		super();
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}
