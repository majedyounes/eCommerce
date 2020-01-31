package ecommerce;

public class Acquisto implements Comparable<Acquisto> {
	String data;
	double importo;
	public Acquisto(String data, double importo) {
		super();
		this.data = data;
		this.importo = importo;
	}
	
	public int compareTo(Acquisto o){
		int prov=this.data.compareTo(o.getData());
		if(prov!=0){
			return prov;
		}
		double diff=this.importo-o.getImporto();
		
		if(diff<0)
			return -1;
		else if(diff>0)
			return 1;
		else
			return 0;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

}
