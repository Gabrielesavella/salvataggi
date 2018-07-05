package tester;

import locale.*;
import locale.GestoreLocale;
import persone.*;
import vincoli.*;

import java.util.*;

public class PrenotazionePosti {

    /**
     * creazione dei locali che ossono essere utilizzati dall'utente
     * @author Gabrielesavella,Salvi
     */
    public static void main(String[] args) {
        Tavolo tav1 = new Tavolo("bellaNapoli","tav1",5);
        Tavolo tav2 = new Tavolo("bellaNapoli","tav2",6);
        Tavolo tav3 = new Tavolo("bellaNapoli","tav3",8);
        Tavolo tav4 = new Tavolo("bellaNapoli","tav4",4);
        Tavolo tav5 = new Tavolo("bellaNapoli","tav5",6);
        Tavolo tav6 = new Tavolo("bellaNapoli","tav6",8);



        ArrayList<Tavolo> listaTavoli = new ArrayList <Tavolo>();
        listaTavoli.add(tav1);
        listaTavoli.add(tav2);
        listaTavoli.add(tav3);
        listaTavoli.add(tav4);
        listaTavoli.add(tav5);
        listaTavoli.add(tav6);



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
        // GestoreLocale daMimmo = new GestoreLocale("da Giulio",20,listaTavoli,orarioapertura, chiusura);
        GestoreLocale bellaNapoli = new GestoreLocale("bellaNapoli", 30, orarioapertura, chiusura, giornoChiusura);
        bellaNapoli.aggiungiTavoli(listaTavoli);

        //eventi;
        GestoreEvento e = new GestoreEvento("Matrimonio", orarioEvento,  bellaNapoli, 22);
        GestoreEvento k = new GestoreEvento("Battesimo", orarioEvento, bellaNapoli, 20);

        //invitati;

        Invitato a = new Invitato("MarMaf01","Marco","Maffoni",39);
        Invitato b = new Invitato("GabSav01","Gabriele","Savella",39);
        Invitato c = new Invitato("MarLec01", "Marco","Lecce",39);
        Invitato d = new Invitato("FedDor01", "Federico","Dorigo",39);
        Invitato t = new Invitato("MarRos01","Mario","Rossi",39);
        Invitato f = new Invitato("SalPar01","Salvatore","Parisi",39);
        Invitato g = new Invitato("MauCost01", "Maurizio","Costanzo",39);
        Invitato h = new Invitato("CarCon01", "Carlo","Conti",39);
        Invitato i = new Invitato("MatSal01","Matteo","Salvini",39);
        Invitato l = new Invitato("LuiDiM01", "Luigi","Di Maio",39);
        Invitato m = new Invitato("SilBer01", "Silvio","Berlusconi",39);
        Invitato n = new Invitato("GonHig01", "Gonzalo","Higuain",39);
        Invitato o = new Invitato("BelRod01", "Belen","Rodriguez",39);
        Invitato p = new Invitato("MauSar01", "Maurizio","Sarri",39);
        Invitato q = new Invitato("AntCon01","Antonio","Conte",39);
        Invitato r = new Invitato("MarDeF01","Maria","De Filippi",39);
        Invitato s = new Invitato("FraTot01","Francesco","Totti",39);
        Invitato u = new Invitato("FilInz01","Filippo","Inzaghi",39);
        Invitato v = new Invitato("PaoMald01","Paolo","Maldini",39);
        Invitato z = new Invitato("MasAll01", "Massimiliano","Allegri",39);
        Invitato w = new Invitato("IlaBla01","Ilary", "Blasi", 39);
        Invitato y = new Invitato("EnrMen01","Enrico", "Mentana", 39);
        Invitato j = new Invitato("EnzSor01","Enzo", "Sorrentino", 55);
        Invitato y2 = new Invitato("GerSco01","Gerry", "Scotti", 60);

        //        //inserimento invitati nelle liste evento 1;
        e.addInvitati(a);
        e.addInvitati(b);
        e.addInvitati(c);
        e.addInvitati(d);
        e.addInvitati(f);
        e.addInvitati(g);
        e.addInvitati(h);
        e.addInvitati(i);
        e.addInvitati(l);
        e.addInvitati(m);
        e.addInvitati(n);
        e.addInvitati(o);
        e.addInvitati(p);
        e.addInvitati(q);
        e.addInvitati(r);
        e.addInvitati(s);
        e.addInvitati(t);
        e.addInvitati(u);
        e.addInvitati(v);
        e.addInvitati(z);
        e.addInvitati(w);
        e.addInvitati(j);
        e.addInvitati(y);
        e.addInvitati(y2);
        //evento 2//
        k.addInvitati(a);
        k.addInvitati(b);
        k.addInvitati(c);
        k.addInvitati(d);
        k.addInvitati(f);
        k.addInvitati(g);
        k.addInvitati(h);
        k.addInvitati(i);
        k.addInvitati(l);
        k.addInvitati(m);
        k.addInvitati(n);
        k.addInvitati(o);
        k.addInvitati(p);
        k.addInvitati(q);
        k.addInvitati(r);
        k.addInvitati(s);
        k.addInvitati(t);
        k.addInvitati(u);
        k.addInvitati(v);
        k.addInvitati(z);
         /*
        // stampe per prova;
        System.out.println(bellaNapoli.stampaNomeEventi());
        try {
            txtFacade prova = new txtFacade("primidueinvitati.txt", 2);
            prova.WriteGuests(a.getID_Inv(),a.getNome(),a.getCognome(),a.getEta());
            prova.WriteGuests(b.getID_Inv(),b.getNome(),b.getCognome(),b.getEta());
        }catch(IOException e1){
            System.out.println("Eccezione: " + e1);
        }
        e.showListaInvitati();
        k.showListaInvitati();
        bellaNapoli.smistamentoTavoli(e);
        System.out.println("maffo balordo ");
        System.out.println(bellaNapoli.getInvitatiAlTavolo(tav1));
        */
        // test per la gestione dei vincoli;

        bellaNapoli.getEventi().add(e);

        /*ArrayList<Invitato> listaVincolati  = new ArrayList<>();
        ArrayList<Invitato> listaVincolati2  = new ArrayList<>();
        ArrayList<Invitato> listaVincolati3  = new ArrayList<>();
        ArrayList<Invitato> listaVincolati4  = new ArrayList<>();
        ArrayList<Invitato> listaVincolati5  = new ArrayList<>();

        listaVincolati.add(b);
        listaVincolati.add(c);
        listaVincolati.add(d);
        listaVincolati.add(f);
        listaVincolati.add(p);
        listaVincolati.add(q);

        listaVincolati2.add(g);

        listaVincolati3.add(h);
        listaVincolati3.add(l);
        listaVincolati3.add(m);
        //listaVincolati3.add(n);


        listaVincolati4.add(h);
        listaVincolati4.add(l);
        listaVincolati4.add(z);
        listaVincolati4.add(r);
        //listaVincolati4.add(s);
        listaVincolati4.add(u);
        //listaVincolati4.add(v);
        //listaVincolati4.add(z);
        //listaVincolati4.add(w);

        listaVincolati5.add(g);*/

        /*
        listaVincolati.add(g);
        listaVincolati.add(h);
        listaVincolati.add(i);
        listaVincolati.add(l);
        listaVincolati.add(m);
        listaVincolati.add(n);
        listaVincolati.add(o);
        */

        //GestorePreferenzaInvitato provaVincolo = new GestorePreferenzaInvitato(a,listaVincolati,e,PreferenzaInvitatoEnum.STA_VICINO_A);
        //GestorePreferenzaInvitato provaVincolo2 = new GestorePreferenzaInvitato(b,listaVincolati2,e,PreferenzaInvitatoEnum.STA_VICINO_A);
        //GestorePreferenzaInvitato provaVincolo3 = new GestorePreferenzaInvitato(i,listaVincolati3,e,PreferenzaInvitatoEnum.NON_STA_VICINO_A);
        //GestorePreferenzaInvitato provaVincolo4 = new GestorePreferenzaInvitato(o,listaVincolati4,e,PreferenzaInvitatoEnum.NON_STA_VICINO_A);
        //GestorePreferenzaInvitato provaVincolo5 = new GestorePreferenzaInvitato(b, listaVincolati5, e, PreferenzaInvitatoEnum.NON_STA_VICINO_A);

        //provaVincolo.verificaIdoneita();


        ArrayList<Invitato> listaVincolati  = new ArrayList<Invitato>();
        listaVincolati.add(b);
        listaVincolati.add(c);
        GestorePreferenzaInvitato provaVincolo = new GestorePreferenzaInvitato(a,listaVincolati,e,PreferenzaInvitatoEnum.STA_VICINO_A);
        provaVincolo.verificaIdoneita();

        ArrayList<Invitato> listaVincolati2  = new ArrayList<Invitato>();
        listaVincolati2.add(d);
        GestorePreferenzaInvitato provaVincolo2 = new GestorePreferenzaInvitato(f,listaVincolati2,e,PreferenzaInvitatoEnum.NON_STA_VICINO_A);
        provaVincolo2.verificaIdoneita();

        ArrayList<Invitato> listaVincolati3  = new ArrayList<Invitato>();
        listaVincolati3.add(g);
        listaVincolati3.add(f);
        GestorePreferenzaInvitato provaVincolo3 = new GestorePreferenzaInvitato(d,listaVincolati3,e,PreferenzaInvitatoEnum.NON_STA_VICINO_A);
        provaVincolo3.verificaIdoneita();

        ArrayList<Invitato> listaVincolati4  = new ArrayList<Invitato>();
        listaVincolati4.add(b);
        listaVincolati4.add(p);
        GestorePreferenzaInvitato provaVincolo4 = new GestorePreferenzaInvitato(a,listaVincolati4,e,PreferenzaInvitatoEnum.STA_VICINO_A);
        provaVincolo4.verificaIdoneita();


        System.out.println("Smisto le persone secondo i vincoli:\n\n");
        System.out.println(e.getLocation().showInvitatiAiTavoli());

        /*System.out.println("\n\n\n\n\nOra smisto gli invitati rimanenti:\n\n");

        bellaNapoli.smistamentoTavoli(e);
        System.out.println(e.getLocation().showInvitatiAiTavoli());*/

    }

}