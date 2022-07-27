package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.AberturaContas;
import br.com.emcriptus.TiposDeContas.ContaCorrente;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> numerosContas = new ArrayList<>();
        ArrayList<ContaCorrente> contasCorrente = new ArrayList<>();


        while (true){

            Interface.telaInicial();
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    contaPoupanca();
                    break;
                case 2:
                    contaCorrente(numerosContas,contasCorrente);
                    break;
                case 3:
                    contaEspecial();
                    break;
                case 4:
                    contaEmpresa();
                    break;
                case 5:
                    contaEstudantil();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }

    public static void contaPoupanca(){
        System.out.println("CONTA POUPANÇA");
    }

    public static void contaCorrente(ArrayList<Integer> numerosContas,ArrayList<ContaCorrente> contas){
        Scanner sc = new Scanner(System.in);
        int movimentacoes = 0, codConta;

        codConta = AberturaContas.SelecionarContaCorrente(numerosContas,contas);

        while (true){
            Interface.tela2(2,contas.get(codConta)); //Print informações da Tela 2

            movimentacoes++; //Contador de movimentacoes

            boolean respContinuar = Interface.continuar(); //Pergunta se o cliente deseja continuar

            if(movimentacoes == 10 || respContinuar){
                System.out.println("Deseja solicitar cheque? (S/N)");
                String respCheque = sc.nextLine();

                if(respCheque.equals("S") || respCheque.equals("s")){
                    //Debita R$30 e pede cheque.
                }
            }
        }
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
