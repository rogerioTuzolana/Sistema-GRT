/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.consulta;

/**
 *
* @author grupo 6
*/
public class Chamada {

    private int idChamada;
    private int telChamador;
    private int telChamado;
    private String tipo;
    private int duracao;
    private int custo;
    private String estado;

    public Chamada() {
    }

    public Chamada(int idChamada, int telChamador, int telChamado, String tipo, int duracao, int custo, String estado) {
        this.setIdChamada(idChamada);
        this.setTelChamador(telChamador);
        this.setTelChamado(telChamado);
        this.setTipo(tipo);
        this.setDuracao(duracao);
        this.setCusto(custo);
        this.setEstado(estado);
    }

    public int getIdChamada() {
        return idChamada;
    }

    public void setIdChamada(int idChamada) {
        this.idChamada = idChamada;
    }

    public int getTelChamador() {
        return telChamador;
    }

    public void setTelChamador(int telChamador) {
        this.telChamador = telChamador;
    }

    public int getTelChamado() {
        return telChamado;
    }

    public void setTelChamado(int telChamado) {
        this.telChamado = telChamado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
