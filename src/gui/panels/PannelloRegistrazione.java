package gui.panels;

import gui.controller.SistemaDiPrenotazioneController;
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

public class PannelloRegistrazione extends JPanel {
    static Cliente lecciovich= new Cliente("lecciovich","Marco","Lecce", "marcolecce@gmail.com","prova");
    static ArrayList<Cliente> clienti=new ArrayList<Cliente>(2);

    public PannelloRegistrazione(ArrayList<GestoreLocale> locali,JFrame frame){
        clienti.add(lecciovich);

        JButton conferma = new JButton("Registrati");
        //etichette per descrizione campi di testo
        JLabel eMail = new JLabel("E-Mail:");
        JLabel nome = new JLabel("Nome:");
        JLabel cognome = new JLabel("Cognome:");
        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");
        JLabel confPassword = new JLabel("Conferma Password:");
        JLabel errore = new JLabel();
        //campi di inserimento testo
        JTextField tEMail = new JTextField("");
        JTextField tNome = new JTextField("");
        JTextField tCognome = new JTextField("");
        JTextField tUsername = new JTextField("");
        JTextField tPassword = new JPasswordField("");
        JTextField tConfPassword=new JPasswordField("");

        JTextField clienteAdded= new JTextField("qui nuovi clienti");

        username.setVisible(true);
        password.setVisible(true);
        //pannelli
        JPanel campi=new JPanel();
        JPanel bottoni=new JPanel();
//        int i=6;
//        int j=2;
//        JPanel[][] panelHolder= new JPanel[i][j];
        campi.setLayout(new GridLayout(7,2));

        campi.add(nome);
        campi.add(tNome);
        campi.add(cognome);
        campi.add(tCognome);
        campi.add(eMail);
        campi.add(tEMail);
        campi.add(username);
        campi.add(tUsername);
        campi.add(password);
        campi.add(tPassword);
        campi.add(confPassword);
        campi.add(tConfPassword);
        campi.add(conferma);
        campi.add(errore);
        campi.add(clienteAdded);

        bottoni.add(clienteAdded);
//        bottoni.setLayout(new GridLayout(1,3));
//        add(new JPanel());
//        add(conferma);
//
//        this.setLayout(new GridLayout(1,2));
        this.add(campi);
        this.add(bottoni);


        conferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                if (tPassword.getText().equals(tConfPassword.getText())) {
//                    clienti.add(new Cliente(nome.getText(), cognome.getText(), username.getText(), password.getText()));
//                    clienteAdded.setText("Ho aggiunto un cliente");
//                    errore.setText("");
//                }
                SistemaDiPrenotazioneController sisPr= new SistemaDiPrenotazioneController();
                boolean registrazione=sisPr.signUp(tNome.getText(),tCognome.getText(),tEMail.getText(),tUsername.getText(),tPassword.getText());
                boolean psswrdCorretta=tPassword.getText().equals(tConfPassword.getText());
                if(registrazione & psswrdCorretta){
                    clienteAdded.setText("Ho aggiunto un cliente");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

                }
                else {
                    if (!psswrdCorretta) {
                        errore.setText("Le password non coincidono.\nRiprovare inserimento");
                        errore.setForeground(Color.RED);
                    }
                    clienteAdded.setText("Non ho aggiunto un cliente");
                }
            }
        });

    }

    public static ArrayList<Cliente> getClienti() {
        return clienti;
    }

}
