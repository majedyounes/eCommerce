package ecommerce;

import java.util.*;
import java.io.*;

public class Sito {
	Map<String,Categoria> categorie= new TreeMap<String,Categoria>();
	int codiceProdotto=100;
	List<Prodotto> prodotti= new LinkedList<Prodotto>();
	int codiceUtente=1;
	Map<Integer,Utente> utenti= new TreeMap<Integer,Utente>();

	
	public void nuovaCategoria(String nomeCategoria){
		if(!categorie.containsKey(nomeCategoria)){
			Categoria c= new Categoria(nomeCategoria);
			categorie.put(nomeCategoria, c);
		}
	}

	public Collection<String> elencoCategorie(){
		return new LinkedList<String>(categorie.keySet());
	}
	
	public String nuovoProdotto(String nomeCategoria, String nomeProdotto, String descrizione, double prezzo){
		Categoria c= categorie.get(nomeCategoria);
		if(c==null){
			this.nuovaCategoria(nomeCategoria);
		}
		c=categorie.get(nomeCategoria);
		String codice=""+c.assegnaCodice();
		if(codiceProdotto>=10000){
			codice=codice+codiceProdotto;
		}
		else if(codiceProdotto>=1000){
			codice=codice+"0"+codiceProdotto;
		}
		else{
			codice=codice+"00"+codiceProdotto;
		}
		codiceProdotto++;
		Prodotto p= new Prodotto(codice,nomeProdotto,descrizione,prezzo);
		prodotti.add(p);
		
		return codice;
	}
	
	public Prodotto cercaProdotto(String stringaRicerca){
		stringaRicerca=stringaRicerca.toLowerCase();
		for(Prodotto p:prodotti){
			String codicep=p.getCodice().toLowerCase();
			String nomep=p.getNome().toLowerCase();
			String descrizionep=p.getDescrizione().toLowerCase();
			
			if(codicep.indexOf(stringaRicerca)>=0 || nomep.indexOf(stringaRicerca)>=0
					|| descrizionep.indexOf(stringaRicerca)>=0){
				return p;
			}
			
		}
		return null;
	}
	
	public Collection<Prodotto> elencoProdottiPerNome(){
		Collections.sort(prodotti, new ComparatoreNome());
		return prodotti;
	}

	public Collection<Prodotto> elencoProdottiPerPrezzo(){
		Collections.sort(prodotti, new ComparatorePrezzo());
		return prodotti;
	}
	
	public Collection<Prodotto> elencoProdottiPerNome(String nomeCategoria){
		List<Prodotto> l= new LinkedList<Prodotto>();
		char cat= nomeCategoria.charAt(0);
		for(Prodotto p:prodotti){
			char cod= p.getCodice().charAt(0);
			if(cat==cod){
				l.add(p);
			}
		}
		Collections.sort(l,new ComparatoreNome());
		return l;
	}

	public Collection<Prodotto> elencoProdottiPerPrezzo(String nomeCategoria){
		List<Prodotto> l= new LinkedList<Prodotto>();
		char cat= nomeCategoria.charAt(0);
		for(Prodotto p:prodotti){
			char cod= p.getCodice().charAt(0);
			if(cat==cod){
				l.add(p);
			}
		}
		Collections.sort(l,new ComparatorePrezzo());
		return l;
	}
	
	public void nuovoUtente(String nome, String cognome, String email, String indirizzo){
		boolean trovato=false;
		for(Utente u:utenti.values()){
			if(u.getEmail().equals(email)){
				trovato=true;
			}
		}
		
		if(!trovato){
			Utente u= new Utente(codiceUtente,nome,cognome,email,indirizzo);
			u.setSito(this);
			utenti.put(codiceUtente, u);
			codiceUtente++;
		}
	}
	
	public void nuovoUtente(String nome, String cognome, String email, String indirizzo, String username, String password){
	
		boolean trovato=false;
		for(Utente u:utenti.values()){
			if(u.getEmail().equals(email)){
				trovato=true;
			}
		}
		
		if(!trovato){
			UtenteRegistrato u= new UtenteRegistrato(codiceUtente,nome,cognome,email,indirizzo,username,password);
			utenti.put(codiceUtente, u);
			u.setSito(this);
			codiceUtente++;
		}
	
	}
	
	public Utente cercaUtente(int codiceUtente) throws EccezioneUtenteInesistente{
		Utente u= utenti.get(codiceUtente);
		if(u== null){
			throw new EccezioneUtenteInesistente();
		}
		return u;
	}
	
	public String dettagliCarrello(int codiceUtente) throws EccezioneUtenteInesistente{
		Utente u= utenti.get(codiceUtente);
		if(u==null){
			throw new EccezioneUtenteInesistente();
		}
		String output="";
		
		List<String> l= new LinkedList<String>(u.carrello.keySet());
		
		for(int i=0; i<l.size();i++){
			String cod=l.get(i);
			Prodotto p= this.cercaProdotto(cod);
			output=output+p.getCodice()+" "+p.getPrezzo()+" "+u.carrello.get(cod)+"";
			
			if(i!=l.size()-1){
				output=output+"\n";
			}
			
		}
		
		return output;
	}
	
	public double totaleCarrello(Utente u){
		double totale=0;
		for(String s:u.carrello.keySet()){
			Prodotto p= this.cercaProdotto(s);
			totale=totale+p.getPrezzo()*u.carrello.get(s);
		}
		return totale;
	}
	
	
	
    public void leggiFile(String file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String riga;
			
			while((riga=br.readLine())!=null){
				try{
					String[] ss= riga.split(";");
					StringTokenizer st= new StringTokenizer(riga, ";");
	    			String iniziale= st.nextToken().trim();
	    			
	    			if(iniziale.toUpperCase().equals("P")){
	    				String categoria= st.nextToken().trim();
	    				String nome= st.nextToken().trim();
	    				String descrizione= st.nextToken().trim();
	    				double prezzo= new Double(st.nextToken().trim());
	    				
	    				this.nuovoProdotto(categoria, nome, descrizione, prezzo);
	    			}
	    			
	    			else if (iniziale.toUpperCase().equals("U")){
	    				
	    				
	    				String nome= st.nextToken().trim();
	    				String cognome= st.nextToken().trim();
	    				String email= st.nextToken().trim();
	    				String indirizzo= st.nextToken().trim();
	    				try{
	    				     String username= st.nextToken().trim();
	    				     String password= st.nextToken().trim();
	    				     this.nuovoUtente(nome, cognome, email, indirizzo, username, password);
	    				}
	    				catch (Exception e){
	    					this.nuovoUtente(nome, cognome, email, indirizzo);
	    				}
	    				
	    			}
				}
				
				catch(Exception e){
					e.printStackTrace();
				}
				
				
				
				
				
				
				
			}
			
			br.close();
    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }	
    
    public static class ComparatoreNome implements Comparator<Prodotto>{
    	public int compare(Prodotto a,Prodotto b){
    		return a.getNome().compareTo(b.getNome());
    	}
    }
	
    public static class ComparatorePrezzo implements Comparator<Prodotto>{
    	public int compare(Prodotto a,Prodotto b){
    		double prov= a.getPrezzo()-b.getPrezzo();
    		
    		if(prov<0){
    			return -1;
    		}
    		else if(prov>0){
    			return 1;
    		}
    		else{
    			return 0;
    		}
    	}
    }
}
