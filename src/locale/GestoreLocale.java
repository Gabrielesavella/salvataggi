package locale;

import persone.*;

import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author salvi
 */
public class GestoreLocale {

    static int numLoc = 0;
    public ArrayList<GestoreEvento> eventi_locale;
    public String id_locale;
    private int numMaxTavoli;
    private int numMaxPosti;
    private GregorianCalendar oraApertura, oraChiusura, giornodichiusura;
    Calendar calendar;
    private ArrayList<Tavolo> tavoli;
    private ArrayList<Tavolo> tavoliUtilizzati = new ArrayList<>();
    private ArrayList<Invitato> lista_gia_presenti = new ArrayList<>();

    // cambiato il tipo di dato giorno chiusura da String a Gregoria calendar , molto più facile da gestire
    // aggiunta inoltre del passaggio dei tavoli tramite parametro

    /*
    aggiunta di un costruttore GestoreLocale in più in cui si passa solo il numero massimo di tavoli che vieni istanziato
    nel locale, novità: tutti i locali hanno come giorno di chiusura il lunedì di default
    @author Gabrielesavella
     */
    public GestoreLocale(String id_locale, int numMaxTavoli, GregorianCalendar oraApertura, GregorianCalendar oraChiusura, GregorianCalendar giornodichiusura) {

        this.giornodichiusura=giornodichiusura;
        this.id_locale=id_locale;
        this.numMaxTavoli=numMaxTavoli;
        this.tavoli = new ArrayList<Tavolo>();
        this.oraApertura=oraApertura;
        this.oraChiusura=oraChiusura;
        eventi_locale = new ArrayList<>();
    }

    public void aggiungiEventi(ArrayList<GestoreEvento> eventiLoc){
        for (GestoreEvento e : eventiLoc){
            if(e.getLocation().getId_locale().equals(id_locale)){
                eventi_locale.add(e);
            }
        }
    }

    public void aggiungiTavoli(ArrayList<Tavolo> tavoliTotali){
        for (Tavolo t : tavoliTotali){
            if(t.getID_Loc().equals(id_locale)){ tavoli.add(t); }

        }
    }

    /*Smista tutti invitati ad un particolare evento nei tavoli. Fatto ciò, restituisce un arraylist di tutti i tavoli utilizzati*/
    public ArrayList<Tavolo> smistamentoTavoli(GestoreEvento e){

        rimuoviGiaPresenti(e);

        int count = 0;
        ArrayList<Invitato>listainvitati;

        for (GestoreEvento ev : eventi_locale)
            if (ev.equals(e)) {
                listainvitati = ev.getListaInvitati();

                for (Tavolo t : tavoli) {
                    do {
                        t.addGuest(listainvitati.get(count));
                        count++;

                    } while (t.getDisponibile() && (count+1)<=listainvitati.size());
                    tavoliUtilizzati.add(t);
                   /*
                    se conto tutti gli invitati della lista esco dal ciclo del tavolo (count è incrementato di 1 perchè
                    parte da zero
                     */
                    if ((count) == listainvitati.size())
                        break;

                }
            }
        return tavoliUtilizzati;
    }

    //Rimuove dagli invitati da smistare, le persone che sono già sedute al tavolo.
    public void rimuoviGiaPresenti(GestoreEvento e){
        for (Tavolo t : tavoli){
            lista_gia_presenti.addAll(t.getArraylistInvitati());
        }
        e.getListaInvitati().removeAll(lista_gia_presenti);
    }

    /*Restituisce un arraylist di tutti gli invitati presenti in tutti i tavoli utilizzati*/
    public ArrayList<Invitato> getInvitatiOgniTavolo(){

        ArrayList<Invitato> invitatiTotali = new ArrayList<>();

        for (Tavolo t : tavoliUtilizzati){
            invitatiTotali.addAll(t.getArraylistInvitati());
        }
        return invitatiTotali;
    }

    /*Cancella il contenuto di tavoliUtilizzati, in modo da poter essere riutilizzato per un altro evento*/
    public void clearTavoliUtilizzati(){
        for (Tavolo t : tavoliUtilizzati){
            tavoliUtilizzati.remove(t);
        }
    }


    public int getMinTavoli(int num_invitati){
        int num_tavoli = 0;
        int posti_tavolo;
        int contatore_posti = 0;
        for(Tavolo t: tavoli){
            if (contatore_posti <= num_invitati){
                posti_tavolo = t.getNumPosti();
                contatore_posti += posti_tavolo;
                num_tavoli++;
            }
        }
        return num_tavoli;
    }

    public String showInvitatiAiTavoli(){
        String invitatiTavoli = "";
        for (Tavolo t : tavoli){
            if (!(t.getNumPosti()==t.getPostiTot()))
            invitatiTavoli += t.showInvitati() + "\n\n\n";
        }
        return invitatiTavoli;
    }
    /*creo un metodo per calcolare il massimo numero di posti
     * derivato dalla capienza dei tavoli presenti nel locale
     * @author: Gabrielesavella
     */

    public int getMaxSeats() {
        int capienza = 0;
        for(Tavolo tavolo:tavoli) {
            capienza += tavolo.getNumPosti();
        }
        numMaxPosti = capienza;
        return capienza;

    }

    public ArrayList<GestoreEvento> getEventi(){ return eventi_locale; }

    public String stampaNomeEventi(){
        String a="";
        for (GestoreEvento e : eventi_locale){
            a += e.getName() + "\n";
        }
        return a;
    }
    /*
    creo un getter per il numero max di posti
    che li conta ogni volta tramite il metodo getMaxSeats()
    @author Gabrielesavella
     */
    public int getNumMaxPosti() {
        getMaxSeats();
        return numMaxPosti;
    }
    /*
    controllo che il numero massimo di tavoli sia minore di quello che ho nel locale se è così
    aggiungo un tavolo al locale, di conseguenza calcolo nuovamente il numero massimo di posti @author Gabrielesavella
     */
    public void addTable(Tavolo newTable){
        boolean added = false;
        if(tavoli.size()< numMaxTavoli) {
            added = tavoli.add(newTable);
        }
        //se il tavolo è stato aggiunto aggiungo il numero massimo dei posti
        if(added) {
            numMaxPosti += newTable.getNumPosti();
        }

    }

    /*
    modifica: il metodo restituisce gli invitati di un determinato tavolo specificato da progamma
    @author Gabrielesavella
     */
    public ArrayList<Invitato> getInvitatiAlTavolo(Tavolo t){
        ArrayList<Invitato> tableGuests = new ArrayList<Invitato>();
        tableGuests.addAll(t.getArraylistInvitati());
        return tableGuests;
    }


    public ArrayList<Tavolo> getTavoliLocale() {
        return tavoli;
    }
    public int getNPostiTavolo(String idTavolo) {
        int numposti = 0;

        for (Tavolo t : tavoli) {
            if (t.getIDTavolo().equals(idTavolo))
                numposti += t.getPostiTot();
        }
        return numposti;
    }

    public String getId_locale() {
        return id_locale;
    }
}