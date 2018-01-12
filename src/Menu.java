import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import my.lib.InputDati;

public class Menu {

	//variabili e costanti
		private String msgBenvenuto ="Buongiorno e benvenuto nella libreria!";
		public static final String MSG_SCELTA = "1)Inserisci un nuovo libro\n2)Visualizza il contenuto dell’intera collezione\n3)Ricerca da titolo\n4)Ricerca da autore\n5)Ricerca per serie\n6)Rimuovi un libro\n7)Esci dal programma\n->";
		public static final String MSG_MODLIB = "1)Modifica luogo\n2)Aggiungi copia\n3)Modifica titolo\n4)Rimuovi libro\n5)Torna su\n->";
		public static final String NO_SERIE="-Non appartiene ad una serie-";
		public static final String NO_SOTTO="-Non ha un sottotitolo-";
		//costruttore
		public Menu(){
			
		}
		
		//metodi
		public void scelta(ElencoLibri elenco){
			
			
			int scelta;
			 String nome = null;
			 File file = null ;
			 
			int azione =InputDati.leggiIntero("1)Apri file esistente\n2)Crea nuovo file\n-> ", 1,2);
			
			switch(azione) {
			
				case 1:{
					JFileChooser chooser = new JFileChooser("/home/mari/eclipse-workspace/Libreria-master");
					Scanner in =null;
					if (chooser.showOpenDialog(chooser)==JFileChooser.APPROVE_OPTION){
						file = chooser.getSelectedFile();
					}
					
					nome =file.getName();
					
					if (file.exists()){
			            System.out.println("Il file " + nome + " esiste.");
			            try {
							leggiFile(file, elenco);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
			            
			            
			        } else
						try {
							if (file.createNewFile()){
							    System.out.println("Il file " + nome + " è stato creato.");   
							}
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
			        break;
				}
				
				case 2:{
					
					nome = InputDati.leggiStringaNonVuota("Inserisci il nome del file da creare: ");
					file = new File(nome);
					 
			        if (file.exists()){
			            System.out.println("Il file " +nome + " esiste.");
			            try {
							leggiFile(file, elenco);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
			            
			            
			        } else
						try {
							if (file.createNewFile()){
							    System.out.println("Il file " + nome + " è stato creato.");   
							}
						} catch (IOException e1) {
							
							e1.printStackTrace();
						}
			        
			   
			
				}
			}
			      
			 
			
			
			do{
				System.out.println("Cosa vuoi fare ?\n");
				scelta = InputDati.leggiIntero(MSG_SCELTA,1 , 7);
						
				switch(scelta){
				
				case 1:{
					//nuovo libro
					elenco.addLibro();
					
					//salvataggio
					try {
						scriviFile(elenco, nome);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					break;
				}
				
				
				case 2:{
					//vedi tutti i libri
					elenco.vediLibri();
					break;
				}
				
				case 3:{
					//ricerca da titolo
					String titololibro = InputDati.leggiStringaNonVuota("Inserisci il titolo del libro da cercare (senza sottotitolo) : ");
					String andor;
					
					do{
					andor= InputDati.leggiStringaNonVuota("Il titolo deve contenere almeno una parola o tutte? (almeno una/tutte) \n-> ");
					}while(!( andor.equalsIgnoreCase("tutte")||andor.equalsIgnoreCase("almeno una")));
					
					if(andor.equalsIgnoreCase("almeno una")){
						ArrayList<Libro> stampa = elenco.ricercaDaTitoloOR(titololibro);
						
						if(stampa.size()!=0){
							
							System.out.println("I libri che presentano almeno una delle parole \""+titololibro+"\" nel titolo presenti nella libreria sono:");
						
							for(int i=0; i<stampa.size(); i++){
								if(stampa.get(i).getSottotitolo().equalsIgnoreCase(NO_SOTTO)){
									System.out.printf("%-6d%-40s                    di %-20s\t%-40s\n",stampa.get(i).getId(), stampa.get(i).getTitolo(),stampa.get(i).getAutore(), stampa.get(i).getLuogo());
									
									}
								else{
									System.out.printf("%-6d%-40s,%-20s di %-20s\t%-40s\n",stampa.get(i).getId(),stampa.get(i).getTitolo(),stampa.get(i).getSottotitolo(),stampa.get(i).getAutore(), stampa.get(i).getLuogo());
									}
								}
							
								analisiscelta(elenco);
						}
						else{
							System.out.println("Non sono presenti libri che presentano queste parole nel titolo nella libreria.");
						}
							
					}
					else if(andor.equalsIgnoreCase("tutte")){
						ArrayList<Libro> stampa = elenco.ricercaDaTitoloAND(titololibro);
						
						if(stampa.size()!=0){
							
							System.out.println("I libri che presentano nel titolo queste parole \""+titololibro+"\" presenti nella libreria sono:");
						
							for(int i=0; i<stampa.size(); i++){
								if(stampa.get(i).getSottotitolo().equalsIgnoreCase(NO_SOTTO)){
									System.out.printf("%-6d%-40s                    di %-20s\t%-40s\n",stampa.get(i).getId(), stampa.get(i).getTitolo(),stampa.get(i).getAutore(), stampa.get(i).getLuogo());
									
									}
								else{
									System.out.printf("%-6d%-40s,%-20s di %-20s\t%-40s\n",stampa.get(i).getId(),stampa.get(i).getTitolo(),stampa.get(i).getSottotitolo(),stampa.get(i).getAutore(), stampa.get(i).getLuogo());
									}
								}
							analisiscelta(elenco);
						}
						else{
							System.out.println("Non sono presenti libri che presentano queste parole nel titolo nella libreria.");
						}
							
					}
					
					break;
				}
					
				case 4:{
					//ricerca da autore
					String autorelibro = InputDati.leggiStringaNonVuota("Inserisci l'autore di cui si vuole avere un elenco di libri: ");
					ArrayList<Libro> stampa = elenco.ricercaDaAutore(autorelibro);
					
					
					if(stampa.size()!=0){
						
						System.out.println("I libri di "+autorelibro+" presenti nella libreria sono:");
					
						for(int i=0; i<stampa.size(); i++){
								if(stampa.get(i).getSottotitolo().equalsIgnoreCase(NO_SOTTO)&&stampa.get(i).getSerie().equalsIgnoreCase(NO_SERIE)){
									System.out.printf("%-6d                    %-40s                    \t%-40s\n", stampa.get(i).getId(),stampa.get(i).getTitolo(),stampa.get(i).getLuogo());
									}
								else if(stampa.get(i).getSottotitolo().equalsIgnoreCase(NO_SOTTO)){
									System.out.printf("%-6d%-20s%-40s                    \t%-40s\n", stampa.get(i).getId(),stampa.get(i).getSerie(), stampa.get(i).getTitolo(),stampa.get(i).getLuogo());
									}
								else if(stampa.get(i).getSerie().equalsIgnoreCase(NO_SERIE)){
									System.out.printf("%-6d                    %-40s %-20s\t%-40s\n",stampa.get(i).getId(), stampa.get(i).getTitolo(),stampa.get(i).getSottotitolo(),stampa.get(i).getLuogo());
								}
								else{
									System.out.printf("%-6d%-20s%-40s%-20s\t%-40s\n", stampa.get(i).getId(),stampa.get(i).getSerie(), stampa.get(i).getTitolo(),stampa.get(i).getSottotitolo(), stampa.get(i).getLuogo());
								}
							}
						
						analisiscelta(elenco);
					
					}
					else{
						
						System.out.println("Non sono preseti libri di questo autore nella libreria.");
					}
						
					
					
					
					break;
				}
				
				case 5:{
					//ricerca da serie
					String serielibro = InputDati.leggiStringaNonVuota("Inserisci la serie di cui si vuole avere un elenco di libri: ");
					ArrayList<Libro> stampa = elenco.vediSerieLibro(serielibro);
					
						if(stampa.size()!=0){
							System.out.println("I libri della serie \""+serielibro+"\" presenti nella libreria sono:");
							
							for(int i=0; i<stampa.size(); i++){
								if(stampa.get(i).getSottotitolo().equalsIgnoreCase(NO_SOTTO)){
									System.out.printf("%d %-40s                    \t%-40s\n",stampa.get(i).getId(), stampa.get(i).getTitolo(), stampa.get(i).getLuogo());
								}
								else{
									System.out.printf("%d %-40s%-20s\t%-40s\n",stampa.get(i).getId(), stampa.get(i).getTitolo(),stampa.get(i).getSottotitolo(), stampa.get(i).getLuogo());
								}							
							
							}
							
							analisiscelta(elenco);

							
						}
						else{
							System.out.println("Non sono preseti libri di questa serie nella libreria.");
						}
							
						
					
					break;
				}
				
				case 6:{
					//rimuovi libro
					String risposta = InputDati.leggiStringaNonVuota("Preferisci inserire il titolo o l'id?\n-> ");
					
					if(risposta.equalsIgnoreCase("titolo")){
						String titolo = InputDati.leggiStringaNonVuota("Inserisci il titolo del libro da togliere: ");
						String autore = InputDati.leggiStringaNonVuota("Inserisci l'autore del libro da togliere: ");
						
						elenco.removeLibro(titolo, autore);
					}
					else if(risposta.equalsIgnoreCase("id")){
						
						int id = InputDati.leggiIntero("Inserisci id: ", 1, elenco.numeroLibri());
						
						System.out.println("Il libro da eliminare è: "+elenco.sceglidaId(id).getTitolo());
						
						elenco.removeById(id);
						System.out.println("Libro trovato ed eliminato!");
					}
					else {
						System.out.println("Richiesta non valida, inserisci o titolo o id!");
					}
					//salvataggio
					try {
						scriviFile(elenco, nome);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					break;
				}
				
				case 7:{
					//esci
					
					try {
						scriviFile(elenco, nome);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					
					System.out.println("Arrivederci!!");
					break;
				}

				}
				
				
			}while(scelta!=7);
			
		}
		
		 public void leggiFile(File in, ElencoLibri elenco) throws FileNotFoundException{
			
			Scanner input = new Scanner(in);
			input.useDelimiter("\n"); 
			int size =input.nextInt();
			int ultimo_id = input.nextInt();
			
			elenco.setIDCount(ultimo_id);
			
			for(int n=0; n<size; n++){
				
				String titolo = input.next(); 
				String sottot = input.next();
				String autore = input.next();
				String genere = input.next();
				String serie = input.next();
				String luogo = input.next();
				int copie = input.nextInt();
				int id = input.nextInt();
				
				Libro nuovoL = new Libro(titolo,sottot, autore, genere, serie,luogo, copie, id);
				elenco.addLibroS(nuovoL);
			
			}
			input.close();
			
		}
		
		
		public void scriviFile(ElencoLibri elenco, String nome) throws FileNotFoundException{
			
			PrintWriter out = new PrintWriter(nome);
			out.println(elenco.numeroLibri());
			out.println(elenco.getId());
			
			ArrayList<Libro> elencolibri = elenco.vediElencoL();
			
			int tot = elencolibri.size();
			
			for(int i=0; i<tot; i++){
				
				out.println(elencolibri.get(i).getTitolo());
				out.println(elencolibri.get(i).getSottotitolo());
				out.println(elencolibri.get(i).getAutore());
				out.println(elencolibri.get(i).getGenere());
				out.println(elencolibri.get(i).getSerie());
				out.println(elencolibri.get(i).getLuogo());
				out.println(elencolibri.get(i).getCopie());
				out.println(elencolibri.get(i).getId());
				
			}
			
			
			out.close();
			
		}
		
		 
		public void modificaLibro(Libro oggetto, ElencoLibri elenco){
			
			
			System.out.println("Titolo: "+oggetto.getTitolo()+", "+oggetto.getSottotitolo());
			System.out.println("Autore: "+oggetto.getAutore());
			System.out.println("Serie: "+oggetto.getSerie());
			System.out.println("Genere: "+oggetto.getGenere());
			System.out.println("Luogo: "+oggetto.getLuogo());
			System.out.println("N° copie: "+oggetto.getCopie());
			System.out.println("Id: "+oggetto.getId());
			
			int scelta_lib = 0;
			
			do{
				
				scelta_lib=InputDati.leggiIntero(MSG_MODLIB, 1, 5);
				
				switch(scelta_lib){
				
				case 1:{
					//modifica luogo
					String luogonew=InputDati.leggiStringaNonVuota("Inserisci il nuovo luogo: ");
					oggetto.modLuogo(luogonew);
					break;
				}
				
				case 2:{
					//aggiungi copia
					oggetto.addCopie();
					System.out.println("Copia aggiunta!");
					break;
				}
				
				case 3:{
					//modifica titolo
					String titolonew=InputDati.leggiStringaNonVuota("Inserisci il nuovo titolo: ");
					oggetto.modTitolo(titolonew);
					break;
				}
				
				case 4:{
					//rimuovi libro
					elenco.rimuoviOggetto(oggetto);
					
				}
				case 5 :{
					//Menù precedente
					break;
				}
				
				}
				
				
				
			}while(scelta_lib!=5 && scelta_lib!=4);
			
		}
		
		
	
	public void analisiscelta(ElencoLibri elenco) {
		
		String risp= InputDati.leggiStringaNonVuota("Vuoi sceglierne uno? (si/no)\n-> ");
				
		if(risp.equalsIgnoreCase("si")){
			int id_scelto=0;
			Libro scelto=null;
			
			do{
				id_scelto = InputDati.leggiIntero("Inserisci id: ", 1, elenco.numeroLibri());
				scelto = elenco.sceglidaId(id_scelto);
				
				if(scelto == null){
					System.out.println("Attenzione id inserito non esistente o errato");
				}
				else {
					modificaLibro(scelto, elenco);
				}
			}while(scelto==null);
		}	
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
