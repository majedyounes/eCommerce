package ecommerce;

public class Categoria {
	String nome;

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String assegnaCodice(){
		char c=this.nome.charAt(0);
		String s=""+c+"";
		return s;
		
	}

}
