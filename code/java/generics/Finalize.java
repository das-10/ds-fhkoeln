/***************************************************************/
/* Prof. Dr. Carsten Vogt                                      */
/* FH Koeln, Fak. 07 / Nachrichtentechnik                      */
/* http://www.nt.fh-koeln.de/vogt                              */
/*                                                             */
/* Das Programm illustriert die Methode finialize().           */
/***************************************************************/

import java.io.*;

class Konto {

 String inhaber;

 /* finalize() wird auf einem Objekt ausgefuehrt
    unmittelbar bevor der Garbage Collector das Objekt loescht. */

 protected void finalize() {
  System.out.println("Das " + inhaber + "-Konto wird geloescht.");
 }

}

public class Finalize {

 public static void main(String args[]) {

  Konto konto1 = new Konto();

  konto1.inhaber = new String("Schmitz");

  konto1 = null;

  /* Nach Zuweisung von null an konto1 ist das "Schmitz-Konto" nicht mehr
     zugreifbar und kann durch den Garbage Collector geloescht werden. */

  System.out.println();
  System.out.println("konto1 wurde der Nullzeiger zugewiesen.");

  /* aus Demonstrationszwecken: expliziter Aufruf des Garbage Collectors,
     damit das Objekt noch vor Programmende geloescht wird. Im allgemeinen
     wird bei der Programmierung auf diesen Aufruf verzichtet; dann ent-
     fernt der Garbage Collector das Objekt irgendwann spaeter - es sei denn,
     das Programm terminiert vorher. */
 
  System.gc();

 }

}
