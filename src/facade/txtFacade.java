package facade;

import locale.GestoreEvento;
import persone.Cliente;
import persone.Invitato;

import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import static persone.Invitato.setID_Inv;

public class txtFacade extends AbstractFacade {

    private String pathClient = "registrazioni.txt", pathGuests = "invitati.txt", pathEvents = "eventi.txt";
    private int numberofObject, writings = 0;
    private FileWriter txtFileW;
    private FileReader txtFileR;
    private BufferedWriter bufferWriter;
    private BufferedReader buffReader;
    private GestoreEvento gestoreEvento = null;
    private Cliente client = null;
    public Invitato invitato = null;
    private boolean registered = false;

    //costruttori
    public txtFacade(String namefile, int numberofObject) throws IOException {
        txtFileW = new FileWriter(namefile, true);
        this.numberofObject = numberofObject;
        bufferWriter = new BufferedWriter(txtFileW);
        txtFileR = new FileReader(namefile);
        buffReader = new BufferedReader(txtFileR);
    }

    public txtFacade(int numberofObject) throws IOException {
        super();
        this.numberofObject = numberofObject;
    }

    //writings
    @Override
    public void WriteClient(String username, String password, String name, String surname, String email) throws IOException {
        boolean exist = false;
        txtFileW = new FileWriter(pathClient, true);
        exist = check(pathClient, username);
        if (!exist) {
            bufferWriter = new BufferedWriter(txtFileW);
            super.WriteClient(username, password, name, surname, email);
        }
    }

    @Override
    public void WriteGuests(String fiscaleCode, String nameGuest, String surnameGuest, int age) throws IOException {
        boolean exist = false;
        txtFileW = new FileWriter(pathGuests, true);
        exist = check(pathGuests, fiscaleCode);
        if (!exist) {
            bufferWriter = new BufferedWriter(txtFileW);
            super.WriteGuests(fiscaleCode, nameGuest, surnameGuest, age);
        }
    }

    @Override
    public void WriteEvent(String nameEvent, GregorianCalendar dateEvent, int guestNumber) throws IOException {
        boolean exist = false;
        txtFileW = new FileWriter(pathEvents, true);
        exist = check(pathEvents, nameEvent);
        if (!exist) {
            bufferWriter = new BufferedWriter(txtFileW);
            super.WriteEvent(nameEvent, dateEvent, guestNumber);
        }
    }

    @Override
    public void generate() throws IOException {
        //genera un file i cui campi sono separati da un tab
        for (String campo : field) {
            bufferWriter.write(campo + "\t");
        }
        bufferWriter.newLine();
        writings++;
        bufferWriter.flush();

        if (writings == numberofObject) {
            closeWriting();
        } else {
            bufferWriter.newLine();
            super.generate();
        }
    }
    //fetchClient + fetch del relativo oggetto

    @Override
    public Cliente fetchClient(String username, String password) throws IOException {
        String line;
        String[] colonna;
        FileWriter writing = new FileWriter(pathClient, true);
        writing.close();
        buffReader = new BufferedReader(new FileReader(pathClient));

        while (buffReader.ready()) {
            line = buffReader.readLine();
            colonna = line.split("\t");
            fetch(username, password, colonna);
        }
        return client;
    }


    @Override
    public Cliente fetch( String username, String password, String[] colonna) throws IOException {
        if (colonna[3].equals(username) && colonna[4].equals(password)) {
            registered = true;
            client = new Cliente(colonna[0], colonna[1], colonna[3], colonna[2], colonna[4]);
            return client;

        } else {
            return client;
        }
    }

    //fetchEvento + fetch del relativo oggetto

    public GestoreEvento fetchEvento(String nomeEvento) throws IOException {

        String line;
        String[] colonna;
        FileWriter writing = new FileWriter(pathEvents, true);
        writing.close();
        BufferedReader reader = new BufferedReader(new FileReader(pathEvents));

        while (reader.ready()) {
            line = reader.readLine();
            colonna = line.split("\t");
            gestoreEvento = fetch(nomeEvento, colonna);
        }
        return gestoreEvento;
    }


    public GestoreEvento fetch(String nomeEvento, String[] colonna) throws IOException {

        if (colonna[0].equals(nomeEvento)) {
            GregorianCalendar orarioapertura = new GregorianCalendar();
            orarioapertura.add(GregorianCalendar.HOUR, Integer.parseInt(colonna[1]));
            gestoreEvento = new GestoreEvento(colonna[0], orarioapertura, Integer.parseInt(colonna[2]));
            return gestoreEvento;

        } else {
            return gestoreEvento;
        }

    }

    //fetch AllGuests

    public ArrayList<Invitato> fetchAllGuests() throws IOException{

        ArrayList<Invitato> AllGuests = new ArrayList<Invitato>();

        String line;
        String[] colonna;
        FileWriter writing = new FileWriter(pathGuests, true);
        writing.close();
        buffReader = new BufferedReader(new FileReader(pathGuests));

        while ( buffReader.ready()) {
            line = buffReader.readLine();
            colonna = line.split("\t");

            invitato = new Invitato(setID_Inv(colonna[1], colonna[2]), colonna[1],colonna[2],Integer.parseInt(colonna[3]));

            AllGuests.add(invitato);
        }
        closeReading();
        return AllGuests;

    }
    //fetchInvitato + get del relativo oggetto

    public Invitato fetchGuest(String idInvitato) throws IOException {

        String line;
        String[] colonna;
        FileWriter writing = new FileWriter(pathGuests, true);
        writing.close();
        buffReader = new BufferedReader(new FileReader(pathGuests));

        while ( buffReader.ready()) {
            line =  buffReader.readLine();
            colonna = line.split("\t");
            invitato = getGuest(idInvitato, colonna);
        }
        return invitato;
    }

    //REVISIONARE
    public Invitato getGuest(String idInvitato, String[] colonna) throws IOException {

        if (colonna[0].equals(idInvitato)) {

            invitato = new Invitato(setID_Inv(colonna[1],colonna[2]), colonna[1],colonna[2],Integer.parseInt(colonna[3]));

            return invitato;

        } else {
            return invitato;
        }

    }

    



    //controllo che l'oggetto non sia già memorizzato nel file

    public boolean check(String path, String key) throws IOException {
        boolean esito = false;
        String line;
        String[] colonna;
        txtFileR = new FileReader(path);
        buffReader = new BufferedReader(txtFileR);

        while (buffReader.ready()) {
            line = buffReader.readLine();
            colonna = line.split("\t");
            if (colonna[0].equals(key)) {
                esito = true;
            }
        }
        closeReading();
        return esito;
    }

    //chiusura dei flussi

    public void closeWriting() throws IOException {
        bufferWriter.close();
        txtFileW.close();
    }

    public void closeReading() throws IOException {
        buffReader.close();
        txtFileR.close();
    }


    //faccio chiusura sia del bufferWriter che del file
    public void closeAll() throws IOException {
        closeWriting();
        closeReading();
        super.generate();
    }

    //metodi getter

    public String getPathClient() {
        return pathClient;
    }

    public String getPathGuests() {
        return pathGuests;
    }

    public String getPathEvents() {
        return pathEvents;
    }
}



