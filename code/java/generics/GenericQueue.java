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

public class GenericQueue {

 public static void main(String args[]) throws IOException {

  BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

  int choice, maxEintraege;

  Queue<Integer> queue;

  // Erzeugung der Queue

  System.out.println();
  System.out.print("Gewuenschte maximale Anzahl von Eintraegen der Queue: ");
  maxEintraege = Integer.parseInt(in.readLine());

  queue = new Queue<Integer>(maxEintraege);

  if (queue!=null) {
     System.out.println();
     System.out.println("Queue erfolgreich erzeugt");
    }
   else {
     System.out.println();
     System.out.println("Erzeugung fehlgeschlagen");
     System.exit(-1);
    }

  // Schleife zur Durchfuehrung von Operationen auf der Queue

  do {

   System.out.println();
   System.out.println("Bitte waehlen:");
   System.out.println(" 1: Eintrag einfuegen");
   System.out.println(" 2: Eintrag ausfuegen");
   System.out.println(" 3: Queue voll?");
   System.out.println(" 4: Queue leer?");
   System.out.println(" 5: Queue-Inhalt ausgeben");
   System.out.println(" 0: Ende");
   do {
    choice = Integer.parseInt(in.readLine());
   } while (choice<0||choice>5);

   System.out.println();

   switch(choice) {

    case 0: break;

    case 1: System.out.println();
            System.out.print("Einzufuegender Eintrag: ");
            int wert_in = Integer.parseInt(in.readLine());
            if (queue.add(wert_in))
              System.out.println("--> Einfuegen erfolgreich");
             else
              System.out.println("--> Einfuegen fehlgeschlagen");
            break;

    case 2: Integer wert_out = queue.remove();
            if (wert_out!=null)
              System.out.println("--> Ausgefuegt: "+wert_out);
             else
              System.out.println("--> Ausfuegen fehlgeschlagen");
            break;

    case 3: if (queue.voll())
              System.out.println("--> Queue ist voll");
             else
              System.out.println("--> Queue ist nicht voll");
            break;

    case 4: if (queue.leer())
              System.out.println("--> Queue ist leer");
             else
              System.out.println("--> Queue ist nicht leer");
            break;

    case 5: System.out.println(queue.toString());
            break;

   }

  } while (choice!=0);

 }

}
