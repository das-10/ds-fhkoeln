/***************************************************************/
/* Prof. Dr. Carsten Vogt                                      */
/* FH Koeln, Fak. 07 / Nachrichtentechnik                      */
/* http://www.nt.fh-koeln.de/vogt                              */
/*                                                             */
/* Die Methode getProperties() liefert wichtige Informationen  */
/* �ber das darunterliegende System.                           */
/* Mit getProperty() kann man eine bestimmte Information       */
/* erfragen.                                                   */
/***************************************************************/

public class Properties {

 public static void main(String args[]) {

  System.getProperties().list(System.out);

  System.out.println();
  System.out.println("*******************************");
  System.out.println();

  System.out.println("Java-Version: "+System.getProperty("java.version"));
  System.out.println();
  System.out.println("Betriebssystem: "+System.getProperty("os.name"));
  System.out.println("BS-Architektur: "+System.getProperty("os.arch"));
  System.out.println("BS-Version: "+System.getProperty("os.version"));
  System.out.println();
  System.out.println("Benutzername: "+System.getProperty("user.name"));
  System.out.println("Home Directory: "+System.getProperty("user.home"));
  System.out.println("Arbeitsverzeichnis: "+System.getProperty("user.dir"));

 }

}
