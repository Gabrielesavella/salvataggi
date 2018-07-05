package locale;

import database.ConnessioneDB;
import locale.Locale;
import persone.Invitato;
import sun.util.calendar.Gregorian;
import vincoli.Vincolo;

import java.util.*;


public class Evento {

    private String nomeEvento;
    private Locale location;
    private ArrayList <Invitato> invitati;
    private ArrayList <Vincolo> lista_vincoli;
    private ArrayList<Locale> locali;
    private String dataEvento;
    private int numInvitati;
    GregorianCalendar dataEventoCalendario;

    private Date dataEv;
    private String nomeLocale;



    /*nell'uml manca la data dell'evento ( l'aggiungo al costruttore )]*/


    public Evento(String nomeEvento, String dataEvento, String nomeLocale, int numInvitati){

        /*Crea un Evento caratterizzato da un nome, una data e un Locale. Al suo interno verranno successivamente inseriti
        una lista di Invitati e di Vincoli*/

        this.nomeEvento = nomeEvento;
        this.nomeLocale=nomeLocale;
        this.numInvitati=numInvitati;
        this.dataEvento = dataEvento;
    }


    public static GregorianCalendar ricavaData(String data){

        GregorianCalendar dataEventoCalendario = new GregorianCalendar();
        String[] st = data.split("/");
        if(st[0]!=null) {
            dataEventoCalendario.add(GregorianCalendar.DAY_OF_MONTH, Integer.parseInt(st[0]));
        }
        if(st[1]!=null){
            dataEventoCalendario.add(GregorianCalendar.MONTH, Integer.parseInt(st[1]));
        }
        if(st[2]!=null){
            dataEventoCalendario.add(GregorianCalendar.YEAR, Integer.parseInt(st[2]));
        }

        return dataEventoCalendario;
    }

    public String getName(){return nomeEvento;}

    public String getNomeLocale(){return nomeLocale;}

    public Locale getLocation(){ return location;}

    public int getNumInvitati(){return numInvitati;}

    public Locale prendiLocale(String nomeLoc) {
        Locale loca=null;
        for (Locale l : locali) {
            if (nomeLoc == l.getID_Locale()) {
                loca = l;
            }
        }
        return loca;
    }

    public ArrayList<Invitato> ricavaInvitati(String ID_Evento){
        ConnessioneDB c= new ConnessioneDB();
        c.startConn();
        ArrayList<Invitato> invitati= c.getInvitato(ID_Evento);
        c.closeConn();
        return invitati;
    }

    public GestoreEvento gestisciEvento(){

        GestoreEvento ge;
        GestoreLocale gl;
        this.dataEventoCalendario=ricavaData(dataEvento);
        this.location= prendiLocale(nomeLocale);
        gl = location.gestisciLocale();
        ge = new GestoreEvento(nomeEvento, dataEventoCalendario, gl, numInvitati);
        ge.getListaInvitati().addAll(ricavaInvitati(nomeEvento));

        return ge;
    }



}