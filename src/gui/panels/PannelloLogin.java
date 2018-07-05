package gui.panels;

import gui.controller.SistemaDiPrenotazioneController;
import gui.finestre.FinestraCreazioneEvento;
import gui.finestre.FinestraLogin;
import gui.finestre.FinestraRegistrazione;
import locale.GestoreLocale;
import persone.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 *
 * @author lecciovich
 */

public class PannelloLogin extends JPanel {
    public PannelloLogin(ArrayList<GestoreLocale> locali, FinestraLogin frame){
        // LookandFeel setup
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }} catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        //Bottoni
        JButton signUp = new JButton("SignUp");
        JButton logIn = new JButton("LogIn");
        //etichette per descrizione campi di testo
        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");
        //campi di inserimento testo
        JTextField tUsername = new JTextField("");
        JPasswordField tPassword = new JPasswordField("");
        username.setVisible(true);
        password.setVisible(true);
        signUp.setVisible(true);
        logIn.setVisible(true);

        int i=12;
        int j=3;
        JPanel[][] panelHolder= new JPanel[i][j];
        this.setLayout(new GridLayout(12,3));
        for (int m=0;m<i;m++){
            for (int n=0;n<j;n++){
                panelHolder[m][n]=new JPanel();
                add(panelHolder[m][n]);
                panelHolder[m][n].setLayout(new BorderLayout());
            }
        }
        panelHolder[4][1].add(username);
        panelHolder[5][1].add(tUsername);
        panelHolder[6][1].add(password);
        panelHolder[7][1].add(tPassword);
        panelHolder[9][1].add(logIn);
        panelHolder[0][2].add(signUp,BorderLayout.CENTER);

        SistemaDiPrenotazioneController sisPr=new SistemaDiPrenotazioneController();
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinestraRegistrazione fr=new FinestraRegistrazione(locali);
                //fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                fr.setVisible(true);
            }
        });

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
/*                if (PannelloRegistrazione.clienti!=null){
                    for (Cliente c:PannelloRegistrazione.clienti) {
                        if (c.getID().equals(username.getText()) && c.getPsw().equals(password.getText())){
                            FinestraCreazioneEvento fe=new FinestraCreazioneEvento(locali);
                            fe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            fe.setVisible(true);
                        }
                    }
                }
                ArrayList<String> recordLoggato= .fetchClient(tUsername.getText(),tPassword.getText());
                Cliente cliente= new Cliente(recordLoggato.get(0),recordLoggato.get(1),recordLoggato.get(2),recordLoggato.get(3));
*/
                Cliente cliente = sisPr.login(tUsername.getText(), String.valueOf(tPassword.getPassword()));
                if (cliente!=null){
                    FinestraCreazioneEvento fe=new FinestraCreazioneEvento(locali,cliente);
                    fe.setVisible(true);
                    //fe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                }
                else{
                    System.out.println("errore in creazione Cliente");
                }
            }
        });

    }

    }
