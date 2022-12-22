
package grt.textui.client;
import grt.textui.oneMobile.MenuEntry.Telemovel;
import java.util.ArrayList;
/**
 *
* @author grupo 6
*/
public class Cliente {
    private String idCliente;
    private String nomeCliente;
    private String tipoCliente;
    private String estadoAtendedor;
    private  ArrayList<Telemovel> numeroTelefones = new ArrayList<Telemovel>();
    private long saldo;
    public Cliente(String chave, String nome,String tipoCliente,String estado,long saldo) {
        this.idCliente= chave;
        this.nomeCliente=nome;
        this.tipoCliente = tipoCliente;
        this.estadoAtendedor=estado;
        //this.numeroTelefones= 
        this.saldo=saldo;
    }
    
    public Cliente(){
    }
    
    public void setEstadoAtendedor(String estadoAtendedor) {
        this.estadoAtendedor = estadoAtendedor;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getEstadoAtendedor() {
        return estadoAtendedor;
    }

    public ArrayList<Telemovel> getNumeroTelefones() {
        return numeroTelefones;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setNumeroTelefones(ArrayList<Telemovel> numeroTelefones) {
        this.numeroTelefones = numeroTelefones;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }
    
}
