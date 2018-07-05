package gui.finestre;

import gui.panels.PannelloLogin;
import locale.GestoreLocale;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author lecciovich
 */

public class FinestraLogin extends JFrame {
    public FinestraLogin(ArrayList<GestoreLocale> locali){
        setSize(800,800);
            PannelloLogin p= new PannelloLogin(locali,this);
            Container c=getContentPane();
            c.add(p);
    }

}
