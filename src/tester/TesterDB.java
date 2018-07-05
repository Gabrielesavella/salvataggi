package tester;

import database.ConnessioneDB;

import java.sql.Date;


public class TesterDB {
    public static void main(String args[]) {
        String ID_Cliente="Batman";
        String nomeCliente="Bruce";
        String cognomeCliente="Wayne";
        String password="Gotham";
        String ID_Evento="Matrimonio Batman-Catwoman";
        Date dataEvento= new Date(2019,1,21);
        String nomeLocale= "Da Pinguino, pesce fresco";
        int numeroInvitati= 200;
        String ID_Inv="ClaKen038";
        String nomeInv= "Clark";
        String cognomeInv= "Kent";
        String starVicino= "DiaPri042, HalJor050";
        String starLontano = "ArtCur060";
        int etaInv= 30;
        boolean t= true;
        boolean f= false;
        ConnessioneDB connetto= new ConnessioneDB();
        System.out.println("Stato della connessione prima del tentativo di connessione: " + connetto.checkConn());
        connetto.startConn();
        System.out.println("Stato della connessione dopo il tentativo di connessione: " + connetto.checkConn());
        //connetto.inserisciDatiCliente(ID_Cliente, nomeCliente, cognomeCliente, password);
        //connetto.inserisciDatiEvento(ID_Cliente, ID_Evento, dataEvento, nomeLocale, numeroInvitati);
       // connetto.inserisciDatiInvitato(ID_Evento, ID_Inv, nomeInv, cognomeInv, etaInv);
       // connetto.inserisciVincoliTavolo(ID_Evento, ID_Inv, 1, 0, 1 , 0, 0 , 0 );
        //connetto.inserisciVincoloInvitati(ID_Evento, ID_Inv, starVicino, starLontano);
       // connetto.getCliente(ID_Cliente);
        //connetto.getEvento(ID_Evento);
       // connetto.getInvitato(ID_Inv);
        //connetto.getVincoloInvitato(ID_Inv);
        connetto.getVincoloTavolo(ID_Inv);
        connetto.closeConn();
        System.out.println("Stato della connessione dopo il tentativo di chiusura di essa: " + connetto.checkConn());
    }

}
