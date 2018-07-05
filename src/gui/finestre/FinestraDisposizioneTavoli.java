package gui.finestre;

import gui.panels.PannelloDisposizioneTavoli;
import locale.GestoreEvento;
import locale.GestoreLocale;
import vincoli.*;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author lecciovich
 */

public class FinestraDisposizioneTavoli extends JFrame {
    public FinestraDisposizioneTavoli(GestoreLocale gestoreLocale, GestoreEvento gestoreEvento, GestoreVincoliTavolo gestoreVincoliTavolo){
        setSize(200,200);
        PannelloDisposizioneTavoli pd=new PannelloDisposizioneTavoli(gestoreLocale, gestoreEvento,gestoreVincoliTavolo);
        Container c=getContentPane();
        c.add(pd);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
