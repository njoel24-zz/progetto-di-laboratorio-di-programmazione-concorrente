/*     Progetto Lab3 
   classe Pilota per Thread
   versione 1
   Autore: Cristiano Anselmi 
   login: anselmi
   matricola: 226847
       */
import java.util.Random; //classe del jdk per la generazione di numeri casuali

public class PilotaT extends Thread {

  boolean controllofinale=false;  
  int tempomigliore = 121;
  int nome, k;
  int contagiri = 0;
  Gestione c; //istanza del monitor
  int giro, minuti, secondi=0;    

  public PilotaT (int n, int kappa, Gestione ck) { //costruttore
    this.nome= n;
    k= kappa;
    this.c=ck; //inizializzo l'istanza di Gestione con i valori impostati nel main
  }            
    
  public void run() {
	
    while(contagiri<k) { //il pilota fa k giri
      
      controllofinale = c.controllo_ultimo();  //controllo se é l'ultimo
	    
      if(controllofinale==false){   //non é l'ultimo
	Random numero = new Random(); 
	int pitstop=  numero.nextInt(2000)+3000;
	//deschedulo il Thread per il pitstop
	
	try{
	  
	  Thread.sleep(pitstop);
	    	
	}
	
		catch (InterruptedException e){} //in caso di fallimento della sleep viene lanciata un eccezione del tipo InterruptedException
	
	contagiri++; //il pilota é pronto per il giro
	System.out.println("Il Pilota " + nome + " e' pronto per fare il giro : " + contagiri);
	System.out.println();
	giro = (numero.nextInt(40)+80);
	minuti = (int) giro/60; //calcola i minuti
	secondi = giro % 60;   //calcola i secondi
	
	System.out.println();
	for (int i=1; i<=(giro/2); i++){
	  System.out.print("-");
	}
	System.out.print(">");
	System.out.println();
		
	if(giro<tempomigliore){
	  tempomigliore=giro; //aggiorno tempomigliore
	  System.out.println();
	  System.out.println("Il pilota: " + nome + " ha migliorato il suo tempo!!!!:  " + minuti + " : " + secondi);
	  System.out.println();
	  c.aggiornaclassifica(nome,giro); //aggiorna la classifica    
	}
	
		else{
		  System.out.println();
		  System.out.println("Il pilota: " + nome + " termina il giro " +contagiri + " non migliorando il suo tempo");
		  System.out.println();
		}
      }
      
      else{
	if ((tempomigliore < (85+nome)) &&( c.primo(nome)==true) )  {  //controlla il suo record personale e se é primo
	  minuti = (85+nome)/60;
	  secondi = (85+nome)%60;
	  System.out.println ("Il pilota " + nome + "non continua la corsa visto che il suo record precedente era di: " + minuti + " : " + secondi); 
	  contagiri=k; //smette di girare
	}
	else{
	  c.fcpz();  //continua a girare
	}
	    }
    }
	
    System.out.println();    
    System.out.println("Il pilota   "+ nome + " ha terminato i giri a sua disposizione");
    System.out.println();
    c.fine_corsa(); //aggiorna il contatore dei piloti che hanno terminato
    
    if (c.controllo_ultimo() == true) 
      c.classificafinale(); // stampa la classifica finale quando ha finito ed é l'ultimo
  }
}



