/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.mobile;

import grt.textui.client.Cliente;
import static grt.textui.client.MenuCliente.ativar_contactos_falhados;
import static grt.textui.client.MenuCliente.calcular_saldo;
import static grt.textui.client.MenuCliente.desativar_contactos_falhados;
import static grt.textui.client.MenuCliente.formatar_id;
import static grt.textui.client.MenuCliente.registar_cliente;
import static grt.textui.client.MenuCliente.visualizar_todos_clientes;
import static grt.textui.client.MenuCliente.voltar;
import grt.textui.client.UnknownClientKeyException;

import grt.textui.main.MenuEntry;
import static grt.textui.oneMobile.MenuEntry.MenuTelemovel.menu_gestao_telemovel;
import grt.textui.oneMobile.MenuEntry.Telemovel;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *
 * @author grupo 6
 */
public class MenuTelemoveis {

    private static void quebralinha() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
    }

    public static void menu_gestao_telemoveis() throws InterruptedException {
        char op;
        do {
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       *          GESTAO DE TELEMOVEIS DA GRT         *");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       |1 - VISUALIZAR TODOS TELEMOVEIS               |");
            System.out.println("\t\t\t\t\t       |2 - REGISTAR UM NOVO TELEMÓVEL                |");
            System.out.println("\t\t\t\t\t       |3 - GESTAO DO TELEMÓVEL                       |");
            System.out.println("\t\t\t\t\t       |V - VOLTAR                                    |");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\n\n\n");
            System.out.print("\t\t\t\t\t        ESCOLHA A SUA OPÇÃO: ");
            op = new Scanner(System.in).next().charAt(0);
            switch (op) {
                case '1':
                    quebralinha();
                    visualizar_todos_telemoveis(MenuEntry.clientes);
                    break;
                case '2':
                    quebralinha();
                    registar_telemovel(MenuEntry.clientes);

                    break;
                case '3':
                    quebralinha();
                    numero_gestao_telemovel(MenuEntry.clientes);
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

    public static void listar_cliente_com_numeros(int i, ArrayList<Cliente> clientes) {
        for (int j = 0; j < clientes.get(i).getNumeroTelefones().size(); j++) {
            System.out.print("\t\t\t\t\t       |" + clientes.get(i).getNumeroTelefones().get(j).getNumero_telefone() + "|" + clientes.get(i).getIdCliente() + "|");
            System.out.print(clientes.get(i).getNumeroTelefones().get(j).getEstado() + "|" + clientes.get(i).getNumeroTelefones().get(j).getSaldo() + "|");
            if (clientes.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size() > 0) {
                for (int k = 0; k < clientes.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size(); k++) {
                    String concatenacao = (k + 1 < clientes.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size()) ? "," : "";
                    System.out.print(clientes.get(i).getNumeroTelefones().get(j).getTelefones_amigo().get(k).getAmigos() + concatenacao);
                }
                System.out.print("|");

            } else {
                System.out.print(" |");
            }
            System.out.print("\n");
        }
    }

    public static void visualizar_todos_telemoveis(ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *          VISUALIZAÇÃO DE TELEMOVEIS DA GTR         *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");

        for (int i = 0; i < cliente.size(); i++) {
            listar_cliente_com_numeros(i, cliente);
        }
        System.out.println("\n\n\n");
        voltar();
    }

    public static void adicionar_numero(ArrayList<Cliente> cliente, String id, int numero) {
        if (verificar_numero(cliente, numero) == -1) {
            int posicao = verifica_idCliente(id, cliente);
            if (posicao > -1) {
                cliente.get(posicao).getNumeroTelefones().add(new Telemovel(numero, 0, "mobileOn()"));
                System.out.println("\n\t\t\t\t\t         " + cliente.get(posicao).getNomeCliente() + "\n\t\t\t\t\t         Número adicionado!");
                System.out.println("\n\n\n");
                voltar();
            }
        }
    }

    public static void registar_telemovel(ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *             REGISTO DE TELEMOVEL DA GTR          *");
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\n\n\n");
        int numero = 0, numero_final = 0;
        int n_padrao = numero_automatico(cliente);
        boolean v = true;
        do {
            try {
                System.out.print("\t\t\t\t\t           Digite o número( com 6 dígitos): " + n_padrao);
                numero = new Scanner(System.in).nextInt();
                numero_final = Integer.parseInt(n_padrao + "" + numero);

                v = false;
            } catch (InputMismatchException e) {
                quebralinha();
                System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
                System.out.println("\t\t\t\t\t       *             REGISTO DE TELEMOVEL DA GTR          *");
                System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
                System.out.println("\n\n\n");
                System.out.println("\t\t\t\t\t            \"Digite número com 6 dígitos\" ");
            }
        } while (v);

        if (verifica_tamanho_numero(numero_final) < 6 || verifica_tamanho_numero(numero_final) > 6) {
            System.out.println("\n\t\t\t\t\t       Número de dígitos e inferior ou superior que 6!");
            voltar_registo_numero(cliente);
        } else {
            if (verificar_numero(cliente, numero_final) != -1) {
                System.out.println("\t\t\t\t\t           \"duplicateMobile()\"");
                voltar_registo_numero(cliente);
            } else {
                System.out.print("\t\t\t\t\t           Digite o identificador do cliente: ");
                String id = new Scanner(System.in).nextLine();
                try {
                    autenticar_id(cliente, id, numero_final);
                } catch (UnknownClientKeyException e) {
                    System.err.println(e.getMessage());
                    voltar_registo_numero(cliente);
                }
                /*if (verifica_idCliente(id, cliente) == -1) {
                 System.out.println("\n\t\t\t\t\t       ID \"" + id + "\" não encontrado!");
                    
                 voltar_registo_numero(cliente);
                 } else {
                 adicionar_numero(cliente, id, numero_final);
                 }*/
            }
        }
    }

    static void autenticar_id(ArrayList<Cliente> cliente, String id, int numero_final) throws UnknownClientKeyException {
        if (verifica_idCliente(id, cliente) == -1) {
            throw new UnknownClientKeyException("\t\t\t\t\t       ID \"" + id + "\" não encontrado!");
        } else {
            adicionar_numero(cliente, id, numero_final);
        }
    }

    static int numero_automatico(ArrayList<Cliente> cliente) {
        Random id = new Random();
        int numero1 = 9;
        int numero2 = id.nextInt(4) + 1;
        if (numero2 == 1) {
            numero2++;
        }
        return Integer.parseInt(numero1 + "" + numero2);
    }

    static int verificar_id_auto_cliente(ArrayList<Cliente> cliente, String id) {
        int cont = 0;
        String idCli = "cli" + id + "ao";
        for (int i = 0; i < cliente.size(); i++) {
            if (idCli.equals(cliente.get(i).getIdCliente())) {
                cont++;
            }
        }
        if (cont == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void voltar_registo_numero(ArrayList<Cliente> cliente) {
        System.out.println("\n\t\t\t\t\t           Desejas continuar ?\n\t\t\t\t\t           1-Sim\n\t\t\t\t\t           2-Não");
        System.out.print("\n\t\t\t\t\t        ESCOLHA A SUA OPÇÃO: ");
        char op = new Scanner(System.in).next().charAt(0);
        switch (op) {
            case '1':
                quebralinha();
                registar_telemovel(cliente);
                break;
            default:
                break;
        }
    }

    public static int verificar_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return j;
                }
            }
        }
        return -1;
    }

    public static int verificar_cliente_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int verifica_idCliente(String id, ArrayList<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static int verifica_tamanho_numero(int numero) {
        String num = numero + "";
        return num.length();
    }

    public static void voltar() {
        char op;
        System.out.print("\n\t\t\t\t\t       (*)Voltar__");
        op = new Scanner(System.in).next().charAt(0);
        quebralinha();
    }

    static void autenticar_numero(ArrayList<Cliente> cliente, int numero) throws UnknownKeyException, InterruptedException {
        if (verificar_numero(cliente, numero) == -1) {
            throw new UnknownKeyException("\t\t\t\t\t           Desejas continuar ?\n\t\t\t\t\t           1-Sim\n\t\t\t\t\t           2-Não");
        } else {
            menu_gestao_telemovel(numero);
        }
    }

    public static void numero_gestao_telemovel(ArrayList<Cliente> cliente) throws InterruptedException {

        try {
            System.out.print("\t\t\t\t\t        Digite o número a gerir: ");
            int numero = new Scanner(System.in).nextInt();
            autenticar_numero(cliente, numero);
        } catch (UnknownKeyException e) {
            System.out.println(e.getMessage());
            System.out.print("\n\t\t\t\t\t         ESCOLHA A SUA OPÇÃO: ");
            char op = new Scanner(System.in).next().charAt(0);

            switch (op) {
                case '1':
                    quebralinha();
                    numero_gestao_telemovel(cliente);
                    break;
                case '2':
                    quebralinha();
                    break;
                default:
                    quebralinha();
                    System.out.println("\t\t\t\t\t         Opção Inválida");
                    menu_gestao_telemoveis();
            }

        }
        catch(java.util.InputMismatchException e){
            System.err.println("\t\t\t\t\t         Insira apenas numeros!!!\n");
            numero_gestao_telemovel(cliente);
        }
    }
}
