package gui.panels;

import locale.*;
import persone.*;
import vincoli.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author lecciovich
 */

public class PannelloDisposizioneTavoli extends JPanel {
    public PannelloDisposizioneTavoli(GestoreLocale gestoreLocale, GestoreEvento gestoreEvento, GestoreVincoliTavolo gestoreVincoliTavolo){
        JPanel  pAllGuests = new JPanel();
        JPanel  pTablesGuests = new JPanel();
        JLabel  labelElenco = new JLabel("Elenco invitati a " + gestoreEvento.getName());
        JTextArea stampaElenco = new JTextArea();
        JLabel  labelDisposizione = new JLabel("Elenco invitati disposti per tavoli");
        JTextArea stampaDisposizione = new JTextArea();

        Dimension d=new Dimension(400,400);
        stampaDisposizione.setSize(d);
        stampaDisposizione.setLineWrap(true);
        stampaDisposizione.setEditable(false);
        stampaDisposizione.setAutoscrolls(true);

        stampaElenco.setEditable(false);
        stampaElenco.setAutoscrolls(true);
        stampaElenco.setSize(d);
//        JScrollBar scroll = new JScrollPane(stampaElenco);

        //stampaElenco.(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        pAllGuests.add(labelElenco);
        pAllGuests.add(stampaElenco);

        pTablesGuests.add(labelDisposizione);
        pTablesGuests.add(stampaDisposizione);

        add(pAllGuests);
        add(pTablesGuests);



        stampaDisposizione.append("numero invitati: " + gestoreEvento.getNumInvitati()+"\n");
        for (Invitato i: gestoreEvento.getListaInvitati()) {
            stampaElenco.append(i.getID_Inv()+"\t"+i.getNome()+"\t"+i.getCognome()+"\t"+i.getEta()+"\n");

        }


//        for (Invitato i:gestoreEvento.getListaInvitati()) {
//            stampaElenco.append(i.getID_Inv(+"\t"+i.getNome()+"\t"+i.getCognome()+"\t"+i.getEta()+"\n");
//        }

//        for (Tavolo t: gestoreLocale.smistamentoTavoli(gestoreEvento)) {
//            stampaDisposizione.append(t.getIDTavolo()+"\n");
//            for (Invitato invitato:t.getArraylistInvitati()) {
//                stampaDisposizione.append(invitato.getID_Inv()+"\t"+invitato.getNome()+"\t"+invitato.getCognome()+"\t"+invitato.getEta()+"\n");
//            }
//        }
        ArrayList<Tavolo> tavoliSmistati=new ArrayList<>();

        Tavolo tav1 = new Tavolo("da Giulio","tav1",5);
        Tavolo tav2 = new Tavolo("da Giulio","tav2",6);
        Tavolo tav3 = new Tavolo("da Giulio","tav3",8);
        Tavolo tav4 = new Tavolo("da Giulio","tav4",4);
        Tavolo tav5 = new Tavolo("da Giulio","tav5",6);
        Tavolo tav6 = new Tavolo("da Giulio","tav6",8);

        tavoliSmistati.add(tav1);
        tavoliSmistati.add(tav2);
        tavoliSmistati.add(tav3);
        tavoliSmistati.add(tav4);
        tavoliSmistati.add(tav5);
        tavoliSmistati.add(tav6);

//        gestoreLocale.aggiungiTavoli(gestoreLocale.getTavoliLocale());
//        gestoreLocale.getEventi().add(gestoreEvento);
//        gestoreLocale.aggiungiEventi(gestoreEventos);/
// /getEventi().
//        tavoliSmistati= gestoreLocale.smistamentoTavoli(gestoreEvento);
//        System.out.println(gestoreLocale.showInvitatiAiTavoli());
        stampaDisposizione.append("\nDisposizione:\n");
//        stampaDisposizione.append(gestoreLocale.showInvitatiAiTavoli());
        for (Tavolo t:gestoreVincoliTavolo.getTavoliVincolati()) {
            stampaDisposizione.append(t.getIDTavolo());
            stampaDisposizione.append(t.showInvitati());

        }

    }
}
