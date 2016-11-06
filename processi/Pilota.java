/*     Progetto Lab3 
   classe Pilota per processi
   versione 1
     */

import jcsp.lang.*; 
import java.util.Random;

class Pilota implements CSProcess{
    
  int tempomigliore = 1000;
  ChannelOutput out1;  
  int nome;
  int k;
  boolean continua = true;
  Random numero = new Random(); //istanza della classe Random per la generazione di numeri causali
  int contagiri,pitstop, giro,i, minuti,secondi=0;
 
  public Pilota (ChannelOutput o1, int n, int kappa ){
    this.out1= o1;
    this.nome= n;
    k= kappa;
  }            

  public void run(){
	
    while(continua){
	    
      pitstop=  numero.nextInt(2000)+3000; //pitstop
      System.out.println("Il pilota " + nome + "va al garage......");
	    
      try {
	Thread.sleep(pitstop);
      }
      catch (InterruptedException e){}
	   
      contagiri++; //il pilota é pronto per il giro

      if (contagiri<=k){
	System.out.println("Il Pilota"+nome +"e' pronto per fare il giro:" + contagiri);
	System.out.println();
	       
	giro = (numero.nextInt(20)+80);
	minuti =  giro/60;
	secondi = giro % 60;
	       
	System.out.println();
	for ( i=1; i<=(giro/2); i++){ //simulo la corsa
	  System.out.print("-");
	}
	       
	System.out.print("->");
	System.out.println();
	    
	if(giro<tempomigliore){
	  tempomigliore=giro;
	  System.out.println();
	  System.out.println("Il pilota:"+nome+"ha migliorato il suo tempo!!!!: "+minuti+ " : "+secondi);
	  System.out.println();
	  System.out.println("Il pilota:"+nome+"ha terminato il giro"+contagiri);
	  System.out.println();
	  out1.write(new Integer(tempomigliore));
	}
	else{
	  System.out.println();
	  System.out.println("Il pilota:"+nome+"termina il giro "+contagiri+ " non migliorando il suo tempo");
	  System.out.println();
	}
      }
      else {
	continua=false;
	System.out.println();
	System.out.println("il pilota  "+nome+ "ha terminato i giri a sua disposizione");
	System.out.println();
	out1.write(new Integer(-777));
      }
      
    }

  }
}
