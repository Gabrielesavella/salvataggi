package vincoli;

/**
 *
 * @author Salvatore Parisi
 */

//import database.ConnessioneDB;
import database.ConnessioneDB;
import locale.Tavolo;
import persone.Invitato;

import java.util.*;

public class GestoreVincoliTavolo {



    private ArrayList<Tavolo> tavoli;
    private ArrayList<Tavolo> tavoliVincolati;
    private ArrayList<SpecificaTavolo> nessunaSpecifica, unicaSpecifica, piuSpecifiche, vincoliTav, vincoliNonRispettabili;
    private ArrayList<Invitato> invitati;
    private ArrayList<String> etichette;
    private ArrayList<Tavolo> tavoliDisponibili;
    private ConnessioneDB c;


    public GestoreVincoliTavolo(String ID_Ev) {
        //prelevo dal DB gli Invitati, i Tavoli e i VincoliTavolo relativi all'Evento, secondo il suo identificativo ID_Ev
        c=new ConnessioneDB();
        c.startConn();
        this.vincoliTav = c.getVincoloTavolo(ID_Ev);
        this.invitati = c.getInvitato(ID_Ev);
        this.tavoli= c.getTavolo(ID_Ev);
        c.closeConn();

        //Ordino i tavoli per numero di posti liberi (dal minore al maggiore)
        //E' conveniente sortarli per posti liberi e non per posti totali perchÃ© in questo punto del programma tali valori coincidono,
        //mentre successivamente ho bisogno di averli sortati per "posti liberi"
        Collections.sort(tavoli);

        //verifico che il numero di invitati per ogni vincolo sia >= al limite minimo (la metÃ  del numero di posti del piÃ¹ piccolo dei tavoli)
        verificaLimiteMinimo(contaInvitatiPerVincolo(vincoliTav));

        //divido gli Invitati in tre categoria, secondo il numero di VincoliTavolo (0, 1 o piu)
        smistaInvitati();

        // Ordino i vincoli per numero di invitati (dal maggiore al minore) cosÃ¬ da ottimizzare il processo di assegnazione dei tavoli
        // ai rispetti vincoli (il vincolo col maggior numero di invitati si vedrÃ  assegnato il primo tavolo sufficentemente capiente
        // da ospitare tutti gli invitati con tal vincolo, dato che i tavoli sono ordinati dal piÃ¹ piccolo al piÃ¹ grande)
        assegnaTavoli(ordinaVincoli(contaInvitatiPerVincolo(unicaSpecifica), false));

        //elaboro prima gli invitati con un solo vincolo
        accomodaInvitati(this.unicaSpecifica);


        //ordino gli invitati con piÃ¹ di un vincolo per numero di vincoli ad invitato (dal minore al maggiore) e i tavoli per numero di
        //posti liberi in ordine inverso (dal maggiore al minore): cosÃ¬ l'invitato con meno numero di vincoli (piÃ¹ difficile da piazzare
        // rispetto a chi ne ha di piÃ¹) si vedrÃ  assegnato al tavolo con piÃ¹ posti da riempire
        ordinaVincoli(gesticiVincoliPlurimi(), true);
        Collections.sort(tavoli, Collections.reverseOrder());

        //sistemo gli invitati con piÃ¹ di una specifica
        ArrayList<SpecificaTavolo> ancoraSpecifiche= accomodaInvitati(this.piuSpecifiche);

        //Se ci sono ancora altri invitati vincolati non sistemati nei tavoli unisco i tavoli vincolati con altri tavoli dell'ArrayList tavoli
        //in modo da aumentare il numero di posti per tavolo e far sedere tutti gli invitati
        controllaInvitati();
        accomodaInvitati(ancoraSpecifiche);

        //Ultimo controllo: se ci sono ancora invitati non seduti allora significa che tali vincoli non possono essere rispettati
        //L'algoritmo Ã¨ stato costruito in modo che questi ultimi due casi siano poco probabili
        lastCheck();

    }

    //costruttore Marco Lecce che serve a costruire GestoreVincoliTavoli che ha lo scopo di gestire i vincoli SENZA DB

    public GestoreVincoliTavolo(ArrayList<Tavolo> tavoli,ArrayList<Invitato> invitati,ArrayList<SpecificaTavolo> specificheTavoli){
        this.tavoli = tavoli;
        this.invitati = invitati;
        this.vincoliTav= specificheTavoli=specificheTavoli;

        //Ordino i tavoli per numero di posti liberi (dal minore al maggiore)
        //E' conveniente sortarli per posti liberi e non per posti totali perchÃ© in questo punto del programma tali valori coincidono,
        //mentre successivamente ho bisogno di averli sortati per "posti liberi"
        Collections.sort(tavoli);

        //verifico che il numero di invitati per ogni vincolo sia >= al limite minimo (la metÃ  del numero di posti del piÃ¹ piccolo dei tavoli)
        verificaLimiteMinimo(contaInvitatiPerVincolo(vincoliTav));

        //divido gli Invitati in tre categoria, secondo il numero di VincoliTavolo (0, 1 o piu)
        smistaInvitati();

        // Ordino i vincoli per numero di invitati (dal maggiore al minore) cosÃ¬ da ottimizzare il processo di assegnazione dei tavoli
        // ai rispetti vincoli (il vincolo col maggior numero di invitati si vedrÃ  assegnato il primo tavolo sufficentemente capiente
        // da ospitare tutti gli invitati con tal vincolo, dato che i tavoli sono ordinati dal piÃ¹ piccolo al piÃ¹ grande)
        assegnaTavoli(ordinaVincoli(contaInvitatiPerVincolo(unicaSpecifica), false));

        //elaboro prima gli invitati con un solo vincolo
        accomodaInvitati(this.unicaSpecifica);


        //ordino gli invitati con piÃ¹ di un vincolo per numero di vincoli ad invitato (dal minore al maggiore) e i tavoli per numero di
        //posti liberi in ordine inverso (dal maggiore al minore): cosÃ¬ l'invitato con meno numero di vincoli (piÃ¹ difficile da piazzare
        // rispetto a chi ne ha di piÃ¹) si vedrÃ  assegnato al tavolo con piÃ¹ posti da riempire
        ordinaVincoli(gesticiVincoliPlurimi(), true);
        Collections.sort(tavoli, Collections.reverseOrder());

        //sistemo gli invitati con piÃ¹ di una specifica
        ArrayList<SpecificaTavolo> ancoraSpecifiche= accomodaInvitati(this.piuSpecifiche);

        //Se ci sono ancora altri invitati vincolati non sistemati nei tavoli unisco i tavoli vincolati con altri tavoli dell'ArrayList tavoli
        //in modo da aumentare il numero di posti per tavolo e far sedere tutti gli invitati
        controllaInvitati();
        accomodaInvitati(ancoraSpecifiche);

        //Ultimo controllo: se ci sono ancora invitati non seduti allora significa che tali vincoli non possono essere rispettati
        //L'algoritmo Ã¨ stato costruito in modo che questi ultimi due casi siano poco probabili
        lastCheck();



    }

    /* Metodo che conta quanti invitati ci sono per vincolo. Ritorna un HashMap avente come valore il numero degli invitati per vincolo
     * e come chiave "l'etichetta" del vincolo stesso. Il contatore Ã¨ il valore e non la chiave perchÃ© Ã¨ possibile che piÃ¹ vincoli abbiano
     * lo stesso numero di invitati*/

    public HashMap<String, Integer> contaInvitatiPerVincolo(ArrayList<SpecificaTavolo> sp) {
        int contBambi = 0;
        int contVege = 0;
        int contTavOn = 0;
        int contTavIsola = 0;
        int contDiffMot = 0;
        int contVicinoTV = 0;
        HashMap<String, Integer>contatori = new HashMap<String, Integer>();

        for (SpecificaTavolo s : sp) {
            contBambi += s.getBambini();
            contVege += s.getVegetariano();
            contVicinoTV += s.getVicinoTV();
            contDiffMot += s.getDifficoltaMotorie();
            contTavIsola += s.getTavoloIsolato();
            contTavOn += s.getTavoloOnore();
        }

        contatori.put("bambini", contBambi);
        contatori.put("difficoltaMotorie", contDiffMot);
        contatori.put("tavoloIsolato", contTavIsola);
        contatori.put("tavoloOnore", contTavOn);
        contatori.put("vegetariani", contVege);
        contatori.put("vicinoTV", contVicinoTV);


        etichette= new ArrayList<String>();

        this.etichette.add("bambini");
        this.etichette.add("difficoltaMotorie");
        this.etichette.add("tavoloIsolato");
        this.etichette.add("tavoloOnore");
        this.etichette.add("vegetariani");
        this.etichette.add("vicinoTV");


        return contatori;
    }

    //Metodo utilizzato per verificare che ogni tipo di vincolo sia superiore al limite minimo (cioÃ¨ la metÃ  del numero di posti del tavolo piÃ¹ piccolo)
    //Nel caso che un vincolo non passi tale verifica allora i vincoli di quel tipo non saranno rispettati

    public ArrayList<SpecificaTavolo> verificaLimiteMinimo (HashMap<String, Integer> vinc){
        vincoliNonRispettabili= new ArrayList<SpecificaTavolo>();
        ArrayList<SpecificaTavolo> vincoliTavCopia = (ArrayList)vincoliTav.clone();
        Iterator it = vinc.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry entry = (Map.Entry)it.next();
            if((int)entry.getValue()<(tavoli.get(0).getNumPosti()/2)){
                for (SpecificaTavolo sp: vincoliTavCopia){
                    switch ((String)entry.getKey()){
                        case "bambini":
                            if (sp.getBambini()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                        case "vegetariani":
                            if (sp.getVegetariano()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                        case "vicinoTV":
                            if (sp.getVicinoTV()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                        case "difficoltÃ Motorie":
                            if (sp.getDifficoltaMotorie()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                        case "tavoloIsolato":
                            if (sp.getTavoloIsolato()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                        case "tavoloOnore":
                            if (sp.getTavoloOnore()!=0){
                                vincoliTav.remove(sp);
                                vincoliNonRispettabili.add(sp);
                            }
                    }
                }
            }
        }
        return this.vincoliNonRispettabili;
    }

    /*Divido gli invitati in tre categorie: invitati con nessuna SpecificaTavolo, invitati con una sola specifica, invitati con piu specifiche.
    Per ottimizzare il processo di realizzazione dei vincoli piazzo nei tavoli prima gli invitati con una sola specifica, e tratto gli invitati
    con piÃ¹ opzioni di preferenza sul tavolo come dei "jolly" con cui riempire i tavoli.
     */

    public void smistaInvitati() {
        this.nessunaSpecifica = new ArrayList<SpecificaTavolo>();
        this.unicaSpecifica = new ArrayList<SpecificaTavolo>();
        this.piuSpecifiche = new ArrayList<SpecificaTavolo>();
        for (SpecificaTavolo s : vincoliTav) {
            int sum = s.vicinoTV + s.vegetariano + s.tavoloOnore + s.tavoloIsolato + s.difficoltaMotorie + s.bambini;
            if (sum==0) {

                this.nessunaSpecifica.add(s);}
            if (sum==1) {
                this.unicaSpecifica.add(s);}
            if (sum>1){
                this.piuSpecifiche.add(s);
            }
        }
    }



    //Metodo utilizzato per ordinare un HashMap circa i  suoi valori. Il sorting puÃ² essere secondo il natural order(cioÃ¨ crescente per gli int)
    // o in reverse (decrescente). La scelta Ã¨ regolata dal booleano immesso come parametro.

    public HashMap<String, Integer> ordinaVincoli(HashMap<String, Integer> hm, boolean natural){

        List<String> mapChiavi = new ArrayList<>(hm.keySet());
        List<Integer> mapValori = new ArrayList<>(hm.values());

        if (natural){
            Collections.sort(mapValori);
            Collections.sort(mapChiavi);
        }
        else{
            Collections.sort(mapValori, Collections.reverseOrder());
            Collections.sort(mapChiavi, Collections.reverseOrder());
        }

        LinkedHashMap<String, Integer> mappaSort = new LinkedHashMap<>();

        Iterator<Integer> valoreIt = mapValori.iterator();
        while (valoreIt.hasNext()) {
            int val = valoreIt.next();

            Iterator<String> chiaveIt = mapChiavi.iterator();
            while (chiaveIt.hasNext()) {
                String key = chiaveIt.next();
                int comp1 = hm.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    chiaveIt.remove();
                    mappaSort.put(key, val);
                    break;
                }
            }
        }
        return (HashMap) mappaSort;
    }

    /*Metodo che identifica i tavoli "vincolati" e setta l'ID_Tavolo di ognuno di essi col nome del relativo vincolo*/

    public void assegnaTavoli(HashMap<String, Integer> mappa) {
        this.tavoliVincolati = new ArrayList<>();
        this.tavoliDisponibili= (ArrayList)tavoli.clone();

        for (Map.Entry<String, Integer> entrata : mappa.entrySet()) {
            for (Tavolo t : tavoliDisponibili) {
                if ((!(t.getIDTavolo().equals(entrata.getKey()))) && (t.getPostiTot() >= entrata.getValue())) {

                    t.setId_tavolo(entrata.getKey());
                    tavoliVincolati.add(t);
                    break;
                }
            }
            tavoliDisponibili.removeAll(tavoliVincolati);
        }

    }

    /*Metodo che prende dall'ID_Inv del vincolo e restituisce il corrispettivo Invitato */

    public Invitato prendiInvitati(String ID) {
        Invitato invi=null;
        for (Invitato i : invitati) {
            if (ID == i.getID_Inv()) {
                invi = i;
                break;
            }
        }
        return invi;
    }

    /*Metodo che sistema gli Invitati con un determinato vincolo nello specifico Tavolo */

    public ArrayList<SpecificaTavolo> accomodaInvitati(ArrayList<SpecificaTavolo> vinc) {
        ArrayList<SpecificaTavolo> copia= (ArrayList<SpecificaTavolo>)vinc.clone();

        for (Tavolo t : tavoli) {
            if (t.getIDTavolo().equals("bambini")){
                Tavolo tav1=t;
            };
        }

        for (SpecificaTavolo sp : copia) {
            for (Tavolo t : tavoli) {
                if (sp.getBambini() == 1 && t.getIDTavolo().equals("bambini") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("babbuini");
                }
                if (sp.getDifficoltaMotorie() == 1 && t.getIDTavolo().equals("difficoltaMotorie") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("shancati");
                }
                if (sp.getTavoloIsolato() == 1 && t.getIDTavolo().equals("tavoloIsolato") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("isole");
                }
                if (sp.getTavoloOnore() == 1 && t.getIDTavolo().equals("tavoloOnore") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("onorati");
                }
                if (sp.getVegetariano() == 1 && t.getIDTavolo().equals("vegetariani") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("debosciati");
                }
                if (sp.getVicinoTV() == 1 && t.getIDTavolo().equals("vicinoTV") && t.getNumPosti() != 0) {
                    t.addGuest(prendiInvitati(sp.getID_Inv()));
                    vinc.remove(sp);
                    System.out.println("tvdrogati");
                }

            }
        }
        return vinc;
    }

    //Metodo utilizzato per gestire gli invitati con piÃ¹ vincoli.
    // Crea un HashMap che ha come chiave il nome dell'Invitato e come valore il numero dei suoi vincoli

    public HashMap<String, Integer> gesticiVincoliPlurimi(){
        int numeroVincoli=0;
        HashMap<String, Integer> vincoliPlurimi= new HashMap<>();
        for (SpecificaTavolo sp: this.piuSpecifiche){
            numeroVincoli=sp.getBambini()+sp.getTavoloIsolato()+sp.getVicinoTV()+sp.getVegetariano()+sp.getDifficoltaMotorie()+sp.getTavoloOnore();
            vincoliPlurimi.put(sp.getID_Inv(), numeroVincoli);
        }
        return vincoliPlurimi;
    }

    //Metodo per controllare se ci sono ancora invitati non sistemati. Conto di quanti posti a sedere necessito per vincolo, ovviamente
    //facendo in modo che un invitato non sia ripetuto nel conteggio

    public void controllaInvitati(){
        if (!this.piuSpecifiche.isEmpty()){
            int bambi=0; int diffMot=0; int tavIs=0; int tavOn=0; int vege=0; int vicTV=0;
            ArrayList<SpecificaTavolo> seduti= new ArrayList<>();
            for(SpecificaTavolo sp: this.piuSpecifiche){
                if (sp.getBambini()!=0){
                    bambi++;
                    seduti.add(sp);
                }
                if (sp.getDifficoltaMotorie()!=0){
                    diffMot++;
                    seduti.add(sp);
                }
                if (sp.getTavoloIsolato()!=0){
                    tavIs++;
                    seduti.add(sp);
                }
                if (sp.getTavoloOnore()!=0){
                    tavOn++;
                    seduti.add(sp);
                }
                if (sp.getVegetariano()!=0){
                    vege++;
                    seduti.add(sp);
                }
                if (sp.getVicinoTV()!=0){
                    vicTV++;
                    seduti.add(sp);
                }

            }

            this.piuSpecifiche.removeAll(seduti);

            unisciTavoli(trovaTavolo("bambini"),bambi);
            unisciTavoli(trovaTavolo("difficoltÃ Motorie"),diffMot);
            unisciTavoli(trovaTavolo("tavoloIsolato"),tavIs);
            unisciTavoli(trovaTavolo("tavoloOnore"),tavOn);
            unisciTavoli(trovaTavolo("vegetariano"),vege);
            unisciTavoli(trovaTavolo("vicinoTV"),vicTV);
        } else {
            chiudiTavolo();
        }
    }

//Metodo che, dato l'ID_Tavolo, mi restituisce il tavolo vincolato

    public Tavolo trovaTavolo(String nomeTavolo){
        Tavolo tavola= null;
        for (Tavolo tav : tavoliVincolati) {
            if (tav.getIDTavolo()==nomeTavolo){
                tavola=tav;
                break;
            }
        }
        return tavola;
    }

    //Metodo che unisce il tavolo vincolato (del vincolo che sto considerando) con un altro tavolo t, in modo che il numero di posti
    //del tavolo vincolato sia aumentato del numero di posti del nuovo tavolo. La condizione affinchÃ¨ questo avvenga Ã¨ che il numero di posti
    //di cui necessito sia almeno la metÃ  del numero di posti del nuovo tavolo.

    public void unisciTavoli (Tavolo tav, int x){
        Collections.sort(tavoli);
        ArrayList<Tavolo> tavoliRimanenti = (ArrayList)tavoli.clone();
        tavoliRimanenti.removeAll(tavoliVincolati);
        for (Tavolo t: tavoliRimanenti){
            if (t.getNumPosti()==t.getPostiTot() && t.getPostiTot()<=2*x){
                tav.setNum_posti(tav.getPostiTot()+t.getPostiTot());
                tavoli.remove(t);
            }
        }
    }


    //Metodo che rende un Tavolo vincolato non disponibile ad altri invitati

    public void chiudiTavolo() {
        for (Tavolo t : tavoliVincolati) {
            if (t.getNumPosti() != 0) {
                t.setDisponibile(false);
            }
        }
    }

    //Metodo che controlla nuovamente la lista "piuSpecifiche": se vi sono ancora presenti vincoli significa che essi non possono
    //essere rispettati, pertanto andranno inserite nella lista "vincoliNonRispettabili"

    public void lastCheck(){
        if(!this.piuSpecifiche.isEmpty()){
            for(SpecificaTavolo sp: piuSpecifiche){
                vincoliNonRispettabili.add(sp);
            }
        }
    }

    public ArrayList<Tavolo> getTavoliVincolati() {
        return tavoliVincolati;
    }
}