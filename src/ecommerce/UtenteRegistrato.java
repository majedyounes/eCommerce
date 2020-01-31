package ecommerce;

import java.util.*;

public class UtenteRegistrato extends Utente{

	String username;
	String password;
	boolean loggato=false;
	List<Acquisto> acquisti= new LinkedList<Acquisto>();
	
	
	public UtenteRegistrato(int codice, String nome, String cognome,
			String email, String indirizzo, String username, String password) {
		super(codice, nome, cognome, email, indirizzo);
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public void login(String username, String password) throws EccezioneLoginFallito{
		if(this.username.equals(username) && this.password.equals(password)){
			loggato=true;
		}
		else{
			throw new EccezioneLoginFallito();
		}
	}

	public void logout(){
		loggato=false;
	}
	
	public boolean isLoggato() {
		return loggato;
	}
	
	
	public double paga(String data){
		
		double totale= sito.totaleCarrello(this);
		if(loggato){
			Acquisto a= new Acquisto(data,totale);
			acquisti.add(a);
		}
		carrello.clear();
		return totale;
	}
	
	public String storicoAcquisti(){
		Collections.sort(acquisti);
		String s="";
		
		for(int i=0;i<acquisti.size();i++){
			Acquisto a=acquisti.get(i);
			s=s+a.getData()+" "+a.getImporto()+";";
		}
		return s;
	}
	
	
}
