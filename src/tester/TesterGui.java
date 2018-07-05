package tester;

import gui.finestre.FinestraLogin;
import locale.GestoreLocale;
import locale.Tavolo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TesterGui {
    public static void main(String[] args) {

        Tavolo tav1 = new Tavolo("da Giulio","tav1",4);
        Tavolo tav2 = new Tavolo("da Giulio","tav2", 6);
        Tavolo tav3 = new Tavolo("da Giulio","tav3",8);

        ArrayList<Tavolo> listaTavoli = new ArrayList <Tavolo>();
        listaTavoli.add(tav1);
        listaTavoli.add(tav2);
        listaTavoli.add(tav3);

        GregorianCalendar orarioapertura = new GregorianCalendar();
        orarioapertura.add(GregorianCalendar.HOUR,9);
        GregorianCalendar chiusura = new GregorianCalendar();
        chiusura.add(GregorianCalendar.HOUR,18);
        GregorianCalendar orarioEvento = new GregorianCalendar();
        orarioEvento.add(GregorianCalendar.HOUR, 12);
        orarioEvento.add(GregorianCalendar.MINUTE, 30);
        GregorianCalendar giornoChiusura= new GregorianCalendar();
        giornoChiusura.add(GregorianCalendar.DAY_OF_WEEK, Calendar.MONDAY);
        /*
        i locali chiudono tutti il lunedì (questa è l'intenzione)
        link per vedere che il giorno di chiusura lunedì ha costante 2
        https://docs.oracle.com/javase/7/docs/api/constant-values.html#java.util.Calendar.MONDAY
         */
        //
        chiusura.add(GregorianCalendar.DAY_OF_WEEK,2);
        GestoreLocale daMimmo = new GestoreLocale("da Giulio",20,orarioapertura, chiusura, giornoChiusura);
        GestoreLocale bellaNapoli = new GestoreLocale("Bella Napoli", 30, orarioapertura, chiusura, giornoChiusura);
        //daMimmo.aggiungiTavoli(listaTavoli);
        daMimmo.addTable(tav1);
        daMimmo.addTable(tav2);
        daMimmo.addTable(tav3);

        bellaNapoli.aggiungiTavoli(listaTavoli);

        ArrayList<GestoreLocale> locali= new ArrayList<>();
        locali.add(daMimmo);
        locali.add(bellaNapoli);

        //parte grafica
        FinestraLogin frame= new FinestraLogin(locali);
        //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);



    }
}
