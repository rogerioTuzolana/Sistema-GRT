/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.oneMobile.MenuEntry;

import grt.textui.oneMobile.contactos.TelemovelAmigo;
import java.util.ArrayList;

/**
 *
 * * @author grupo 6
 */
public class Telemovel {

    private int numero_telefone;
    private int saldo;
    private ArrayList<TelemovelAmigo> telefones_amigos;
    //public ArrayList<Integer> chamada = new ArrayList();
    private String estado;

    public Telemovel(int numero_telefone, int saldo, String estado) {
        this.numero_telefone = numero_telefone;
        this.saldo = saldo;
        this.telefones_amigos  = new ArrayList();
        //this.chamada = chamada;
        this.estado= estado;
    }
    
    public void setNumero_telefone(int numero_telefone) {
        this.numero_telefone = numero_telefone;
    }

    public int getNumero_telefone() {
        return numero_telefone;
    }

    public int getSaldo() {
        return saldo;
    }

    public ArrayList<TelemovelAmigo> getTelefones_amigo() {
        return telefones_amigos;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

}
