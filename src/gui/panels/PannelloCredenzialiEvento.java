package gui.panels;

import gui.controller.SistemaDiPrenotazioneController;
import gui.finestre.FinestraCreazioneEvento;
import gui.finestre.FinestraSpecificheEvento;
import locale.GestoreEvento;
import locale.GestoreLocale;
import org.jdesktop.swingx.JXDatePicker;
import persone.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author lecciovich
 */

public class PannelloCredenzialiEvento extends JPanel{
    public PannelloCredenzialiEvento(ArrayList<GestoreLocale> locali, Cliente cliente, FinestraCreazioneEvento frame){
        JButton ok=new JButton("OK");

        JLabel nome= new JLabel("Nome Evento:");
        JLabel data= new JLabel("Data Evento:");
        JLabel selLoc= new JLabel("Selezione Locale:");
        JLabel nInv= new JLabel("Numero Invitati:");

        JTextField tNome= new JTextField();
        JTextField tData= new JTextField();
        JTextField tSelLoc= new JTextField();
        JTextField tnInv= new JTextField();

        JPanel campi=new JPanel();
        campi.setLayout(new GridLayout(4,2));

        campi.add(nome);
        campi.add(tNome);
        campi.add(data);

        //JPanel pData= new JPanel();
        //pData.setLayout(new GridLayout(1,5));

//        JTextField giorno=new JTextField("gg");
//        JTextField mese= new JTextField("mm");
//        JTextField anno= new JTextField("aaaa");
//        JLabel token= new JLabel("/");

        JXDatePicker calendario=new JXDatePicker();

//        pData.add(giorno);
//        pData.add(mese);
//        pData.add(anno);

        campi.add(calendario);

        JComboBox<String> dropDownLocali= new JComboBox<String>();

        dropDownLocali.addItem("da Giulio");
        dropDownLocali.addItem("fortezza della solitudine");
        dropDownLocali.addItem("morte nera");



        campi.add(selLoc);
        campi.add(dropDownLocali);
        campi.add(nInv);
        campi.add(tnInv);

        this.setLayout(new BorderLayout());
        add(campi);
        add(ok,BorderLayout.SOUTH);

        SistemaDiPrenotazioneController sisPr = new SistemaDiPrenotazioneController();
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GestoreLocale gestoreLocaleSelezionato =null;

                int invitati=Integer.parseInt(tnInv.getText());
//                int annoInt= Integer.parseInt(anno.getText());
//                int meseInt= Integer.parseInt(mese.getText());
//                int giornoInt= Integer.parseInt(giorno.getText());
                Date data=calendario.getDate();
                GregorianCalendar calendar = new GregorianCalendar();//annoInt,meseInt,giornoInt
                calendar.setTime(data);

                for (GestoreLocale l:locali) {
                    if(l.id_locale.equals(dropDownLocali.getSelectedItem())) {
                        gestoreLocaleSelezionato =l;
                        if (sisPr.creaEvento(tNome.getText(),calendar,Integer.parseInt(tnInv.getText()),cliente))
                            System.out.println("gestoreEvento creato :)");
                    }
                }

                GestoreEvento gestoreEvento =new GestoreEvento(tNome.getText(),calendar, gestoreLocaleSelezionato,invitati);
                FinestraSpecificheEvento fs= new FinestraSpecificheEvento(gestoreLocaleSelezionato, gestoreEvento);//fetchEvento
                fs.setVisible(true);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

            }
        });
    }
}
