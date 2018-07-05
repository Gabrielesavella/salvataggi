package locale;

import database.ConnessioneDB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Locale {

    private String ID_Loc, orarioApertura, orarioChiusura, giornoChiusura;
    private int numInv;
    private GestoreLocale locale;
    private ConnessioneDB c;
    private ArrayList<Tavolo> tavoliTotali;
    private ArrayList<GestoreEvento> gestoreEventiTotali;
    private ArrayList<Evento> eventiTotali;


    public Locale(String ID_Loc, int numInv, String orarioApertura, String orarioChiusura, String giornoChiusura){

        this.ID_Loc=ID_Loc;
        this.numInv=numInv;
        this.orarioApertura=orarioApertura;
        this.orarioChiusura=orarioChiusura;
        this.giornoChiusura=giornoChiusura;

    }

    public GestoreLocale ricavaLocale() {

        locale= new GestoreLocale(ID_Loc, numInv, ricavaOrario(orarioApertura), ricavaOrario(orarioChiusura), ricavaGiorno(giornoChiusura));

        return locale;
    }

    public GestoreLocale gestisciLocale() {

        ricavaLocale();
        aggiungiTavoli();
        aggiungiEventi();

        return locale;
    }

    public void aggiungiTavoli(){
        c.startConn();
        tavoliTotali=c.getTavolo(ID_Loc);
        c.closeConn();
        ricavaLocale().getTavoliLocale().addAll(tavoliTotali);
    }

    public void aggiungiEventi(){
        ricavaLocale().getEventi().addAll(creaListaGestoreEventi());
    }

    public ArrayList<GestoreEvento> creaListaGestoreEventi(){

        c.startConn();
        eventiTotali=c.getEvento(ID_Loc);
        c.closeConn();

        ArrayList<GestoreEvento> ge= new ArrayList<>();
        for (Evento e: eventiTotali){
            ge.add(e.gestisciEvento());
        }

        return ge;
    }


    public String getID_Locale() {
        return ID_Loc;
    }

    //Per ricavare l'orario dalla stringa
    public GregorianCalendar ricavaOrario(String orario){

        GregorianCalendar time = new GregorianCalendar();

        String[] st = orario.split(":");

        if (!(st[0]==null)){
            time.add(GregorianCalendar.HOUR, Integer.parseInt(st[0]));
        }

        if(!(st[1]==null)){
            time.add(GregorianCalendar.MINUTE, Integer.parseInt(st[1]));
        }

        return time;
    }

    //Per ricavare il giorno dalla stringa
    public GregorianCalendar ricavaGiorno(String giorno) {

        GregorianCalendar day = new GregorianCalendar();

        switch (giorno){

            case "Lunedì":
                day.add(GregorianCalendar.MONDAY, Calendar.WEEK_OF_YEAR);
            case "Martedì":
                day.add(GregorianCalendar.TUESDAY, Calendar.WEEK_OF_YEAR);
            case "Mercoledì":
                day.add(GregorianCalendar.WEDNESDAY, Calendar.WEEK_OF_YEAR);
            case "Giovedì":
                day.add(GregorianCalendar.THURSDAY, Calendar.WEEK_OF_YEAR);
            case "Venerdì":
                day.add(GregorianCalendar.FRIDAY, Calendar.WEEK_OF_YEAR);
            case "Sabato":
                day.add(GregorianCalendar.SATURDAY, Calendar.WEEK_OF_YEAR);
            case "Domenica":
                day.add(GregorianCalendar.SUNDAY, Calendar.WEEK_OF_YEAR);
        }

        return day;
    }
}
