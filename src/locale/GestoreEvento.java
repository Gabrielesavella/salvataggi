
package locale;

import persone.Invitato;
import vincoli.Vincolo;

import java.util.*;


public class GestoreEvento {

    private String nomeEvento;

    private GestoreLocale location = null;
    private ArrayList <Invitato> invitati;
    private  ArrayList <Vincolo> lista_vincoli;
    private GregorianCalendar dataEvento;
    private int numInvitati;





    public GestoreEvento(String nomeEvento, GregorianCalendar dataEvento, GestoreLocale location, int numInvitati){

        /*Crea un GestoreEvento caratterizzato da un nome, una data e un GestoreLocale. Al suo interno verranno successivamente inseriti
        una lista di Invitati e di Vincoli*/


        this.nomeEvento = nomeEvento;
        this.location = location;
        this.dataEvento = dataEvento;
        this.invitati = new ArrayList(numInvitati);
        this.numInvitati=numInvitati;

    }

    public GestoreEvento(String nomeEvento, GregorianCalendar dataEvento, int numInvitati) {
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.numInvitati = numInvitati;
    }

    public String getName(){return nomeEvento;}


    public GestoreEvento(String nomeEvento, GregorianCalendar dataEvento, GestoreLocale location,
                         ArrayList <Vincolo> lista_vincoli, ArrayList <Invitato> invitati){

        /*il locale è creato dall'evento?? serve una classe nel mezzo che crei le istanze?
        da dove prendo i locali ?? (secondo me sono memorizzati nel Database) quindi dovrò trovare una soluzione temporanea
                */

        this.nomeEvento = nomeEvento;
        this.location = location;
        this.dataEvento = dataEvento;
        this.lista_vincoli.addAll(lista_vincoli);
        invitati = new ArrayList();
        this.invitati.addAll(invitati);
        location.getEventi().add(this);
    }


    public GestoreLocale getLocation(){ return location;}

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public void setLocation(GestoreLocale location) {
        this.location = location;
    }

    public void addInvitati(Invitato invitato){
        invitati.add(invitato);
    }


    /*public void setInvitati(ArrayList <persone.Invitato> invitati) {
        this.invitati = invitati;
    }*/

    public int getNumInvitati(){return numInvitati;}

    public GregorianCalendar getDataEvento(){
        return dataEvento;
    }

    public void setLista_vincoli(ArrayList <Vincolo> lista_vincoli) {
        this.lista_vincoli = lista_vincoli;
    }

    public ArrayList<Invitato> getListaInvitati(){return invitati;}

    public void showListaInvitati(){
        for (Invitato singleGuest:invitati) {
            System.out.println(singleGuest.toString());

        }
    }

    public void setDataEvento(GregorianCalendar dataEvento) {
        this.dataEvento = dataEvento;
    }
}