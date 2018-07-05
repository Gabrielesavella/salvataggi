package gui.controller;


import facade.*;
import locale.GestoreEvento;
import persone.Cliente;
import persone.Invitato;
import vincoli.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author lecciovich
 */


public class SistemaDiPrenotazioneController{
    private boolean loggedIn=false;
    private AbstractFacade facade;
    private XlsFacade xlsFacade;

    public SistemaDiPrenotazioneController(){
        try {
            facade=new txtFacade(1);
            xlsFacade=new XlsFacade();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean signUp(String username,String password,String name,String surname,String email){
        try {
            Cliente fetching=facade.fetchClient(username,password);
            if (fetching!=null){
                System.out.println("found client with same username. Please try again with a different one.");
                return false;
            }
            facade.WriteClient(username,password,name,surname,email);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Cliente login(String username, String password){
        Cliente fetching= null;
        try {
            fetching = facade.fetchClient(username,password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fetching!=null) { loggedIn = true; }
        return fetching;
    }


    public void logout(){
        loggedIn=false;
    }


    public boolean creaEvento(String nomeEvento, GregorianCalendar data, int guestNum, Cliente cliente){
        try {
            facade.WriteEvent(nomeEvento,data,guestNum);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public GestoreEvento getEvento(String nomeEvento){
        String[] columns= new String[10];
        GestoreEvento gestoreEvento =null;
        try {
            gestoreEvento =facade.fetch(nomeEvento,columns);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gestoreEvento;
    }


    public boolean acquisisciInvitati(ArrayList<Invitato> invitati){
        for (Invitato i:invitati) {
            try {
                facade.WriteGuests(i.getID_Inv(),i.getNome(),i.getCognome(),i.getEta());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ArrayList<Invitato> getInvitati(){
        ArrayList<Invitato> invitati= null;
        try {
            txtFacade txtFacade= new txtFacade(1);
            invitati.addAll(txtFacade.fetchAllGuests());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return invitati;
    }

    public boolean createXlsGenerality(String nomeEvento){
        xlsFacade.generateXlsGuests(nomeEvento);
        try {
            xlsFacade.openfile(nomeEvento+".xls");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Invitato> loadXlsGenerality(String nomeEvento){
        ArrayList<Invitato> listainvitatiEvento=null;
        try {
            listainvitatiEvento = xlsFacade.readXlsGuests(nomeEvento);

        int sizelist = listainvitatiEvento.size();
        txtFacade t = new txtFacade(nomeEvento+"lista_invitati.txt",sizelist);
        for (Invitato element:listainvitatiEvento) {
            t.WriteGuests(element.getID_Inv(),element.getNome(),element.getCognome(),element.getEta());
        }
        }catch (IOException e){ e.printStackTrace(); }

        return listainvitatiEvento ;
    }

    public ArrayList<Invitato> writeXlsObligations(String nomeEvento){
        if (!xlsFacade.reWriteXls(nomeEvento,loadXlsGenerality(nomeEvento))){
            return null;
        }
        return loadXlsGenerality(nomeEvento);
    }

    public ArrayList<SpecificaTavolo> saveOnObligations(String nomeEvento){
        ArrayList<SpecificaTavolo> specificaTavolos=xlsFacade.readSpecificheTavolo(nomeEvento);
//        if (specificaTavolos==null){
//            System.out.println("no buono");
//        }
//        System.out.println("yuppi");
        return specificaTavolos;
    }

}

