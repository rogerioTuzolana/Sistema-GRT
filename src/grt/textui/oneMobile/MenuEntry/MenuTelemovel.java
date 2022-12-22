/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.oneMobile.MenuEntry;

import grt.textui.consulta.Chamada;
import grt.textui.client.Cliente;
import grt.textui.main.MenuEntry;
import static grt.textui.main.MenuEntry.chamadas;
import grt.textui.mobile.MenuTelemoveis;
import static grt.textui.mobile.MenuTelemoveis.registar_telemovel;
import static grt.textui.mobile.MenuTelemoveis.visualizar_todos_telemoveis;
import grt.textui.mobile.UnknownKeyException;
import grt.textui.oneMobile.contactos.TelemovelAmigo;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 ** @author grupo 6
 */
public class MenuTelemovel {

    private static void quebralinha() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
    }
    static int idChamada = 0;
    static int cont_chamada_msg = 0;
    static int cont_chamada_voz = 0;

    public static void menu_gestao_telemovel(int numeroTelemovel) throws InterruptedException {
        String idClient = id_cliente(MenuEntry.clientes, numeroTelemovel);
        char op;
        do {
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       *    GESTAO DO TELEMOVEL DO CLIENTE " + idClient + "   *");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.println("\t\t\t\t\t       |1 - LIGAR TELEMOVEL                           |");
            System.out.println("\t\t\t\t\t       |2 - COLOCAR TELEMÓVEL NO SILÊNCIO             |");
            System.out.println("\t\t\t\t\t       |3 - DESLIGAR TELEMÓVEL                        |");
            System.out.println("\t\t\t\t\t       |4 - ADICIONAR AMIGO                           |");
            System.out.println("\t\t\t\t\t       |5 - RETIRAR AMIGO                             |");
            System.out.println("\t\t\t\t\t       |6 - PAGAMENTO                                 |");
            System.out.println("\t\t\t\t\t       |7 - CONSULTAR SALDO                           |");
            System.out.println("\t\t\t\t\t       |8 - ESTABELECER LIGAÇÃO                       |");
            System.out.println("\t\t\t\t\t       |V - VOLTAR                                    |");
            System.out.println("\t\t\t\t\t       *----------------------------------------------*");
            System.out.print("\t\t\t\t\t        ESCOLHA A SUA OPÇÃO: ");
            op = new Scanner(System.in).next().charAt(0);
            switch (op) {
                case '1':
                    quebralinha();
                    ligar_telemovel(MenuEntry.clientes, numeroTelemovel);

                    break;
                case '2':
                    quebralinha();
                    telemovel_silencio(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '3':
                    quebralinha();
                    desligar_telemovel(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '4':
                    quebralinha();
                    adicionar_amigo(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '5':
                    quebralinha();
                    eliminar_amigo(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '6':
                    pagamento_verificado(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '7':
                    quebralinha();
                    consultar_saldo(MenuEntry.clientes, numeroTelemovel);
                    break;
                case '8':
                    quebralinha();
                    estabelecer_ligacao(MenuEntry.clientes, numeroTelemovel);
                    break;
                case 'V':
                    cont_chamada_voz = 0;
                    quebralinha();
                    break;
                case 'v':
                    cont_chamada_voz = 0;
                    quebralinha();
                    break;
                default:
                    quebralinha();
                    System.out.println("\t\t\t\t\t           Opção Inválida");
            }
        } while (op != 'v' && op != 'V');
    }

    public static String id_cliente(ArrayList<Cliente> cliente, int numeroTelemovel) {
        int posicao_cliente = verificar_linha_numero(cliente, numeroTelemovel);
        return cliente.get(posicao_cliente).getIdCliente();
    }

    public static int Estado(ArrayList<Cliente> cliente, int numeroTelemovel) {
        int posicao_cliente = verificar_linha_numero(cliente, numeroTelemovel);
        int posicao_num_cliente = verificar_coluna_numero(cliente, numeroTelemovel);
        if (cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_num_cliente).getEstado().equals("mobileOn()")) {
            return 1;
        } else {
            if (cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_num_cliente).getEstado().equals("mobileOff()")) {
                return 0;
            }
            return -1;
        }
    }

    /* INICIO ---Metodos para chamada---*/
    static void quebra() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
    }

    static void simulacao_chamada() throws InterruptedException {
        quebra();
        String conc = ".";
        int c = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("\t\t\t\t\t           A Chamar" + conc);
            if (i == c) {
                conc = conc + '.';
                c++;
            }
            if (conc.equals("....")) {
                conc = ".";
            }
            new Thread().sleep(1000);
            quebra();

        }
    }

    public static void tipo_chamada(ArrayList<Cliente> cliente, int telChamador, int telChamado) throws InterruptedException {
        System.out.println("\t\t\t\t\t           typeReq():\n\t\t\t\t\t           1-VOZ\n\t\t\t\t\t           2-SMS");
        System.out.print("\t\t\t\t\t           Escolha a sua opção: ");
        char op = new Scanner(System.in).next().charAt(0);
        switch (op) {
            case '1':
                chamada_voz(cliente, telChamador, telChamado);
                break;
            case '2':
                chamada_sms(cliente, telChamador, telChamado);
                break;
            default:
                quebralinha();
                tipo_chamada(cliente, telChamador, telChamado);
                break;
        }

    }

    static void autenticar_numero(ArrayList<Cliente> cliente, int telChamador, int telChamado) throws UnknownKeyException, InterruptedException {
        if (verificar_coluna_numero(cliente, telChamado) > -1) {
            tipo_chamada(cliente, telChamador, telChamado);
        } else {
            //System.err.println("\t\t\t\t\t           O número de telemóvel que pretende contactar não existe");
            throw new UnknownKeyException("\t\t\t\t\t           O número do telemóvel que pretendes contactar não existe");
        }
    }

    static void estabelecer_ligacao(ArrayList<Cliente> cliente, int telChamador) throws InterruptedException {
        int posicao_cliente = verificar_linha_numero(cliente, telChamador);
        int posicao_num_cliente = verificar_coluna_numero(cliente, telChamador);
        if (!cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_num_cliente).getEstado().equals("mobileOff()")) {
            System.out.print("\t\t\t\t\t           Digite o número a chamar: ");
            int telChamado = new Scanner(System.in).nextInt();
            if (!(telChamado == telChamador)) {
                try {
                    autenticar_numero(cliente, telChamador, telChamado);
                } catch (UnknownKeyException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.out.println("\t\t\t\t\t           Falha na ligação! Call yourself.");
                voltar();
            }
        } else {
            System.out.print("\t\t\t\t\t           O telemovel está desligado\n\t\t\t\t\t           (*)Voltar__");
            voltar();
        }

        quebralinha();
    }

    static void tipoCliente(ArrayList<Cliente> cliente, int numeroTelemovel, String tipoChamada, int tentativa) {
        int posicao_cliente = verificar_linha_numero(cliente, numeroTelemovel);
        if (cliente.get(posicao_cliente).getSaldo() == 0) {
            cliente.get(posicao_cliente).setTipoCliente("NORMAL");
        }
        if (tentativa == 6 && cliente.get(posicao_cliente).getSaldo() > 500 && tipoChamada.equals("voiceMessage()")) {
            cliente.get(posicao_cliente).setTipoCliente("PLATINA");
            //System.out.println("yaaaaaaaaaa");
        }
        if (tentativa == 3 && cliente.get(posicao_cliente).getTipoCliente().equals("PLATINA") && tipoChamada.equals("textMessage()")) {
            cliente.get(posicao_cliente).setTipoCliente("OURO");
            //System.out.println("yeeeeeeee");
        }
    }

    static void continuar_chamada(ArrayList<Cliente> cliente, int telChamador, int telChamado, int duracao, int custo, String tipoChamada) throws InterruptedException {
        char op;
        System.out.println("\n\t\t\t\t\t           Desejas chamar mais?\n");
        System.out.println("\t\t\t\t\t           1-Estabelecer outra ligação:\n\t\t\t\t\t           (*)Voltar__");
        System.out.print("\t\t\t\t\t           Escolha a sua opção: ");
        op = new Scanner(System.in).next().charAt(0);
        switch (op) {
            case '1':
                quebralinha();
                estabelecer_ligacao(cliente, telChamador);
                break;
            default:
                break;
        }
    }

    /* FIM ---Metodos para chamada---*/

 /* INICIO ---Metodos para chamada VOZ---*/
    static int custo_por_minuto(ArrayList<Cliente> cliente, int telChamador, int custo, int time, int tarifario) {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);
        int custo_min = 0;
        if (custo > cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo()) {
            for (int i = 1; i <= time; i++) {

                if (custo_min < cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo()) {
                    custo_min = i * tarifario;
                }
            }
        }
        return custo_min;
    }

    static int minuto_do_custo(ArrayList<Cliente> cliente, int telChamador, int custo, int time, int tarifario) {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);
        int custo_min = 0, min = 0;
        if (custo > cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo()) {
            for (int i = 1; i <= time; i++) {
                if (custo_min < cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo()) {
                    custo_min = i * tarifario;
                    min++;
                }
            }
        }
        return min;
    }

    static void chamada_voz(ArrayList<Cliente> cliente, int telChamador, int telChamado) throws InterruptedException {
        boolean v = true;
        int time = 0;
        do {
            System.out.print("\t\t\t\t\t         Duração da chamada, timeReq(): ");
            try {
                time = new Scanner(System.in).nextInt();
                v = false;
            } catch (InputMismatchException e) {
                quebralinha();
                System.err.print("\n\t\t\t\t\t         Voce digitou caracter nao esperado! Digite novamente.");
            }

        } while (v);

        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);

        String tipoChamada = "voiceMessage()";
        int custo = 0, custo_min = 0, min_custo = 0;
        if (cliente.get(posicaoChamador).getTipoCliente().equals("NORMAL")) {
            custo = time * 20;
            custo_min = custo_por_minuto(cliente, telChamador, custo, time, 20);
            min_custo = minuto_do_custo(cliente, telChamador, custo, time, 20);
            for (int i = 0; i < cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().size(); i++) {

                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().get(i).getAmigos() == telChamado) {
                    float c = custo;
                    float b = (float) (50.0 / 100.0);
                    custo = (int) ((int) c - c * b);
                }
            }
            if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                finaliza_chamada_voz(cliente, telChamador, telChamado, time, custo, tipoChamada, true);
            } else {
                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo_min && custo_min > 0) {
                    finaliza_chamada_voz(cliente, telChamador, telChamado, min_custo, custo_min, tipoChamada, false);
                } else {
                    System.out.println("\t\t\t\t\t           Saldo insuficiênte! No mínimo faz um carregamento de 20Kz.");
                    voltar();
                }
            }
        } else {
            if (cliente.get(posicaoChamador).getTipoCliente().equals("OURO")) {
                custo = time * 10;
                custo_min = custo_por_minuto(cliente, telChamador, custo, time, 10);
                min_custo = minuto_do_custo(cliente, telChamador, custo, time, 10);
                for (int i = 0; i < cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().size(); i++) {

                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().get(i).getAmigos() == telChamado) {
                        float c = custo;
                        float b = (float) (50.0 / 100.0);
                        custo = (int) ((int) c - c * b);
                    }
                }
                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                    finaliza_chamada_voz(cliente, telChamador, telChamado, time, custo, tipoChamada, true);
                } else {
                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo_min && custo_min > 0) {
                        finaliza_chamada_voz(cliente, telChamador, telChamado, min_custo, custo_min, tipoChamada, false);
                    } else {
                        System.out.println("\t\t\t\t\t           Saldo insuficiênte! No mínimo faz um carregamento de 10Kz.");
                        voltar();
                    }
                }
            } else {
                if (cliente.get(posicaoChamador).getTipoCliente().equals("PLATINA")) {
                    custo = time * 10;
                    custo_min = custo_por_minuto(cliente, telChamador, custo, time, 10);
                    min_custo = minuto_do_custo(cliente, telChamador, custo, time, 10);
                    for (int i = 0; i < cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().size(); i++) {

                        if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getTelefones_amigo().get(i).getAmigos() == telChamado) {
                            float c = custo;
                            float b = (float) (50.0 / 100.0);
                            custo = (int) ((int) c - c * b);
                        }
                    }
                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                        finaliza_chamada_voz(cliente, telChamador, telChamado, time, custo, tipoChamada, true);
                    } else {
                        if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo_min && custo_min > 0) {
                            finaliza_chamada_voz(cliente, telChamador, telChamado, min_custo, custo_min, tipoChamada, false);
                        } else {
                            System.out.println("\t\t\t\t\t           Saldo insuficiênte! No mínimo faz um carregamento de 10Kz.");
                            voltar();
                        }
                    }
                }
            }
        }
    }

    static void finaliza_chamada_voz(ArrayList<Cliente> cliente, int telChamador, int telChamado, int duracao, int custo, String tipoChamada, boolean estado) throws InterruptedException {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);
        int posicaoChamado = verificar_linha_numero(cliente, telChamado);
        int posicao_num_chamado = verificar_coluna_numero(cliente, telChamado);
        simulacao_chamada();
        if (cliente.get(posicaoChamado).getNumeroTelefones().get(posicao_num_chamado).getEstado().equals("mobileOn()") && tipoChamada.equals("voiceMessage()")) {
            cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).setSaldo(cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() - custo);
            cliente.get(posicaoChamador).setSaldo(cliente.get(posicaoChamador).getSaldo() - custo);
            cont_chamada_voz++;
            /**/
            tipoCliente(cliente, telChamador, tipoChamada, cont_chamada_voz);
            quebralinha();
            if (estado == true) {
                System.out.println("\t\t\t\t\t           Chamada efectuada para " + telChamado + "! Duração: " + duracao + "min");
            } else {
                System.out.println("\t\t\t\t\t           Chamada efectuada mas não terminada para " + telChamado + "! Duração: " + duracao + "min");
            }
            do {
                idChamada++;
            } while (verificar_id_chamada(chamadas, idChamada) > -1);
            chamadas.add(new Chamada(idChamada, telChamador, telChamado, tipoChamada, duracao, custo, "done()"));

            continuar_chamada(cliente, telChamador, telChamado, duracao, custo, tipoChamada);
        } else {
            if (cliente.get(posicaoChamado).getNumeroTelefones().get(posicao_num_chamado).getEstado().equals("mobileOff()")) {
                System.out.println("\t\t\t\t\t           O número para qual ligou, está desligado!");
                if (cliente.get(posicaoChamado).getEstadoAtendedor().equals("ACTIVO")) {
                    do {
                        idChamada++;
                    } while (verificar_id_chamada(chamadas, idChamada) > -1);
                    chamadas.add(new Chamada(idChamada, telChamador, telChamado, tipoChamada, 0, 0, "noMessage()"));
                }
            } else {
                System.out.println("\t\t\t\t\t           O número para qual ligou, está em silêncio!");
                do {
                    idChamada++;
                } while (verificar_id_chamada(chamadas, idChamada) > -1);

                chamadas.add(new Chamada(idChamada, telChamador, telChamado, tipoChamada, 0, 0, "message()"));
            }
            voltar();
        }
    }

    static int verificar_id_chamada(ArrayList<Chamada> chamadas, int id) {
        for (int i = 0; i < chamadas.size(); i++) {
            if (chamadas.get(i).getIdChamada() == id) {
                return i;
            }
        }

        return -1;
    }

    /* FIM ---Metodos para chamada VOZ---*/

 /* INICIO ---Metodos para chamada SMS---*/
    static void chamada_sms(ArrayList<Cliente> cliente, int telChamador, int telChamado) throws InterruptedException {
        System.out.print("\t\t\t\t\t         Escrever a sms, charReq(): \n\t\t\t\t\t         ");
        String numChar = new Scanner(System.in).nextLine();
        fazer_chamada_sms_normal(cliente, telChamador, telChamado, numChar);
        fazer_chamada_sms_ouro(cliente, telChamador, telChamado, numChar);
        fazer_chamada_sms_platina(cliente, telChamador, telChamado, numChar);
    }

    static void fazer_chamada_sms_ouro(ArrayList<Cliente> cliente, int telChamador, int telChamado, String numChar) throws InterruptedException {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);

        int posicaoChamado = verificar_linha_numero(cliente, telChamado);
        int posicao_num_chamado = verificar_coluna_numero(cliente, telChamado);
        String tipoChamada = "textMessage()";
        int custo = 0;
        int tamanhoSms = numChar.length();
        if (tamanhoSms < 50 && cliente.get(posicaoChamador).getTipoCliente().equals("OURO")) {
            custo = 10;
            if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
            } else {
                System.out.println("\t\t\t\t\t           Saldo insuficiênte! No mínimo faz um carregamento de 10Kz.");
                voltar();
            }
        } else {
            if ((tamanhoSms >= 50 && tamanhoSms < 100) && cliente.get(posicaoChamador).getTipoCliente().equals("OURO")) {
                custo = 10;
                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                    finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                } else {
                    System.out.println("\t\t\t\t\t           Saldo insuficiênte! No mínimo faz um carregamento de 10Kz.");
                    voltar();
                }
            } else {
                if (tamanhoSms >= 100 && cliente.get(posicaoChamador).getTipoCliente().equals("OURO")) {
                    custo = 2 * tamanhoSms;
                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                        finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                    } else {
                        System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                        voltar();
                    }
                }
            }
        }
    }

    static void fazer_chamada_sms_normal(ArrayList<Cliente> cliente, int telChamador, int telChamado, String numChar) throws InterruptedException {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);

        int posicaoChamado = verificar_linha_numero(cliente, telChamado);
        int posicao_num_chamado = verificar_coluna_numero(cliente, telChamado);
        String tipoChamada = "textMessage()";
        int custo = 0;
        int tamanhoSms = numChar.length();
        if (tamanhoSms < 50 && cliente.get(posicaoChamador).getTipoCliente().equals("NORMAL")) {
            custo = 10;
            if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
            } else {
                System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                voltar();
            }
        } else {
            if ((tamanhoSms >= 50 && tamanhoSms < 100) && cliente.get(posicaoChamador).getTipoCliente().equals("NORMAL")) {
                custo = 16;
                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                    finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                } else {
                    System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                    voltar();
                }
            } else {
                if (tamanhoSms >= 100 && cliente.get(posicaoChamador).getTipoCliente().equals("NORMAL")) {
                    custo = 2 * tamanhoSms;
                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                        finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                    } else {
                        System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                        voltar();
                    }
                }
            }
        }
    }

    static void fazer_chamada_sms_platina(ArrayList<Cliente> cliente, int telChamador, int telChamado, String numChar) throws InterruptedException {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);

        int posicaoChamado = verificar_linha_numero(cliente, telChamado);
        int posicao_num_chamado = verificar_coluna_numero(cliente, telChamado);
        String tipoChamada = "textMessage()";
        int custo = 0;
        int tamanhoSms = numChar.length();
        if (tamanhoSms < 50 && cliente.get(posicaoChamador).getTipoCliente().equals("PLATINA")) {
            custo = 0;
            if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
            } else {
                System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                voltar();
            }
        } else {
            if ((tamanhoSms >= 50 && tamanhoSms < 100) && cliente.get(posicaoChamador).getTipoCliente().equals("PLATINA")) {
                custo = 4;
                if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                    finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                } else {
                    System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                    voltar();
                }
            } else {
                if (tamanhoSms >= 100 && cliente.get(posicaoChamador).getTipoCliente().equals("PLATINA")) {
                    custo = 4;
                    if (cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() >= custo) {
                        finalizar_chamada_msg(cliente, telChamador, telChamado, 0, custo, tipoChamada);
                    } else {
                        System.out.println("\t\t\t\t\t           Saldo insuficiênte! Faz um pagamento de saldo.");
                        voltar();
                    }
                }
            }
        }
    }

    static void finalizar_chamada_msg(ArrayList<Cliente> cliente, int telChamador, int telChamado, int duracao, int custo, String tipoChamada) throws InterruptedException {
        int posicaoChamador = verificar_linha_numero(cliente, telChamador);
        int posicao_num_chamador = verificar_coluna_numero(cliente, telChamador);

        int posicaoChamado = verificar_linha_numero(cliente, telChamado);
        int posicao_num_chamado = verificar_coluna_numero(cliente, telChamado);
        if (!cliente.get(posicaoChamado).getNumeroTelefones().get(posicao_num_chamado).getEstado().equals("mobileOff()") && tipoChamada.equals("textMessage()")) {
            cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).setSaldo(cliente.get(posicaoChamador).getNumeroTelefones().get(posicao_num_chamador).getSaldo() - custo);
            cliente.get(posicaoChamador).setSaldo(cliente.get(posicaoChamador).getSaldo() - custo);

            cont_chamada_msg++;
            /**/
            tipoCliente(cliente, telChamador, tipoChamada, cont_chamada_msg);
            quebralinha();
            System.out.println("\t\t\t\t\t           Mensagem efectuada para " + telChamado + "!");
            do {
                idChamada++;
            } while (verificar_id_chamada(chamadas, idChamada) > -1);
            chamadas.add(new Chamada(idChamada, telChamador, telChamado, tipoChamada, duracao, custo, "done()"));
            continuar_chamada(cliente, telChamador, telChamado, duracao, custo, tipoChamada);
        } else {
            System.out.println("\t\t\t\t\t           Falha no envio, está desligado!");
            if (cliente.get(posicaoChamado).getEstadoAtendedor().equals("ACTIVO")) {
                do {
                    idChamada++;
                } while (verificar_id_chamada(chamadas, idChamada) > -1);
                chamadas.add(new Chamada(idChamada, telChamador, telChamado, tipoChamada, 0, 0, "noMessage()"));
            }
            voltar();
        }
    }

    /* FIM ---Metodos para chamada SMS---*/
    static void ligar_telemovel(ArrayList<Cliente> cliente, int numero) {
        if (verificar_linha_numero(cliente, numero) > -1) {
            int l = verificar_linha_numero(cliente, numero);
            int c = verificar_coluna_numero(cliente, numero);
            if (cliente.get(l).getNumeroTelefones().get(c).getEstado().equals("mobileOn()")) {
                System.out.println("\t\t\t\t\t    alreadyOn()");
                voltar();
            } else {
                cliente.get(l).getNumeroTelefones().get(c).setEstado("mobileOn()");
                System.out.println("\t\t\t\t\t         " + cliente.get(l).getNumeroTelefones().get(c).getEstado());
                voltar();
            }
        }
    }

    static void desligar_telemovel(ArrayList<Cliente> cliente, int numero) {
        if (verificar_linha_numero(cliente, numero) > -1) {
            int l = verificar_linha_numero(cliente, numero);
            int c = verificar_coluna_numero(cliente, numero);
            if (cliente.get(l).getNumeroTelefones().get(c).getEstado().equals("mobileOff()")) {
                System.out.println("\t\t\t\t\t         alreadyOff()");
                voltar();
            } else {
                cliente.get(l).getNumeroTelefones().get(c).setEstado("mobileOff()");
                System.out.println("\t\t\t\t\t         " + cliente.get(l).getNumeroTelefones().get(c).getEstado());
                voltar();
            }
        }
    }

    static void telemovel_silencio(ArrayList<Cliente> cliente, int numero) {
        if (verificar_linha_numero(cliente, numero) > -1) {
            int l = verificar_linha_numero(cliente, numero);
            int c = verificar_coluna_numero(cliente, numero);
            if (cliente.get(l).getNumeroTelefones().get(c).getEstado().equals("mobileSilence()")) {
                System.out.println("\t\t\t\t\t         alreadySilent()");
                voltar();
            } else {
                //if (cliente.get(l).getNumeroTelefones().get(c).getEstado().equals("mobileOff()")) {
                //    System.out.println("\t\t\t\t\t     O telemovel está desligado!");
                //    voltar();
                //} else {
                cliente.get(l).getNumeroTelefones().get(c).setEstado("mobileSilence()");
                System.out.println("\t\t\t\t\t         " + cliente.get(l).getNumeroTelefones().get(c).getEstado());
                voltar();
                //}
            }
        }
    }

    static void adicionar_amigo(ArrayList<Cliente> cliente, int numTelemovel) {
        System.out.print("\t\t\t\t\t           Adicionar contacto amigo (6 dígitos): ");
        int numeroAmigo = 0;
        try {
            numeroAmigo = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            numeroAmigo = new Scanner(System.in).nextInt();
        }
        int posicaoCliente = verificar_linha_numero(cliente, numTelemovel);
        int posicaoTelemovel = verificar_coluna_numero(cliente, numTelemovel);
        if (verificar_coluna_numero(cliente, numeroAmigo) > -1) {
            if ((verificar_amigo_lista(cliente, numTelemovel, numeroAmigo) == -1) && numTelemovel != numeroAmigo) {
                cliente.get(posicaoCliente).getNumeroTelefones().get(posicaoTelemovel).getTelefones_amigo().add(new TelemovelAmigo(numeroAmigo));
                System.out.println("\t\t\t\t\t           Contacto adicionado!");
            } else {
                System.out.println("\t\t\t\t\t           Contacto não adicionado!");
            }
            voltar();
        }
    }

    static void eliminar_amigo(ArrayList<Cliente> cliente, int numTelemovel) {
        System.out.print("\t\t\t\t\t           Eliminar contacto amigo (6 dígitos): ");
        int numeroAmigo = 0;
        try {
            numeroAmigo = new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            numeroAmigo = new Scanner(System.in).nextInt();
        }
        int posicaoCliente = verificar_linha_numero(cliente, numTelemovel);
        int posicaoTelemovel = verificar_coluna_numero(cliente, numTelemovel);

        if ((verificar_coluna_numero(cliente, numeroAmigo) > -1) && (verificar_amigo_lista(cliente, numTelemovel, numeroAmigo) > -1) && numTelemovel != numeroAmigo) {
            cliente.get(posicaoCliente).getNumeroTelefones().get(posicaoTelemovel).getTelefones_amigo().remove(verificar_amigo_lista(cliente, numTelemovel, numeroAmigo));
            System.out.println("\t\t\t\t\t           \"Contacto eliminado\"");
        } else {
            System.out.println("\t\t\t\t\t           \"O contacto não existe\"");
        }
        System.out.println("\n\n\n");
        voltar();
    }

    static int pagamento() {
        int pagamento = 0;
        do {
            quebralinha();
            System.out.print("\t\t\t\t\t           PaymentValue(): ");
            pagamento = new Scanner(System.in).nextInt();
            if (pagamento < 0) {
                char op;
                System.out.println("\n\t\t\t\t\t       Pagamento inválido!\n\t\t\t\t\t       1-Tentar novamento o pagamento\n\t\t\t\t\t       (*)Voltar");
                System.out.print("\t\t\t\t\t        Escolha a sua opção: ");
                op = new Scanner(System.in).next().charAt(0);
                switch (op) {
                    case '1':
                        break;
                    default:
                        pagamento = 0;
                        break;
                }
            }
        } while (pagamento < 0);
        return pagamento;
    }

    static void pagamento_verificado(ArrayList<Cliente> cliente, int numTelemovel) {
        int posicao_cliente = verificar_linha_numero(cliente, numTelemovel);
        int posicao_numero = verificar_coluna_numero(cliente, numTelemovel);
        int saldo_anterior = cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_numero).getSaldo();
        int pagamento = pagamento();
        if (pagamento > 0) {
            cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_numero).setSaldo(saldo_anterior + pagamento);
            cliente.get(posicao_cliente).setSaldo(cliente.get(posicao_cliente).getSaldo() + pagamento);
            System.out.println("\t\t\t\t\t           \"Carregamento efectuado\"");
            tipo_cliente(cliente, numTelemovel, pagamento);

        } else {
            System.out.println("\t\t\t\t\t           \"O carregamento não foi efectuado\"");
        }
        System.out.println("\t\t\t\t\t           Saldo: " + cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_numero).getSaldo() + "Kz");
        voltar_pagamento(cliente, numTelemovel);
    }

    static void voltar_pagamento(ArrayList<Cliente> cliente, int numTelemovel) {
        char op;
        System.out.println("\t\t\t\t\t       1-Fazer novo pagamento\n\t\t\t\t\t       (*)Voltar...");
        System.out.print("\t\t\t\t\t       Escolha a sua opção: ");
        op = new Scanner(System.in).next().charAt(0);
        switch (op) {
            case '1':
                quebralinha();
                pagamento_verificado(cliente, numTelemovel);
                break;
            default:
                break;
        }
    }

    static void tipo_cliente(ArrayList<Cliente> cliente, int numeroTelemovel, int pagamento) {
        int posicao_cliente = verificar_linha_numero(cliente, numeroTelemovel);
        int posicao_numero = verificar_coluna_numero(cliente, numeroTelemovel);
        String tipoCliente = cliente.get(posicao_cliente).getTipoCliente();
        int saldo = cliente.get(posicao_cliente).getNumeroTelefones().get(posicao_numero).getSaldo();
        if (tipoCliente.equals("NORMAL") && saldo > 500) {
            cliente.get(posicao_cliente).setTipoCliente("OURO");
        }
    }

    static void consultar_saldo(ArrayList<Cliente> cliente, int numero) {
        System.out.println("\t\t\t\t\t     *--------------------------------------*");
        System.out.println("\t\t\t\t\t     *           CONSULTA DO SALDO          *");
        System.out.println("\t\t\t\t\t     *--------------------------------------*");
        int posicaoCliente = verificar_linha_numero(cliente, numero);
        int posicaoTelemovel = verificar_coluna_numero(cliente, numero);
        System.out.println("\t\t\t\t\t     Saldo: " + cliente.get(posicaoCliente).getNumeroTelefones().get(posicaoTelemovel).getSaldo() + "Kz");
        voltar();
    }

    static int verificar_amigo_lista(ArrayList<Cliente> cliente, int numTelemovel, int numero_amigo) {
        int posicaoCliente = verificar_linha_numero(cliente, numTelemovel);
        int posicaoTelemovel = verificar_coluna_numero(cliente, numTelemovel);
        for (int i = 0; i < cliente.get(posicaoCliente).getNumeroTelefones().get(posicaoTelemovel).getTelefones_amigo().size(); i++) {
            if (cliente.get(posicaoCliente).getNumeroTelefones().get(posicaoTelemovel).getTelefones_amigo().get(i).getAmigos() == numero_amigo) {
                return i;
            }
        }
        return -1;
    }

    static void voltar() {
        char op;
        System.out.print("\n\t\t\t\t\t       (*)Voltar__");
        op = new Scanner(System.in).next().charAt(0);
        quebralinha();
    }

    static int verificar_linha_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return i;
                }
            }
        }
        return -1;
    }

    static int verificar_coluna_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return j;
                }
            }
        }
        return -1;
    }
}
