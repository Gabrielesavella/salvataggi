package gui.finestre;

import gui.panels.PannelloRegistrazione;
import locale.GestoreLocale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author lecciovich
 */

public class FinestraRegistrazione extends JFrame {
    public FinestraRegistrazione(ArrayList<GestoreLocale> locali){
        setSize(800,800);
        PannelloRegistrazione p= new PannelloRegistrazione(locali,this);
        Container c=getContentPane();
        c.add(p);

    }
}
