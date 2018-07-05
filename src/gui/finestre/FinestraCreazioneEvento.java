package gui.finestre;

import gui.panels.PannelloCredenzialiEvento;
import locale.GestoreLocale;
import persone.Cliente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author lecciovich
 */

public class FinestraCreazioneEvento extends JFrame {
    public FinestraCreazioneEvento(ArrayList<GestoreLocale> locali, Cliente cliente){
        setSize(500,500);
        PannelloCredenzialiEvento pc=new PannelloCredenzialiEvento(locali,cliente,this);
        Container c=getContentPane();
        c.add(pc);

    }

}
