import java.util.ArrayList;

import my.lib.InputDati;

public class ElencoLibri {
	
	//variabili
	private ArrayList<Libro> elenco_libri;
	public static final String ERRORE1 ="Il libro è già stato inserito nell'elenco!";
	public static final String NO_SERIE="-Non appartiene ad una serie-";
	public static final String NO_SOTTO="-Non ha un sottotitolo-";
	private int id_count;
	
	//costruttore
	public ElencoLibri(){
		elenco_libri=new ArrayList<>();
	}
	
	//metodi
	
	public ArrayList<Libro> vediElencoL(){
		return elenco_libri;
	}
	
	
	public int numeroLibri(){
		return elenco_libri.size();
	}
	
	
	public void addLibroS(Libro aggiungi){
		elenco_libri.add(aggiungi);
	}
	
	public void addLibro(){
		
		String titoloagg;
		String sottotagg;
		String autoreagg;
		String genereagg;
		String serieagg;
		String luogoagg;
		int copieagg;
		
		Libro inserimento=null;
		
		boolean continua=false;
		
		String risp=null;
		
		do{
			titoloagg = InputDati.leggiStringa("Inserisci il titolo del libro da aggiungere: ");
			
			do{
				risp=InputDati.leggiStringaNonVuota("Il libro ha un sottotitolo ? (Si/No)\n->");
			}while(!(risp.equalsIgnoreCase("si")||risp.equalsIgnoreCase("no")));
			
			if(risp.equalsIgnoreCase("si")){
				sottotagg = InputDati.leggiStringa("Inserisci il sottotitolo del libro da aggiungere: ");
			}
			else {
				sottotagg = NO_SOTTO;
			}
			
			autoreagg = InputDati.leggiStringaNonVuota("Inserisci l'autore del libro da aggiungere: ");
			genereagg = InputDati.leggiStringaNonVuota("Inserisci il genere del libro da aggiungere: ");
			
			String rix= null;
			do{
				rix=InputDati.leggiStringaNonVuota("Il libro appartiene ad una serie? (Si/No)\n->");
			}while(!(rix.equalsIgnoreCase("si")||rix.equalsIgnoreCase("no")));
			
			if(rix.equalsIgnoreCase("si")){
				serieagg = InputDati.leggiStringaNonVuota("Inserisci la serie a cui appartiene il libro da aggiungere:\n->");
			}
			else {
				serieagg=NO_SERIE;
			}
			
			luogoagg = InputDati.leggiStringaNonVuota("Inserisci dove si trova il libro da aggiungere: ");
			copieagg = InputDati.leggiInteroConMinimo("Inserisci il numero di copie del libro: ", 1);
			
			this.id_count=id_count+1;
			
			inserimento = new Libro(titoloagg, sottotagg, autoreagg, genereagg, serieagg, luogoagg, copieagg, id_count);
			
			continua=controllaInserimentoTot(inserimento);
			
			if (continua == true){
				System.out.println(ERRORE1);
			}
			
		}while(continua == true);
		
		elenco_libri.add(inserimento);
	}
	
	
	
	public void vediLibri(){
		for(int i=0; i<elenco_libri.size(); i++){
			System.out.println(elenco_libri.get(i).getTitolo()+" di "+elenco_libri.get(i).getAutore()+"\nSerie: "+elenco_libri.get(i).getSerie()+"\nGenere: "+elenco_libri.get(i).getGenere());
		}
		
	}
	
	
	
	public boolean controllaInserimentoTot(Libro libro){
		
		boolean boole=false;
		
		if(libro==null){
			boole=true;
		}
		
		for(int i=0; i<elenco_libri.size(); i++){
			
			if((libro.getTitolo()).equalsIgnoreCase(elenco_libri.get(i).getTitolo())){
				
				if((libro.getAutore()).equalsIgnoreCase(elenco_libri.get(i).getAutore())){
					
					if((libro.getGenere()).equalsIgnoreCase(elenco_libri.get(i).getGenere())){
						
						if(libro.getSottotitolo().equalsIgnoreCase(elenco_libri.get(i).getSottotitolo())){
						
							boole=true;
						}
					}
				}
				
			}
		}
		
		return boole;
	}
	
	
	public boolean controllaInserimentoEL(Libro libro, ArrayList<Libro> elenco){
		
		boolean boole=false;
		
		if(libro==null){
			boole=true;
		}
	 	
		for(int i=0; i<elenco.size(); i++){
			
			if((libro.getId()) == (elenco.get(i).getId())){
				
						
							boole=true;
						
				
			}
		}
		
		return boole;
	}
	
	
	public boolean controllaLibrodaTitolo(String titolo, String autore){
		
		boolean boole = false;
		
		for(int i=0; i<elenco_libri.size(); i++){
			
			if(titolo.equalsIgnoreCase(elenco_libri.get(i).getTitolo()) && autore.equalsIgnoreCase(elenco_libri.get(i).getAutore())){
				boole = true;
			}
		}
		
		return boole;
	}
	
	
	public ArrayList<Libro> ricercaDaTitoloOR(String titololibro){
		
		ArrayList<Libro> elencocercato= new ArrayList<>();
		
		Libro ricercato=null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(titololibro.equalsIgnoreCase(elenco_libri.get(i).getTitolo())){
			
				ricercato = elenco_libri.get(i);
				elencocercato.add(ricercato);
			}
			else {
				String[] titleString = elenco_libri.get(i).getTitolo().split(" ");
				String[] titleIns = titololibro.split(" ");
				int size1 = titleIns.length;
				int size2 = titleString.length;
				
				for(int n=0; n<size1; n++){
					for(int m=0; m<size2; m++){
						if(titleIns[n].equalsIgnoreCase(titleString[m])){
							
							ricercato=elenco_libri.get(i);
							
							if(!(controllaInserimentoEL(ricercato, elencocercato))){
								elencocercato.add(ricercato);
							}
							
							
						}
					}
				}
			}
		}
		
		
		
		return elencocercato;
	}
	
	
	
	public ArrayList<Libro> ricercaDaTitoloAND(String titololibro){
		
		ArrayList<Libro> elencocercato= new ArrayList<>();
		
		Libro ricercato=null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(titololibro.equalsIgnoreCase(elenco_libri.get(i).getTitolo())){
			
				ricercato = elenco_libri.get(i);
				elencocercato.add(ricercato);
			}
			else {
				
				String[] titleIns = titololibro.split(" "); 
				String[] titleString = elenco_libri.get(i).getTitolo().split(" ");
				
				if(stringhePresenti(titleIns, titleString)==true){
					ricercato=elenco_libri.get(i);
							
					if(!(controllaInserimentoEL(ricercato, elencocercato))){
						elencocercato.add(ricercato);
					}
				}
				
			}
		}
		
		
		
		return elencocercato;
	}
	

	public boolean stringhePresenti(String[] titoloIns, String[] titoloTot){
		boolean controllo = true;
		
		int lenIns= titoloIns.length;
		int lenTot = titoloTot.length;
		
		int count = 0;
		
		for(int i=0; i<lenIns; i++){
			
			for(int n=0; n<lenTot; n++){
				
				if(titoloIns[i].equalsIgnoreCase(titoloTot[n])){
					count++;
				}
			}
			
		}
		
		if(count != lenIns){
			controllo = false;
		}
		
		return controllo;
	}









	
	public ArrayList<Libro> ricercaDaAutore(String autore){
		
		ArrayList<Libro> elencocercato= new ArrayList<>();
		Libro aggiunta=null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(autore.equalsIgnoreCase(elenco_libri.get(i).getAutore())){
				
				aggiunta=elenco_libri.get(i);
				elencocercato.add(aggiunta);
			}
			else {
				String[] autorString = elenco_libri.get(i).getAutore().split(" ");
				String[] autoIns = autore.split(" ");
				int size1 = autoIns.length;
				int size2 = autorString.length;
				
				for(int n=0; n<size1; n++){
					for(int m=0; m<size2; m++){
					if(autoIns[n].equalsIgnoreCase(autorString[m])){
						aggiunta=elenco_libri.get(i);
						
						if(!(controllaInserimentoEL(aggiunta, elencocercato))){
							elencocercato.add(aggiunta);
						}
						
					}
					}
				}
			}
		}
		
		
		return elencocercato;
		
	}
	
	public ArrayList<Libro> vediSerieLibro(String serie){
		
		ArrayList<Libro> elencoserie = new ArrayList<>();
		Libro cerca = null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(serie.equalsIgnoreCase(elenco_libri.get(i).getAutore())){
				
				cerca=elenco_libri.get(i);
				elencoserie.add(cerca);
			}
			else {
				String[] SerString = elenco_libri.get(i).getSerie().split(" ");
				String[] SerIns = serie.split(" ");
				int size1 = SerIns.length;
				int size2 = SerString.length;
				
				for(int n=0; n<size1; n++){
					for(int m=0; m<size2; m++){
					if(SerIns[n].equalsIgnoreCase(SerString[m])){
						cerca=elenco_libri.get(i);
						
						if(!(controllaInserimentoEL(cerca, elencoserie))){
							elencoserie.add(cerca);
						}
						
					}
					}
				}
			}
		}
		
		
		return elencoserie;
	}
	
	public void removeLibro(String titolo, String autore){
		
		boolean esiste = controllaLibrodaTitolo(titolo, autore);
		
		if(esiste==true){
			
			for(int i=0; i<elenco_libri.size(); i++){
				if(titolo.equalsIgnoreCase(elenco_libri.get(i).getTitolo()) && autore.equalsIgnoreCase(elenco_libri.get(i).getAutore())){
					elenco_libri.remove(i);
					System.out.println("Libro trovato ed eliminato!");
				}
			}
			
			
		}
		else {
			System.out.println("Il libro non appartiene a questa libreria!");
		}
		
		
	}
	
	public Libro vediLibro(String titolo, String autore){
		
		Libro trova = null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(titolo.equalsIgnoreCase(elenco_libri.get(i).getTitolo()) && autore.equalsIgnoreCase(elenco_libri.get(i).getAutore())){
				trova = elenco_libri.get(i);
			}
		}
		
		return trova;
	}
	
	public void rimuoviOggetto(Libro libro_rem){
		elenco_libri.remove(libro_rem);
		System.out.println("Il libro è stato rimosso!\n");
	}
	
	public void setIDCount(int id_new){
		this.id_count=id_new;
	}
	
	public int getId(){
		return id_count;
	}
	
	public void removeById(int id_rem){
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(id_rem==elenco_libri.get(i).getId()){
				elenco_libri.remove(i);
			}
		}
	}
	
	public Libro sceglidaId(int id){
		
		Libro scelta = null;
		
		for(int i=0; i<elenco_libri.size(); i++){
			if(id==elenco_libri.get(i).getId()){
				scelta = elenco_libri.get(i);
			}
		}
		
		return scelta;
	}

}
