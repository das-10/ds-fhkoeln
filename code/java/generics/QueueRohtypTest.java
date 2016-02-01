/***************************************************************/
/* Prof. Dr. Carsten Vogt                                      */
/* FH Koeln, Fak. 07 / Nachrichtentechnik                      */
/* http://www.nt.fh-koeln.de/vogt                              */
/*                                                             */
/* Das Programm zeigt die Implementation einer Queue (= Warte- */
/* schlange mit FIFO-Zugriffsreihenfolge). Die Queue wird mit  */
/* Hilfe einer generischen Klasse realisiert. Diese generische */
/* Klasse erlaubt es, Queues zur Speicherung von Objekten be-  */
/* liebiger Klassen zu erzeugen. Allerdings muss bei der Erzeu-*/
/* gung einer Queue festgelegt werden, welcher Klasse die in   */
/* ihr gespeicherten Objekte angeh�ren m�ssen, und sie kann    */
/* dann nur Objekte dieser Klasse aufnehmen.                   */
/***************************************************************/

import java.io.*;

// Definition der Queue-Klasse

class Queue<T> {
 
  private T[] inhalt = null;
                             // Array zur Speicherung der Eintr�ge der Queue.
                             // Der Array ist als "Ringpuffer" organisiert: Zwei Indizes (s.u.) bezeichnen
                             // die Arraypositionen, an denen der n�chste Lese- bzw. Schreibzugriff stattfindet.
                             // Ein Index wird nach einer Lese- bzw. Schreiboperation um 1 erh�ht und dabei
                             // auf 0 zur�ckgesetzt, wenn das Arrayende erreicht ist.

  private int laenge,        // L�nge des Arrays (= maximale Anzahl von Eintr�gen + 1). Eine Position bleibt
                             // stets leer, um die F�lle "Queue voll" und "Queue leer" unterscheiden zu
                             // k�nnen (siehe unten).
 
              leseIndex,     // An der Arrayposition 'leseIndex' findet der n�chste Lesezugriff statt.

              schreibIndex;  // An der Arrayposition '(schreibIndex+1)%laenge'
                             // findet der n�chste Lesezugriff statt.

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

// Hauptprogramm

class QueueRohtypTest {

 public static void main(String args[]) throws IOException {

  Queue<String> qs;
  Queue q;
  qs = new Queue<String>(5);

//  qs.add(4711);
//  System.out.println(qs.toString());
//  System.out.println();

  q = qs;
  q.add(4711);
  System.out.println(q.toString());
  System.out.println();

  q = new Queue(5);
  qs = q;

 }

}