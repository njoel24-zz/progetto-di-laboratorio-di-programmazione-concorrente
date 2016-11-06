public class Gestione {
  
  private int temp=0; //variabili temporanee
  private int temp2=0;
  int n; //numero dei piloti
  static private int Ranking [][];  
  int minuti, secondi,z, fine_corsa_pilota =0;  

  public Gestione (int enne, int kappa)
  {
    this.n=enne;
    Ranking  = new int [2][n]; 
    
    for (int x=0; x<=n-1; x++){ //inizializzo la matrice dandoli un ordine
      Ranking[0][x]=x; //piloti
      Ranking[1][x]=333; //tempi
    }
  }
  
  

    public synchronized void aggiornaclassifica (int pilot, int g)
    {
      //algoritmo di ordinamento

      System.out.println();
      System.out.println("E' appena avvenuto un aggiornamento in classifica.....provvedo a riordinarla");
      System.out.println();

      int x=0; //puntatore al pilota nella classifica da aggiornare
	
      while (pilot!= Ranking[0][x]){ //trovo il pilota all'interno del vettore dei piloti, n nel caso peggiore
	x++;
      }
	
      Ranking[1][x]=g; //aggiorno il tempo
      
      for (int y=x-1; y>=0; y--){ //scorro l'array
	if(Ranking[1][y]<= Ranking[1][x]){ //controllo se il tempo del pilota x-1 é piú piccolo di x
	  y=0; //se si finisco . L'array é preventivamente ordinato
	}
	else{
	    //scambio il pilota e il tempo x-1 con x servendomi delle variabili temporanee temp e temp2
	  temp=Ranking[1][x];;
	  temp2=Ranking[0][x];
	  Ranking[1][x] = Ranking[1][y];
	  Ranking[0][x] = Ranking[0][y];
	  Ranking[1][y]=temp;
	  Ranking[0][y]=temp2;
	  x=y; //scalo x di una posizione 
	}
	
      }
	System.out.println();
	//stampo la classifica
	for (int z=0; z<=n-1; z++){
	  minuti= Ranking[1][z]/60; 
	    secondi=  Ranking[1][z]%60;
	    System.out.println("Pos.:  " +(z+1) +  " ||   Pilota   " + Ranking[0][z] +   " ||  tempo : " + minuti + " : " + secondi);
	}
	System.out.println();
	
	if (x==0){ //controllo se il pilota é in testa
	  // a paritá di primi tempi si ferma ., cioé é primo, chi ha girato per primo
	    System.out.println();
	    System.out.println("Il pilota "+pilot+" é primo in classifica!");
	    System.out.println();
	    notify(); // sveglio il precedente primo in classifica
	    System.out.print("e si ferma......");
	    System.out.println();
	    System.out.println();
	    if (n!=1){ //se c'é un solo pilota non eseguo la wait per evitare lo stallo e la notify va persa.
	      try { wait(); } catch (InterruptedException e) {} //e mi sospendo , la wait la eseguo solo alla fine del metodo
	    }
	}
	
	
    }
  
  public void classificafinale(){ //il metodo non é sincronizzato perché lo esegue un solo processo
 	if (n!=1){ 
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("Il vincitore della corsa é il pilota :  "+Ranking[0][0] + "!!!");
	System.out.println("---------------Classifica finale---------------");
	for ( z=0; z<=n-1; z++){
	    minuti= Ranking[1][z]/60; 
	    secondi=  Ranking[1][z]%60;
	    System.out.println("Pos.:   "+(z+1)+   "|| Pilota  "+ Ranking[0][z]+   " ||  tempo:" + minuti + " : " + secondi);
	}
	System.out.println("------------------------------------------------");
    }
	else{ // nel caso di un solo pilota 
	     minuti= Ranking[1][z]/60; 
	    secondi=  Ranking[1][z]%60 ;
	    System.out.println("Fine delle prove per il pilota " + Ranking[0][0] + " che ha raggiunto il tempo " + minuti + ":" + secondi);  
	}
    }

  public synchronized void fine_corsa (){
      //quando un pilota finisce i suoi giri viene incrementata la variabile fine_corsa_pilota 
    fine_corsa_pilota++;
    
    if (fine_corsa_pilota == n-1){
	notify(); //il penultimo pilota che finisce i suoi giri  sveglia il primo in classifica
    }
    
  }

    public synchronized boolean controllo_ultimo (){
	if (fine_corsa_pilota == n-1){  //controlla se é l'ultimo
	 return true;
       }
       return false;
    }


    public void fcpz (){
	// invocato dall'ultimo pilota che vuole continuare, il valore é -1 per includere il caso in cui il numero dei piloti é 1, non c'é sincronizzazione
	fine_corsa_pilota=-1;
    }

    public boolean primo (int nom) {
	//metodo booleano che restituisce true se l'ultimo pilota é primo, non c'é bisogno di sincronizzazione
	if(nom == Ranking[0][0])
	    return true;
	else
	    return false;
    }
    
}





   
    

 
