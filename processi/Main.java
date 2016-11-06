/*     Progetto Lab3 
   classe Main per processi
   versione 1
   14/03/2002     */
import jcsp.lang.*;
public class Main {

  public static void main(String [] args){
      boolean errore = false;
      
      int num =Integer.parseInt(args[0]);
      int k = Integer.parseInt(args[1]);
      int x = 0;
      
      if (args.length != 2) 
        errore = true;
      else {
        try {
            num = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) 
	  {errore =true;}
      }
      
      if (!errore & ((num < 1)  || (k < 1)))
        errore =true;
      
      if (errore) {
        System.out.println("Errore: 2 parametri interi positivi: numero_piloti e giri");
	return;
      }
      System.out.println("-------------------------------------------------------------");
      System.out.println();
      System.out.println("Benvenuti alle prove del gran premio di formula 1!!!");
      if ((num==1)&&(k==1)){
	System.out.println("E' presente " + num +  "pilota che effettuerá " + k + " giro ");
      }
      else {
	if ((num>=1)&&(k==1)){
	  System.out.println("Sono presenti " + num + " piloti che effettueranno " + k + " giro ");
	}
	 else{
	   if ((num==1)&&(k>=1)){
	     System.out.println("E' presente " + num + " pilota che effettuerá " + k + " giri ");
	     }
	   else{   
	     System.out.println("Sono presenti " + num + " piloti che effettueranno " + k + " giri ciascuno");
	     
	   }
	 }
      }
      
      System.out.println();
      System.out.println("----------------------------------------------------------------");
      

      
      

      One2OneChannel chn [] =  One2OneChannel.create(num);
      System.out.println("Inizializzato l'array di canali...");
      System.out.println();
      CSProcess processi [] = new CSProcess[num+1];
      System.out.println();
      System.out.println("Inizializzato l'array di processi...");
      for (x=0; x<=num-1; x++){
	processi[x]= new Pilota(chn[x],x,k);
      }
      System.out.println();
      System.out.println("Ho creato n piloti...");
      processi[num] = new Direttore_di_Gara(chn);
      System.out.println();
      System.out.println("Ho creato il direttore di gara...");
      CSProcess parallelo = new Parallel(processi);
      System.out.println();
      System.out.println("Sto lanciando il comando parallelo...");
      parallelo.run();
  }
}
