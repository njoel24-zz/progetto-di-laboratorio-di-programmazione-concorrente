//Direttore di gara
import jcsp.lang.*;

public class Direttore_di_Gara implements CSProcess {
    
  private AltingChannelInput input [] ;
  int n;
  Classifica aggiorna;
  int conta,pilota,giro=0;
  boolean continua=true;
  Integer giron = new Integer(0);
  Alternative alt;
  Guard g [];
    
  public Direttore_di_Gara (final AltingChannelInput in []  ){
    n=in.length;  
    aggiorna = new Classifica(n); //creo l'istanza di Classifica
    input= new AltingChannelInput[n]; 
    
    for(int i=0; i<n; i++){ //inizializzo il vettore input  di tipo AltingChannelInput con i valori passati dal Main 
      this.input[i]=in[i];
    }   
    g = new Guard[n];
      
    for (int f = 0; f <=n-1; f++){ //inizializzo il vettore delle guardie con le istanze di tipo AltingChannelInput
      g[f] = input[f]; 
    }
    alt=new Alternative(g); //inizializzo alt con il vettore delle guardie
  }
  

  public void run(){
   
    while(continua){
      pilota =  alt.select();
      giron = (Integer)  input[pilota].read();
      giro= giron.intValue();
	  
      if(giro>=0) 
	aggiorna.stampaclassifica(pilota, giro);
      else
	conta++;
	  
      if(conta==n){
	continua=false;
	aggiorna.classificafinale();
      }
    }
  }
}    






















