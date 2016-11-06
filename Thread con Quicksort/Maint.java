

public class Maint
{
    public static void main(String[] args) {

       
      boolean errore = false;
        
      int n = 0;
      int k=0; 
        
      if (args.length != 2) 
          errore = true;
      else {
          try {
              n = Integer.parseInt(args[0]);
	      k = Integer.parseInt(args[1]);
          } catch (NumberFormatException e) 
              {errore =true;}
      }

      if (!errore & ((n < 1)  || (k < 1)))
          errore =true;

      if (errore) {
          System.out.println("Errore: 2 parametri interi positivi: numero_giocatori e casella_finale");
          return;
      }
      
      
      
      Gestione classifica = new Gestione(n,k);
                                      
      System.out.println("-------------------------------------------------------------");
      System.out.println();
      System.out.println("Benvenuti alle prove del gran premio di formula 1!!!");
      if ((n==1)&&(k==1)){
	    System.out.println("E' presente " + n +  "pilota che effettuerá " + k + " giro ");
      }
      else {
	 if ((n>=1)&&(k==1)){
	    System.out.println("Sono presenti " + n + " piloti che effettueranno " + k + " giro ");
	 }
	 else{
	     if ((n==1)&&(k>=1)){
	      System.out.println("E' presente " + n + " pilota che effettuerá " + k + " giri ");
	     }
	     else{   
		 System.out.println("Sono presenti " + n + " piloti che effettueranno " + k + " giri ciascuno");
		 
	     }
	 }
      }
      
      System.out.println();
      System.out.println("----------------------------------------------------------------");
  

  

        Thread piloti[] = new Thread [n];
     
        for (int i=0; i <= n-1; i++) {

	  piloti[i] = new PilotaT(i, k, classifica);
            piloti[i].start();
        }
    }
}

