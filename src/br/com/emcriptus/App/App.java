package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.ContaCorrente;
import br.com.emcriptus.TiposDeContas.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Integer> numerosContas = new ArrayList<>();// Armazenar todos números das contas

        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>(); // Mais de uma conta por tipo de conta
        ArrayList<ContaPoupanca> contasPoupanca = new ArrayList<>(); // Mais de uma conta por tipo de conta

        while (true){
            int op = Interface.telaInicial();

            switch (op) {
                case 1 -> contaPoupanca(numerosContas, contasPoupanca);
                case 2 -> contaCorrente(numerosContas, contasCorrente);
                case 3 -> contaEspecial();
                case 4 -> contaEmpresa();
                case 5 -> contaEstudantil();
                case 6 -> System.exit(0);
                default -> System.out.println("Opção Invalida!!!\n");
            }
        }
    }
    public static void contaPoupanca(ArrayList<Integer> numerosContas, ArrayList<ContaPoupanca> contas){
    	//Scanner sc = new Scanner(System.in);
    	int movimentacoes = 0, codConta;
    	codConta = AberturaContas.SelecionarContaPoupanca(numerosContas,contas);
    	while (true){
            Interface.tela2(1,contas.get(codConta),movimentacoes); //Print informações da Tela 2

            movimentacoes++; //Contador de movimentacoes
            
            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar
           
            if(movimentacoes >= 10 || !respContinuar){
                System.out.println(movimentacoes);
            }
            else{
                break;
            }
    	}
    }
    public static void contaCorrente(ArrayList<Integer> numerosContas,ArrayList<ContaCorrente> contas){
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaCorrente(numerosContas,contas);
        //Faz criação de conta e returna o index dela no arraylist

        while (true){
            Interface.tela2(1,contas.get(codConta),movimentacoes); //Print informações da Tela 2

            movimentacoes += contas.get(codConta).movimento(); // Retorna 1 se for executada ação e retorna 0 se não for.

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar

            if(movimentacoes == 10 || !respContinuar){
                System.out.println("Deseja solicitar CHEQUE? (S/N)");
                String respCheque = sc.nextLine().toUpperCase().replaceAll(" ", "");

                if(respCheque.equals("S")){
                    contas.get(codConta).pediTalao();
                }
                break;
            }

        }
        //
    }

    public static void contaEspecial(){
        System.out.println("CONTA ESPECIAL");
    }

    public static void contaEmpresa(){
        System.out.println("CONTA EMPRESA");
    }

    public static void contaEstudantil(){
        System.out.println("CONTA ESTUDANTIL");
    }
}
