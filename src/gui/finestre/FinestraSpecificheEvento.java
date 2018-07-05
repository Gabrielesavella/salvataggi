package gui.finestre;

import gui.panels.PannelloSpecificheEvento;
import locale.GestoreEvento;
import locale.GestoreLocale;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author lecciovich
 */

public class FinestraSpecificheEvento extends JFrame{
    public FinestraSpecificheEvento(GestoreLocale gestoreLocale, GestoreEvento gestoreEvento){
        setSize(200,200);
        PannelloSpecificheEvento ps=new PannelloSpecificheEvento(gestoreLocale, gestoreEvento,this);
        Container c=getContentPane();
        c.add(ps);
    }

}
