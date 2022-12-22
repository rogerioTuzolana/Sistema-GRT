package grt.textui.client;

import grt.textui.main.MenuEntry;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

/**
 *
* @author grupo 6
*/
public class MenuCliente {

    private static int xxx = 1;

    private static void quebralinha() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n\n");
        }
    }

    public static void menu_gestao_cliente() {
        char op;
        do {
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       *           GESTAO DE CLIENTES DA GRT          *");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       |1 - VISUALIZAR TODOS CLIENTES                 |");
            System.out.println("\t\t\t\t\t       |2 - REGISTAR UM NOVO CLIENTE                  |");
            System.out.println("\t\t\t\t\t       |3 - ATIVAR RECEPÇÃO CONTACTO FALHADOS         |");
            System.out.println("\t\t\t\t\t       |4 - DESATIVAR RECEPÇÃO DE CANTACTOS FALHADOS  |");
            System.out.println("\t\t\t\t\t       |5 - CALCULAR O SALDO DE UM CLIENTE            |");
            System.out.println("\t\t\t\t\t       |V - VOLTAR                                    |");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\n\n\n");
            System.out.print("\t\t\t\t\t        ESCOLHA A SUA OPÇÃO: ");
            op = new Scanner(System.in).next().charAt(0);
            switch (op) {
                case '1':
                    quebralinha();
                    visualizar_todos_clientes(MenuEntry.clientes);
                    break;
                case '2':
                    quebralinha();
                    registar_cliente(MenuEntry.clientes);
                    break;
                case '3':
                    quebralinha();
                    ativar_contactos_falhados(MenuEntry.clientes);

                    break;
                case '4':
                    quebralinha();
                    desativar_contactos_falhados(MenuEntry.clientes);
                    break;
                case '5':
                    quebralinha();
                    calcular_saldo(MenuEntry.clientes);
                    break;
                case 'V':
                case 'v':
                    break;
                default:
                    quebralinha();
                    System.out.println("\t\t\t\t\t       Opção Inválida");
            }
        } while (op != 'v' && op != 'V');
    }

    public static void listar_cliente_com_numero(int i, ArrayList<Cliente> clientes) {
        long saldo = 0;
        System.out.print("\t\t\t\t\t       |" + clientes.get(i).getIdCliente() + "|" + clientes.get(i).getNomeCliente() + "|");
        System.out.print(clientes.get(i).getTipoCliente() + "|" + clientes.get(i).getEstadoAtendedor() + "|");
        for (int j = 0; j < clientes.get(i).getNumeroTelefones().size(); j++) {
            String concatenacao = (j + 1 < clientes.get(i).getNumeroTelefones().size()) ? "," : "";
            System.out.print(clientes.get(i).getNumeroTelefones().get(j).getNumero_telefone() + concatenacao);

            saldo = saldo + clientes.get(i).getNumeroTelefones().get(j).getSaldo();
            clientes.get(i).setSaldo(saldo);
        }
        System.out.println("|" + clientes.get(i).getSaldo() + "|");
    }

    public static void visualizar_todos_clientes(ArrayList<Cliente> clientes) {
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *           VISUALIZAÇÃO DE CLIENTE DA GTR           *");
        System.out.println("\t\t\t\t\t       *----------------------------------------------------*");
        for (int i = 0; i < clientes.size(); i++) {
            int qtdeTel = clientes.get(i).getNumeroTelefones().size();

            if (qtdeTel > 0) {
                listar_cliente_com_numero(i, clientes);
            } else {
                System.out.print("\t\t\t\t\t       |" + clientes.get(i).getIdCliente() + "|" + clientes.get(i).getNomeCliente() + "|");
                System.out.print(clientes.get(i).getTipoCliente() + "|" + clientes.get(i).getEstadoAtendedor() + "|");
                System.out.println("0|0|");
            }
        }
        System.out.println("\n\n\n");
        voltar();

    }

    static void listar_cliente_com_numeros(int i, ArrayList<Cliente> clientes) {
        for (int j = 0; j < clientes.get(i).getNumeroTelefones().size(); j++) {
            System.out.print("\t\t\t\t\t       |" + clientes.get(i).getNumeroTelefones().get(j).getNumero_telefone() + "|" + clientes.get(i).getNomeCliente() + "|");
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
        }
    }

    public static void registar_cliente(ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *             REGISTO DE CLIENTE  DA GTR           *");
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\n");
        System.out.print("\t\t\t\t\t           Digite o Nome: ");
        String nome = new Scanner(System.in).nextLine();
        Random id = new Random();

        if (cliente.size() < 999) {
            do {
                xxx = id.nextInt(999) + 1;
            } while (verificar_id_auto_cliente(cliente, (formatar_id() + xxx)) == 1);

            cliente.add(new Cliente("cli" + (formatar_id() + xxx) + "ao", nome, "NORMAL", "ACTIVO", 0));
            System.out.print("\t\t\t\t\t           O cliente foi registado!");
        } else {
            System.out.print("\t\t\t\t\t           A lista de cliente está totalmente preenchida!");
        }
        System.out.println("\n\n\n");
        voltar();

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

    public static void ativar_contactos_falhados(ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *             ATIVAR CONTACTOS FALHADOS            *");
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\n\n\n");
        System.out.print("\t\t\t\t\t           Digite o identificador do cliente: ");
        String id = new Scanner(System.in).nextLine();
        if (verifica_idCliente(id, cliente) > -1) {
            int v = verifica_idCliente(id, cliente);
            if (cliente.get(v).getEstadoAtendedor().equals("ACTIVO")) {
                System.out.println("\t\t\t\t\t       \"alreadyMessageActive()\"");

            } else {
                cliente.get(v).setEstadoAtendedor("ACTIVO");
                System.out.println("\t\t\t\t\t        Estado atendedor do cliente, Ativo!");
            }

        } else {
            System.out.println("\t\t\t\t\t        Cliente não encontrado, verifique o ID!");
        }
        System.out.println("\n\n\n");
        voltar();
    }

    public static void desativar_contactos_falhados(ArrayList<Cliente> cliente) {
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\t\t\t\t\t       *            DESATIVAR CONTACTOS FALHADOS          *");
        System.out.println("\t\t\t\t\t       *--------------------------------------------------*");
        System.out.println("\n\n\n");
        System.out.print("\t\t\t\t\t           Digite o identificador do cliente: ");
        String id = new Scanner(System.in).nextLine();
        if (verifica_idCliente(id, cliente) > -1) {
            int v = verifica_idCliente(id, cliente);
            if (cliente.get(v).getEstadoAtendedor().equals("INACTIVO")) {
                System.out.println("\t\t\t\t\t       \"alreadyMessageInactive()\"");
            } else {
                cliente.get(v).setEstadoAtendedor("INACTIVO");
                System.out.println("\t\t\t\t\t        Estado atendedor do cliente, Inativo!");
            }
        } else {
            System.out.println("\t\t\t\t\t        Cliente não encontrado, verifique o ID!");
        }
        System.out.println("\n\n\n");
        voltar();
    }

    public static void voltar() {
        char op;
        System.out.print("\n\t\t\t\t\t       (*)Voltar__");
        op = new Scanner(System.in).next().charAt(0);
        quebralinha();
    }

    public static int verifica_idCliente(String id, ArrayList<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static String formatar_id() {
        String concatenar = "";
        if (xxx < 10) {
            concatenar = "00";
        } else {
            if (xxx < 100) {
                concatenar = "0";
            } else {
                if (xxx >= 100) {
                    concatenar = "";
                }
            }
        }
        return concatenar;
    }

    public static void calcular_saldo(ArrayList<Cliente> clientes) {
        System.out.println("\t\t\t\t\t  *----------------------------------------------------------------*");
        System.out.println("\t\t\t\t\t  *    CALCULO DE SALDO REFERENTE TODOS TELEFONE  DE UM CLIENTE    *");
        System.out.println("\t\t\t\t\t  *----------------------------------------------------------------*");
        System.out.println("\n\n\n");
        System.out.print("\t\t\t\t\t  Digite o Identificador do cliente: ");
        String id = new Scanner(System.in).nextLine();
        if (verifica_idCliente(id, clientes) > -1) {
            int v = verifica_idCliente(id, clientes);
            long saldo = 0;
            System.out.println("\t\t\t\t\t     Cliente: " + clientes.get(v).getNomeCliente());
            for (int i = 0; i < clientes.get(v).getNumeroTelefones().size(); i++) {
                saldo = saldo + clientes.get(v).getNumeroTelefones().get(i).getSaldo();
            }
            System.out.print("\t\t\t\t\t     O saldo de todos os telemóveis do cliente \"" + clientes.get(v).getNomeCliente() + "\" da operadora de telemóveis é " + saldo);
        } else {
            System.out.print("\t\t\t\t\t               Cliente não encontrado, verifique o ID!");
        }
        System.out.println("\n\n\n");
        voltar();
    }
}
