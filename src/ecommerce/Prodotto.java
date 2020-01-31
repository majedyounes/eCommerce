package ecommerce;

public class Prodotto {
	
	String codice;
	String nome;
	String descrizione;
	double prezzo;
	
	public Prodotto(String codice, String nome, String descrizione,
			double prezzo) {
		this.codice = codice;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
	}

	public String getCodice() {
		return this.codice;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public double getPrezzo() {
		return this.prezzo;
	}


	
}


