package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Integer> numerosContas = new ArrayList<>();// Armazenar todos números das contas
        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>(); // Mais de uma conta por tipo de conta
        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>();
        ArrayList<ContaEstudantil> contasEstudantil = new ArrayList<>();
        ArrayList<ContaEmpresa> contasEmpresa = new ArrayList<>();
        ArrayList<ContaEspecial> contasEspecial = new ArrayList<>();


        while (true) {

            int op = Interface.telaInicial();

            switch (op) {
                case 1 -> contaPoupanca(numerosContas, contasPoupanca);
                case 2 -> contaCorrente(numerosContas, contasCorrente);
                case 3 -> contaEspecial(numerosContas, contasEspecial);
                case 4 -> contaEmpresa(numerosContas, contasEmpresa);
                case 5 -> contaEstudantil(numerosContas, contasEstudantil);
                case 6 -> System.exit(0);
                default -> System.out.println("Opção não existe! Tente novamente.\n");
            }
        }
    }

    public static void contaPoupanca(ArrayList<Integer> numerosContas, ArrayList<ContaPoupanca> contas) {
        //Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;
        codConta = AberturaContas.SelecionarContaPoupanca(numerosContas, contas);
        while (true) {
            Interface.tela2(0, contas.get(codConta), movimentacoes); //Print informações da Tela 2

            movimentacoes += contas.get(codConta).movimento(); //Contador de movimentacoes

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar
            if (!respContinuar) {
                break;
            }
        }
    }

    public static void contaCorrente(ArrayList<Integer> numerosContas, ArrayList<ContaCorrente> contas) {
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaCorrente(numerosContas, contas);
        //Faz criação de conta e retorna o index dela no arraylist

        while (true) {
            Interface.tela2(1, contas.get(codConta), contas.get(codConta).getMovimentacoes()); //Print informações da Tela 2

//            movimentacoes = contas.get(codConta).getMovimentacoes();  // Retorna 1 se for executada ação e retorna 0 se não for.
            contas.get(codConta).movimento();

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar

            if (movimentacoes >= 10 || !respContinuar) {
                System.out.println("Deseja solicitar CHEQUE? (S/N)");
                String respCheque = sc.nextLine().toUpperCase().trim();

                if (respCheque.equals("S")) {
                    contas.get(codConta).pediTalao();
                }
                break;
            }
        }
    }

    public static void contaEspecial(ArrayList<Integer> numerosContas, ArrayList<ContaEspecial> contas) {
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaEspecial(numerosContas, contas);
        //Faz criação de conta e returna o index dela no arraylist

        while (true) {
            Interface.tela2(2, contas.get(codConta), movimentacoes); //Print informações da Tela 2

            movimentacoes = contas.get(codConta).getMovimentacoes();
            ; // Retorna 1 se for executada ação e retorna 0 se não foi.

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar
            if (!respContinuar) {
                break;
            }
        }
    }

    public static void contaEmpresa(ArrayList<Integer> numerosContas, ArrayList<ContaEmpresa> contas) {
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaEmpresa(numerosContas, contas);
        //Faz criação de conta e returna o index dela no arraylist

        while (true) {
            Interface.tela2(3, contas.get(codConta), contas.get(codConta).getMovimentacoes());  //Print informações da Tela 2
            contas.get(codConta).movimento();

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar

            if (!respContinuar) {
                break;
            }

        }
    }

    public static void contaEstudantil(ArrayList<Integer> numerosContas, ArrayList<ContaEstudantil> contas) {
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaEstudantil(numerosContas, contas);
        //Faz criação de conta e returna o index dela no arraylist

        while (true) {
            Interface.tela2(4, contas.get(codConta), contas.get(codConta).getMovimentacoes()); //Print informações da Tela 2
            contas.get(codConta).movimento();

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar

            if (!respContinuar) {
                break;
            }
        }
    }
}





