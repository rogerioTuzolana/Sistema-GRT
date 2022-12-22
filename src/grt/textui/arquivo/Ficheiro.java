/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grt.textui.arquivo;

import grt.textui.client.Cliente;
import grt.textui.consulta.Chamada;
import grt.textui.main.MenuEntry;
import grt.textui.main.Message;
import grt.textui.oneMobile.MenuEntry.Telemovel;
import grt.textui.oneMobile.contactos.TelemovelAmigo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 *
* @author grupo 6
*/
public class Ficheiro {

    static int posicao = 0;
    public static String extensao = "";
    static String importacao = "", conteudo = "", info = "";
    static Scanner leia = new Scanner(System.in);

    private static void limpatela() {
        for (int i = 0; i < 10; i++) {
            System.out.println("\n");
        }
    }

    public static boolean vImportar(String f) {
        int cont = 0, caracter;
        for (int i = 0; i < f.length(); i++) {
            caracter = f.charAt(i);
            if (caracter == '.') {
                cont++;
            }
        }
        return (cont == 0) ? false : true;
    }

    public static void criar_ficheiro(String f,ArrayList<Cliente>cliente,ArrayList<Chamada> chamada) {
        if (vImportar(f) == false) {
            f = f + ".txt";
        }
        File arquivo = new File(f);
        try {
            if (!arquivo.exists()) {
                cliente.clear();
                chamada.clear();
                arquivo.createNewFile();
            }else{
                System.out.println("\t\t\t\t\t         Já existe este ficheiro!");
                voltar();
            }
        } catch (Exception e) {
            System.out.println("\t\t\t\t\t         Falha na criação do ficheiro!");
            voltar();
        }
    }

    public static void exportarCliente(ArrayList<Cliente> cliente, String exten) throws IOException {
        extensao = exten;
        if (vImportar(extensao) == false) {
            extensao = extensao + ".txt";
        }
        FileWriter escrever = new FileWriter(extensao);/*"C:/Users/TUZOLANA R/Desktop/"+extensao);*/

        BufferedWriter mostrar = new BufferedWriter(escrever);
        try {

            //Escreve os dados no arquivo(.txt)
            for (int i = 0; i < cliente.size(); i++) {
                int qtdeTel = cliente.get(i).getNumeroTelefones().size();
                long saldo = 0;

                mostrar.write("CLIENTE|" + cliente.get(i).getIdCliente() + "|" + cliente.get(i).getNomeCliente() + "|" + cliente.get(i).getTipoCliente() + "|" + cliente.get(i).getEstadoAtendedor() + "|");
                if (qtdeTel > 0) {
                    for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                        String concatenacao = (j + 1 < cliente.get(i).getNumeroTelefones().size()) ? "," : "";
                        mostrar.write(cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() + concatenacao);

                        saldo = saldo + cliente.get(i).getNumeroTelefones().get(j).getSaldo();
                        cliente.get(i).setSaldo(saldo);
                    }
                    mostrar.write("|" + cliente.get(i).getSaldo() + "|");
                    //Depois da primeira impressão o newLine muda de linha

                } else {
                    mostrar.write("0|0|");
                }
                //Depois da primeira impressão o newLine muda de linha
                mostrar.newLine();
            }
            //Depois de escrever, fecha-se todas as conexões.
            mostrar.close();
            escrever.close();
        } catch (IOException e) {
        }
    }

    public static void exportarTelemovel(ArrayList<Cliente> cliente) throws IOException {
        FileWriter escrever = new FileWriter(separarExtensao(extensao) + "Telemoveis.txt");

        BufferedWriter mostrar = new BufferedWriter(escrever);
        try {

            for (int i = 0; i < cliente.size(); i++) {
                for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                    mostrar.write("Telemovel|" + cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() + "|" + cliente.get(i).getNomeCliente() + "|");
                    mostrar.write(cliente.get(i).getNumeroTelefones().get(j).getEstado() + "|" + cliente.get(i).getNumeroTelefones().get(j).getSaldo() + "|");
                    if (cliente.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size() > 0) {
                        for (int k = 0; k < cliente.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size(); k++) {
                            String concatenacao = (k + 1 < cliente.get(i).getNumeroTelefones().get(j).getTelefones_amigo().size()) ? "," : "";
                            mostrar.write(cliente.get(i).getNumeroTelefones().get(j).getTelefones_amigo().get(k).getAmigos() + concatenacao);
                        }
                        mostrar.write("|");

                    } else {
                        mostrar.write(" |");
                    }

                    //Depois da primeira impressão o newLine muda de linha
                    mostrar.newLine();
                }
            }

            //Depois de escrever, fecha-se todas as conexões.
            mostrar.close();
            escrever.close();

        } catch (IOException err2) {
            System.out.println("erro");
        }
    }

    public static void exportarChamadas(ArrayList<Chamada> chamadas) throws IOException {
        FileWriter escrever = new FileWriter(separarExtensao(extensao) + "Chamadas.txt");

        BufferedWriter mostrar = new BufferedWriter(escrever);
        try {
            int qtdeChamadas = chamadas.size();
            if (qtdeChamadas > 0) {
                for (int i = 0; i < chamadas.size(); i++) {
                    mostrar.write("|" + chamadas.get(i).getIdChamada() + "|" + chamadas.get(i).getTelChamador() + "|");
                    mostrar.write(chamadas.get(i).getTelChamado() + "|" + chamadas.get(i).getTipo() + "|");
                    mostrar.write(chamadas.get(i).getDuracao() + "|" + chamadas.get(i).getCusto() + "|" + chamadas.get(i).getEstado() + "|");
                    //Depois da primeira impressão o newLine muda de linha
                    mostrar.newLine();
                }
            }

            //Depois de escrever, fecha-se todas as conexões.
            mostrar.close();
            escrever.close();

        } catch (IOException err2) {
            System.out.println("erro");
        }

    }

    public static boolean importar_clientes(ArrayList<Cliente> cliente, String f) throws IOException, InterruptedException {
        try {
            //Leitura do ficheiro
            int qtd = 0;
            int cont = 1, linha = 0;
            String id = "", nome = "", tipoCli = "", estadoAtend = "", numero = "", saldo = "";
            FileReader ler = new FileReader(f);/*"C:/Users/TUZOLANA R/Desktop/"+f);*/

            BufferedReader Bler = new BufferedReader(ler);
            cliente.clear();
            while (Bler.ready()) {
                //lê a proxima linha
                conteudo = Bler.readLine();
                //O primeiro for percorre ate a primeira barra vertical(|)
                for (int i = 0; i < conteudo.length(); i++) {
                    if (conteudo.charAt(i) != '|') {
                        qtd++;
                    } else {
                        i = conteudo.length();
                    }
                }
                //recebe os conteudos dentro da barra vertical
                for (int i = qtd + 1; i < conteudo.length(); i++) {
                    if (cont == 1) {
                        id = id + conteudo.charAt(i);
                    } else {
                        if (cont == 2) {
                            nome = nome + conteudo.charAt(i);
                        } else {
                            if (cont == 3) {
                                tipoCli = tipoCli + conteudo.charAt(i);
                            } else {
                                if (cont == 4) {
                                    estadoAtend = estadoAtend + conteudo.charAt(i);
                                } else {
                                    if (cont == 5) {
                                        numero = numero + conteudo.charAt(i);
                                    } else {
                                        if (cont == 6) {
                                            saldo += conteudo.charAt(i);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //Verifica se o proximo laco encontraremos o fechamento do conteudo
                    if (conteudo.charAt(i + 1) == '|') {
                        cont++;
                        if (i < conteudo.length()) {
                            i += 1;
                        }
                    }
                }

                //Insirir na lista dos clientes e telemoveis
                Cliente cli = new Cliente(id, nome, tipoCli, estadoAtend, 0);
                cliente.add(cli);

                ArrayList<Integer> aux = new ArrayList();

                for (int i = 0; i < repartir_numero(numero).size(); i++) {
                    aux = repartir_numero(numero);
                    //System.out.println(aux.get(i));
                    cliente.get(linha).getNumeroTelefones().add(new Telemovel(aux.get(i), 0, "mobileOn()"));
                }
                //Limpar dados para proxima linha
                id = "";
                nome = "";
                tipoCli = "";
                estadoAtend = "";
                numero = "";
                saldo = "";
                cont = 1;
                qtd = 0;
                linha++;
            }
            //Fechar leitura do ficheiro
            ler.close();
            Bler.close();

            return true;
        } //Catch capta o tipo de erro e recebe instruções para evitar a paragem do programa
        catch (Exception e1) {
            limpatela();
            System.out.println("\n\t\t\t       O Ficheiro nao foi encontrado ou os dados estao incorretos!");
            voltar();
            Message.menu_principal();
            return false;
        }

    }

    static ArrayList<Integer> repartir_numero(String numero) {
        ArrayList<Integer> numeros_cli = new ArrayList();
        String num = "";
        int c = 0;

        for (int i = 0; i < numero.length(); i++) {
            if (numero.charAt(i) != ',') {
                num = num + numero.charAt(i);
                c++;
                if (c == 6) {
                    numeros_cli.add(Integer.parseInt(num));
                    num = "";
                    c = 0;
                }
            }
        }
        return numeros_cli;
    }

    public static boolean importar_telemoveis(ArrayList<Cliente> cliente, String f) throws IOException, InterruptedException {
        try {
            //Leitura do ficheiro
            int qtd = 0;
            int cont = 1;
            String estado = "", numero = "", saldo = "", amigos = "";
            FileReader ler = new FileReader(f);/*"C:/Users/TUZOLANA R/Desktop/"+f);*/

            BufferedReader Bler = new BufferedReader(ler);

            while (Bler.ready()) {
                //lê a proxima linha
                conteudo = Bler.readLine();
                //O primeiro for percorre ate a primeira barra vertical(|)
                for (int i = 0; i < conteudo.length(); i++) {
                    if (conteudo.charAt(i) != '|') {
                        qtd++;
                    } else {
                        i = conteudo.length();
                    }
                }
                //recebe os conteudos dentro da barra vertical
                for (int i = qtd + 1; i < conteudo.length(); i++) {
                    if (cont == 1) {
                        numero = numero + conteudo.charAt(i);
                    } else {
                        if (cont == 3) {
                            estado = estado + conteudo.charAt(i);
                        } else {
                            if (cont == 4) {
                                saldo = saldo + conteudo.charAt(i);
                            } else {
                                if (cont == 5) {
                                    amigos = amigos + conteudo.charAt(i);
                                }
                            }
                        }
                    }
                    //Verifica se o proximo laco encontraremos o fechamento do conteudo
                    if (conteudo.charAt(i + 1) == '|') {
                        cont++;
                        if (i < conteudo.length()) {
                            i += 1;
                        }
                    }
                }
                ArrayList<Integer> aux = new ArrayList();
                int posicao_cli_numero = verificar_cliente_numero(cliente, Integer.parseInt(numero));
                int posicao_numero = verificar_numero(cliente, Integer.parseInt(numero));
                //Alterar(adicionar) os dados do telemovel
                if (posicao_cli_numero > -1) {
                    cliente.get(posicao_cli_numero).getNumeroTelefones().get(posicao_numero).setEstado(estado);
                    cliente.get(posicao_cli_numero).getNumeroTelefones().get(posicao_numero).setSaldo(Integer.parseInt(saldo));
                    cliente.get(posicao_cli_numero).getNumeroTelefones().get(posicao_numero).setSaldo(Integer.parseInt(saldo));

                    for (int i = 0; i < repartir_numero(amigos).size(); i++) {
                        aux = repartir_numero(amigos);
                        //System.out.println(aux.get(i));
                        cliente.get(posicao_cli_numero).getNumeroTelefones().get(posicao_numero).getTelefones_amigo().add(new TelemovelAmigo(aux.get(i)));
                    }
                }
                estado = "";
                numero = "";
                saldo = "";
                amigos = "";
                cont = 1;
                qtd = 0;
            }
            //Fechar leitura do ficheiro
            ler.close();
            Bler.close();

            return true;
        } catch (Exception e2) {
            limpatela();
            System.out.println("\n\t\t\t       O Ficheiro nao foi encontrado ou os dados estao incorretos!");
            voltar();
            Message.menu_principal();
            return false;
        }
    }

    static String separarExtensao(String info) {
        int cont = 0, caracter;
        String info2 = "";
        for (int i = 0; i < info.length(); i++) {
            //caracter = info.charAt(i);
            if (info.charAt(i) != '.') {
                info2 += info.charAt(i);
            } else {
                return info2;
            }
        }
        return info;
    }

    static void importar_chamadas(ArrayList<Chamada> chamada, String f) throws IOException, InterruptedException {
        try {
            //Leitura do ficheiro
            int qtd = 0;
            int cont = 1;
            String estado = "", telChamador = "", custo = "", telChamado = "", tipoChamada = "", id = "", duracao = "";
            FileReader ler = new FileReader(f);/*"C:/Users/TUZOLANA R/Desktop/"+f);*/

            BufferedReader Bler = new BufferedReader(ler);
            chamada.clear();
            while (Bler.ready()) {
                //lê a proxima linha
                conteudo = Bler.readLine();
                //O primeiro for percorre ate a primeira barra vertical(|)
                for (int i = 0; i < conteudo.length(); i++) {
                    if (conteudo.charAt(i) != '|') {
                        qtd++;
                    } else {
                        i = conteudo.length();
                    }
                }
                //recebe os conteudos dentro da barra vertical
                for (int i = qtd + 1; i < conteudo.length(); i++) {
                    if (cont == 1) {
                        id = id + conteudo.charAt(i);
                    } else {
                        if (cont == 2) {
                            telChamador = telChamador + conteudo.charAt(i);
                        } else {
                            if (cont == 3) {
                                telChamado = telChamado + conteudo.charAt(i);
                            } else {
                                if (cont == 4) {
                                    tipoChamada = tipoChamada + conteudo.charAt(i);
                                } else {
                                    if (cont == 5) {
                                        duracao = duracao + conteudo.charAt(i);
                                    } else {
                                        if (cont == 6) {
                                            custo = custo + conteudo.charAt(i);
                                        } else {
                                            if (cont == 7) {
                                                estado = estado + conteudo.charAt(i);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //Verifica se o proximo laco encontraremos o fechamento do conteudo
                    if (conteudo.charAt(i + 1) == '|') {
                        cont++;
                        if (i < conteudo.length()) {
                            i += 1;
                        }
                    }
                }
                //Adicionar registos das chamadas
                chamada.add(new Chamada(Integer.parseInt(id), Integer.parseInt(telChamador), Integer.parseInt(telChamado), tipoChamada, Integer.parseInt(duracao), Integer.parseInt(custo), estado));

                estado = "";
                telChamador = "";
                custo = "";
                duracao = "";
                telChamado = "";
                tipoChamada = "";
                id = "";
                cont = 1;
                qtd = 0;
            }
            //Fechar leitura do ficheiro
            ler.close();
            Bler.close();

        } catch (Exception e) {

        }
    }

    static void voltar() {
        char op;
        System.out.print("\n\t\t\t\t\t       1-Voltar__");
        op = new Scanner(System.in).next().charAt(0);
        limpatela();
    }

    static int verifica_idCliente(String id, ArrayList<Cliente> clientes) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdCliente().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    static int verificar_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return j;
                }
            }
        }
        return -1;
    }

    static int verificar_cliente_numero(ArrayList<Cliente> cliente, int numero) {
        for (int i = 0; i < cliente.size(); i++) {
            for (int j = 0; j < cliente.get(i).getNumeroTelefones().size(); j++) {
                if (cliente.get(i).getNumeroTelefones().get(j).getNumero_telefone() == numero) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void abrir_ficheiro(ArrayList<Cliente> cliente,String abrir) throws InterruptedException {
        try {
            importacao = abrir;

            //Verifica se o nome do ficheiro tem extensão, caso não tenha ela pede para ler novamente.
            if (false == vImportar(importacao)) {
                importacao = importacao + ".txt";
            }
            //Importa o conteudo lido no ficheiro 
            if (importar_clientes(cliente, importacao)) {
                String impt = separarExtensao(importacao);
                importar_telemoveis(cliente, impt + "Telemoveis.txt");
                importar_chamadas(MenuEntry.chamadas, impt + "Chamadas.txt");
            }

        } catch (IOException ex) {
            
        }
    }
}
