/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.consulta;

import grt.textui.client.Cliente;
import grt.textui.main.MenuEntry;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
* @author grupo 6
*/
public class MenuConsultas {

    private static void quebralinha() {
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }

    public static void menu_consultas() {
        char op;
        do {
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       *          GESTAO DE CONSULTAS DA GRT          *");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       |1 - CONSULTAR TODAS CHAMADAS                  |");
            System.out.println("\t\t\t\t\t       |2 - CONSULTAR CHAMADA DE UM CLIENTE           |");
            System.out.println("\t\t\t\t\t       |3 - CONSULTAR TELEMOVEIS SEM ATIVIDADE        |");
            System.out.println("\t\t\t\t\t       |4 - CONSULTAR TELEMOVEIS COM SALDO POSITIVO   |");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.print("\t\t\t\t\t        ESCOLHA A SUA OPÇÃO: ");
            op = new Scanner(System.in).next().charAt(0);
            switch (op) {
                case '1':
                    quebralinha();
                    consultar_todas_chamadas(MenuEntry.chamadas);
                    break;
                case '2':
                    quebralinha();
                    consultar_chamadas_cliente(MenuEntry.chamadas, MenuEntry.clientes);
                    break;
                case '3':
                    quebralinha();
                    consultar_telemoveis_sem_atividade(MenuEntry.chamadas, MenuEntry.clientes);
                    break;
                case '4':
                    quebralinha();
                    consultar_telemoveis_saldo_positivo(MenuEntry.chamadas, MenuEntry.clientes);
                    break;
                case 'V':
                    break;
                case 'v':
                    break;
                default:
                    quebralinha();
                    System.out.println("\t\t\t\t\t           Opção Inválida");
            }
        } while (op != 'v' && op != 'V');
    }

    static void consultar_todas_chamadas(ArrayList<Chamada> chamadas) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *    CONSULTAR TODAS AS CHAMADAS DO CLIENTE DA GTR   *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        int qtdeChamadas = chamadas.size();
        if (qtdeChamadas > 0) {
            for (int i = 0; i < chamadas.size(); i++) {
                System.out.print("\t\t\t\t\t       |" + chamadas.get(i).getIdChamada() + "|" + chamadas.get(i).getTelChamador() + "|");
                System.out.print(chamadas.get(i).getTelChamado() + "|" + chamadas.get(i).getTipo() + "|");
                System.out.println(chamadas.get(i).getDuracao() + "|" + chamadas.get(i).getCusto() + "|" + chamadas.get(i).getEstado() + "|");

            }
        } else {
            System.out.println("\t\t\t\t                  “Não existem informações que satisfaçam o seu pedido”");
        }
        voltar();
    }

    static void consultar_chamadas_cliente(ArrayList<Chamada> chamadas, ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *       CONSULTAR CHAMADAS DE UM CLIENTE DA GTR      *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.print("\t\t\t\t\t       Digite o identificador do cliente: ");
        String idCli = new Scanner(System.in).nextLine();
        int posicao_cliente = verifica_idCliente(idCli, cliente);
        if (posicao_cliente > -1) {
            for (int i = 0; i < cliente.get(posicao_cliente).getNumeroTelefones().size(); i++) {
                for (int j = 0; j < chamadas.size(); j++) {
                    if (cliente.get(posicao_cliente).getNumeroTelefones().get(i).getNumero_telefone() == chamadas.get(j).getTelChamador()) {
                        System.out.print("\t\t\t\t\t       |" + chamadas.get(j).getIdChamada() + "|" + chamadas.get(j).getTelChamador() + "|");
                        System.out.print(chamadas.get(j).getTelChamado() + "|" + chamadas.get(j).getTipo() + "|");
                        System.out.println(chamadas.get(j).getDuracao() + "|" + chamadas.get(j).getCusto() + "|" + chamadas.get(j).getEstado() + "|");
                    }
                }

            }
        } else {
            System.out.println("\t\t\t\t                  “Não existem informações que satisfaçam o seu pedido”");
        }
        voltar();
    }

    static ArrayList listar_cliente_com_numeros(ArrayList<Cliente> clientes) {
        ArrayList<Integer> listaNumero = new ArrayList();
        for (int i = 0; i < clientes.size(); i++) {
            for (int j = 0; j < clientes.get(i).getNumeroTelefones().size(); j++) {
                listaNumero.add(clientes.get(i).getNumeroTelefones().get(j).getNumero_telefone()
                );

            }
        }
        return listaNumero;
    }

    static void consultar_telemoveis_sem_atividade(ArrayList<Chamada> chamadas, ArrayList<Cliente> clientes) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *      CONSULTAR TELEMOVEIS SEM ATIVIDADE DA GTR     *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        ArrayList listaNumero = new ArrayList();
        int tamanhoLista = listar_cliente_com_numeros(clientes).size();
        int quantidade = 0;
        if (tamanhoLista > 0) {
            for (int i = 0; i < listar_cliente_com_numeros(clientes).size(); i++) {
                for (int j = 0; j < chamadas.size(); j++) {
                    if (listar_cliente_com_numeros(clientes).get(i).equals(chamadas.get(j).getTelChamador()) || listar_cliente_com_numeros(clientes).get(i).equals(chamadas.get(j).getTelChamado())) {
                        quantidade++;
                    }
                }
                if (quantidade == 0) {
                    System.out.println("\t\t\t\t\t       |" + listar_cliente_com_numeros(clientes).get(i).toString() + "|");
                }
                quantidade = 0;
            }
        } else {
            System.out.println("\t\t\t\t                  “Não existem informações que satisfaçam o seu pedido”");
        }
        voltar();
    }

    static ArrayList listar_telemovel_saldo_positivo(ArrayList<Cliente> clientes) {
        ArrayList<Integer> listaNumero = new ArrayList();
        for (int i = 0; i < clientes.size(); i++) {
            for (int j = 0; j < clientes.get(i).getNumeroTelefones().size(); j++) {
                if (clientes.get(i).getNumeroTelefones().get(j).getSaldo() > 0) {
                    listaNumero.add(clientes.get(i).getNumeroTelefones().get(j).getNumero_telefone()
                    );
                }
            }
        }
        return listaNumero;
    }

    static void consultar_telemoveis_saldo_positivo(ArrayList<Chamada> chamadas, ArrayList<Cliente> clientes) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *       CONSULTAR TELEMOVEIS COM SALDO POSITIVO      *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        int tamanhoLista = listar_telemovel_saldo_positivo(clientes).size();
        if (tamanhoLista > 0) {
            for (int i = 0; i < listar_telemovel_saldo_positivo(clientes).size(); i++) {
                System.out.println("\t\t\t\t\t       |" + listar_telemovel_saldo_positivo(clientes).get(i).toString() + "|");
            }
        } else {
            System.out.println("\t\t\t\t                  “Não existem informações que satisfaçam o seu pedido”");
        }
        voltar();
    }

    static int verifica_idCliente(String idCli, ArrayList<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(idCli)) {
                return i;
            }
        }
        return -1;
    }

    static void voltar() {
        char op;
        System.out.print("\n\t\t\t\t\t       1-Voltar__");
        op = new Scanner(System.in).next().charAt(0);
        quebralinha();
    }
}
