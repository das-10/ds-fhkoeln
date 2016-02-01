/***************************************************************/
/* Prof. Dr. Carsten Vogt                                      */
/* FH Koeln, Fak. 07 / Nachrichtentechnik                      */
/* http://www.nt.fh-koeln.de/vogt                              */
/*                                                             */
/* Beispielprogramm Nr. 40a                                    */
/* der frueheren Vorlesung Datenverarbeitung                   */
/*                                                             */
/* Das Programm vergleicht binaere und formatierte Datei-      */
/* ausgabe.                                                    */
/***************************************************************/

#include <stdio.h>
//#include <conio.h>

main() {

 FILE *datei_format,  /* Zeiger fuer Datei, in die formatiert
                         ausgegeben wird */
      *datei_binaer;  /* Zeiger fuer Datei, in die binaer
                         ausgegeben wird */
 int i;               /* Laufindex */

 /* Oeffnen der Dateien*/

 datei_format = fopen("aus_form.txt","w");
 datei_binaer = fopen("aus_bin.txt","wb");

 /* Ausgabe der ganzen Zahlen von 16384 bis 16393: formatiert */

 for (i=16384;i<16394;i++)
  fprintf(datei_format,"%d ",i);

 /* Ausgabe der ganzen Zahlen von 16384 bis 16393: binaer */

 //printf("Size of INT:%d\n",sizeof(int));
 for (i=16384;i<16394;i++)
  fwrite(&i,sizeof(int),1,datei_binaer);

 /* Schliessen der Dateien */

 fclose(datei_format);
 fclose(datei_binaer);

 printf("Und jetzt bitte mal mit dem Editor die Dateien\n");
 printf("ausg_form.txt und ausg_bin.txt anschauen!\n");
 printf("(Lesbarkeit? Anzahl der Eintraege?)\n");
 //getch();

}
