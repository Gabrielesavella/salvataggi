package vincoli;

import database.ConnessioneDB;
import locale.Evento;
import locale.GestoreEvento;
import persone.Invitato;

import java.util.ArrayList;

public class PreferenzaInvitato{

    private ConnessioneDB c;
    private ArrayList<Invitato> invitati;
    private ArrayList<GestoreEvento> eventi;
    private String ID_Ev, ID_Inv,vicino, lontano;
    private GestoreEvento gestoreEvento;
    private ArrayList<Invitato> lista_vincolati_vicini;
    private ArrayList<Invitato> lista_vincolati_lontani;
    private Evento evento;

public PreferenzaInvitato(String ID_Ev,String ID_Inv,String vicino,String lontano){

        this.ID_Ev=ID_Ev;
        this.ID_Inv=ID_Inv;
        this.vicino=vicino;
        this.lontano=lontano;

}

    /*public ArrayList<GestorePreferenzaInvitato> gescisciPreferenzaInvitato() {
        ricavaPreferenza();

        for (GestorePreferenzaInvitato g : ricavaPreferenza()){
            g.aggiungiListaVincoli(ottieniListaGestorePreferenza());
        }

        return ricavaPreferenza();
    }*/

    public GestoreEvento creaGestoreEvento(){

        GestoreEvento ge;

        c.startConn();
        evento=c.getEventoSingolo(ID_Ev);
        c.closeConn();

        ge = evento.gestisciEvento();

        return ge;
    }


    public ArrayList<PreferenzaInvitato> takeListaPreferenze(){

        ArrayList<PreferenzaInvitato> prefer;
        c.startConn();
        prefer=c.getVincoloInvitato(ID_Ev);
        c.closeConn();

        return prefer;
    }



    public Invitato prendiInvitato(String ID) {

        c.startConn();
        invitati=c.getInvitato(ID_Ev);
        c.closeConn();

        Invitato invi=null;
        for (Invitato i : invitati) {
            if (ID == i.getID_Inv()) {
                invi = i;
            }
        }
        return invi;
    }

    //Questo metodo prende la lista_vincolati_lontani.
    public ArrayList<Invitato> creaListaInvitatiVicini(String vicin) {

        String[] st = vicin.split(" ");

        for (int x = 0; x < st.length; x++)
        {
            lista_vincolati_vicini.add(prendiInvitato(st[x]));
        }
        return lista_vincolati_vicini;

    }

    //Questo metodo prende la lista_vincolati_lontani.
    public ArrayList<Invitato> creaListaInvitatiLontani(String lont){

        String[] st = lont.split(" ");

        for (int x = 0; x < st.length; x++)
        {
            lista_vincolati_lontani.add(prendiInvitato(st[x]));
        }
        return lista_vincolati_lontani;

    }

    public ArrayList<GestorePreferenzaInvitato> gestisciPreferenza(){

        ArrayList<GestorePreferenzaInvitato> ges= new ArrayList<>();

        GestorePreferenzaInvitato newPref1;
        newPref1 = new GestorePreferenzaInvitato(prendiInvitato(ID_Inv), creaListaInvitatiVicini(vicino), creaGestoreEvento(), PreferenzaInvitatoEnum.STA_VICINO_A);
        GestorePreferenzaInvitato newPref2;
        newPref2 = new GestorePreferenzaInvitato(prendiInvitato(ID_Inv), creaListaInvitatiLontani(lontano), creaGestoreEvento(), PreferenzaInvitatoEnum.NON_STA_VICINO_A);

        ges.add(newPref1);
        ges.add(newPref2);

        return ges;
    }

    public ArrayList<GestorePreferenzaInvitato> ottieniListaGestorePreferenza() {

        ArrayList<GestorePreferenzaInvitato> gest = new ArrayList<>();

        for (PreferenzaInvitato p : takeListaPreferenze()){

            gest.addAll(p.gestisciPreferenza());

        }

        return gest;
    }
}