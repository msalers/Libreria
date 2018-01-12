

public class Libro {
	
	//variabili
	private String titolo;
	private String sottotitolo;
	private String autore;
	private String genere;
	private String serie;
	private String luogo;
	private int copie;
	private int id;
	
	//costruttore
	public Libro(String titolo, String sottotitolo,String autore, String genere, String serie, String luogo, int copie, int id){
		this.titolo=titolo;
		this.sottotitolo=sottotitolo;
		this.autore=autore;
		this.genere=genere;
		this.serie=serie;
		this.luogo=luogo;
		this.copie=copie;
		this.id=id;
	}
	
	//metodi
	public String getTitolo(){
		return titolo;
	}
	
	public String getSottotitolo(){
		return sottotitolo;
	}
	
	public String getAutore(){
		return autore;
	}
	
	public String getGenere(){
		return genere;
	}
	
	public String getSerie(){
		return serie;
	}
	
	public String getLuogo(){
		return luogo;
	}
	
	public int getCopie(){
		return copie;
	}
	
	public int getId(){
		return id;
	}
	
	public void addCopie(){
		this.copie=copie+1;
	}
	
	public void modLuogo(String luogo_nuovo){
		this.luogo=luogo_nuovo;
	}
	
	public void modTitolo(String titolo_nuovo){
		this.titolo=titolo_nuovo;
	}
	
}
