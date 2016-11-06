//Progetto lab3
//classe Classifica per processi

public class Classifica {
  //algoritmo di ordinamento, lo stesso dell'implementazione con i Thread
  int temp, temp2, minuti,secondi,x=0;
  int n;
  int Ranking [][];

  public Classifica (int num){ //costruttore
    this.n=num;
    Ranking = new int [2][n]; //inizializzo l'array 
    for (int x=0; x<=n-1; x++){
	Ranking[0][x]=x; //piloti
	Ranking[1][x]=333; //tempi
    }
}
  
     void stampaclassifica(int pilot, int g){
       x=0;
       
	while (pilot!= Ranking[0][x]){
	  x++;
	}
	Ranking[1][x]=g;
	
	for (int y=x-1; y>=0; y--){
	  if(Ranking[1][y]<= Ranking[1][x]){
	    y=0;
	  }
	  else{
	    temp=Ranking[1][x];;
	    temp2=Ranking[0][x];
	    Ranking[1][x] = Ranking[1][y];
	    Ranking[0][x] = Ranking[0][y];
	    Ranking[1][y]=temp;
	    Ranking[0][y]=temp2;
	    x=y;
	  }
	
	}
	System.out.println();
	System.out.println("La classifica aggiornata:");
	System.out.println();
	for (int z=0; z<=n-1; z++){
	  
	  minuti =  Ranking[1][z]/60;
	  secondi = Ranking[1][z] % 60;
	  
	  System.out.println("Pos.: " +(z+1) +  " |  Pilota " +  Ranking[0][z] +  " | tempo: " + minuti + " : "+ secondi);
	}
    }
  
    void classificafinale(){
      if (n !=1){
	System.out.println("Il vincitore della corsa é il pilota "  + Ranking[0][0]); 
	System.out.println("-----------------La classifica finale---------------------");
	System.out.println("-----------------------------------------------------------");
	for (int z=0; z<=n-1; z++){
	  
	  minuti =  Ranking[1][z]/60;
	  secondi = Ranking[1][z] % 60;
	  
	  System.out.println("Pos.: " +(z+1) +  " |  Pilota " +  Ranking[0][z] +  " | tempo: " + minuti + " : "+ secondi);
	}
	System.out.println("-----------------------------------------------------------");
	
      }
      else
	minuti =  Ranking[1][0]/60;
        secondi = Ranking[1][0] % 60;
      
      System.out.println("Il pilota  "+ Ranking [0][0] + " ha terminato i suoi giri con il tempo migliore: "  + minuti + " : "+ secondi ); 
    }
}
