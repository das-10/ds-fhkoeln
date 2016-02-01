/***************************************************************/
/* Prof. Dr. Carsten Vogt                                      */
/* FH Koeln, Fak. 07 / Nachrichtentechnik                      */
/* http://www.nt.fh-koeln.de/vogt                              */
/*                                                             */
/* Das Programm zeigt die Implementation einer Queue (= Warte- */
/* schlange mit FIFO-Zugriffsreihenfolge). Die Queue wird mit  */
/* Hilfe eines Arrays des Typs Object[] realisiert, so dass in */
/* ihr Objekte beliebiger Klassen (auch unterschiedlicher Klas-*/
/* sen durchmischt) abgelegt werden k�nnen.                    */
/* Bei dieser Implementation wird keine generische Klasse be-  */
/* nutzt.                                                      */ 
/***************************************************************/

import java.io.*;

// Definition der Queue-Klasse

class Queue {
 
  private Object[] inhalt = null;
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
    inhalt = new Object[laenge];
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

  public boolean add(Object o) { // Hinzuf�gen eines neuen Eintrags an das Queue-Ende
   if (voll())
    return false;
   inhalt[schreibIndex] = o;
   schreibIndex = (schreibIndex+1)%laenge;
   return true;
  }

  public Object remove() { // Entfernen der Eintrags von der Queue-Spitze
   if (leer())
    return null;
   Object erg = inhalt[leseIndex];
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

public class NotGenericQueue {

 public static void main(String args[]) throws IOException {

  Queue queue = new Queue(5);

  queue.add("Hallo");
  queue.add(4711);
  queue.add(1.23);
  queue.add('c');

  System.out.println();
  System.out.println(queue.toString());

 }

}
