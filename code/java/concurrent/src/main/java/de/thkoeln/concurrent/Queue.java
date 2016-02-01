package de.thkoeln.concurrent;

// Definition der Queue-Klasse

public class Queue<T> {
 
  private T[] inhalt = null;         
  private int laenge,leseIndex,schreibIndex;  
                            
@SuppressWarnings("unchecked")
Queue(int maxAnzahlEintraege){
    laenge = maxAnzahlEintraege+1;
    inhalt = (T[]) new Object[laenge];
    leseIndex = schreibIndex = 0;
   }

  public boolean leer() {
   // Die Queue ist leer, wenn Lese- und Schreibindex auf dieselbe Position zeigen.
   return schreibIndex==leseIndex;
  }

  public boolean voll() {
   // Die Queue ist voll, wenn nach einer Schreiboperation der Schreibindex auf dieselbe Position
   // wie der Leseindex verweisen w�rde.
   return (schreibIndex+1)%laenge==leseIndex;
  }

  public boolean add(T o) { // Hinzuf�gen eines neuen Eintrags an das Queue-Ende
   if (voll())
    return false;
   inhalt[schreibIndex] = o;
   schreibIndex = (schreibIndex+1)%laenge;
   return true;
  }

  public T remove() { // Entfernen der Eintrags von der Queue-Spitze
   if (leer())
    return null;
   T erg = inhalt[leseIndex];
   leseIndex = (leseIndex+1)%laenge;
   return erg;
  }

  public String toString() { // Hilfsmethode zur Ausgabe des Queueinhalts
   String erg = new String();
   int i = leseIndex;
   while(i!=schreibIndex) {
    erg += inhalt[i] + " ";
    i = (i+1)%laenge;
   }
   return erg;
  }

}



