package vincoli;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import persone.Invitato;

import java.util.ArrayList;

/**
 *
 * @author Salvatore Parisi
 */
public class SpecificaTavolo implements Vincolo {

    private String ID_Evento, ID_Inv;
    int tavoloOnore, difficoltaMotorie, vegetariano, vicinoTV, bambini, tavoloIsolato;

    public SpecificaTavolo (String ID_Evento, String ID_Inv, int tavoloOnore, int difficoltaMotorie, int vegetariano, int vicinoTV, int bambini, int tavoloIsolato){
        this.ID_Evento=ID_Evento;
        this.ID_Inv=ID_Inv;
        this.bambini=bambini;
        this.difficoltaMotorie=difficoltaMotorie;
        this.tavoloIsolato=tavoloIsolato;
        this.tavoloOnore=tavoloOnore;
        this.vegetariano=vegetariano;
        this.vicinoTV=vicinoTV;


    }

    public String getID_Evento() {
        return ID_Evento;
    }

    public String getID_Inv() {
        return ID_Inv;
    }

    public int getTavoloOnore() {
        return tavoloOnore;
    }

    public int getDifficoltaMotorie() {
        return difficoltaMotorie;
    }

    public int getVegetariano() {
        return vegetariano;
    }

    public int getVicinoTV() {
        return vicinoTV;
    }

    public int getBambini() {
        return bambini;
    }

    public int getTavoloIsolato() {
        return tavoloIsolato;
    }
}


