package ecommerce;
import java.util.*;

public class Utente {
	int codice;
	String nome;
	String cognome;
	String email;
	String indirizzo;
	Map<String,Integer> carrello= new TreeMap<String,Integer>();
	Sito sito;
	

	public Utente(int codice, String nome, String cognome, String email,
			String indirizzo) {
		super();
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.indirizzo = indirizzo;
	}

	public int getCodice() {
		return codice;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getEmail() {
		return email;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setSito( Sito sito){
		this.sito=sito;
	}

	public void selezionaProdotto(Prodotto prodotto){
		int quantitaAttuale=0;
		if(carrello.containsKey(prodotto.getCodice())){
			quantitaAttuale=carrello.get(prodotto.getCodice());
			carrello.remove(prodotto.getCodice());
		}
		
		carrello.put(prodotto.getCodice(), quantitaAttuale+1);
	}
	
	public double paga(String data){
		
		double totale= sito.totaleCarrello(this);
		
		
		carrello.clear();
		return totale;
	}

}
