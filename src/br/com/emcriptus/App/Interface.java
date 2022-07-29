package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;

import java.util.Scanner;

public class Interface {

    public static int telaInicial(){
        Scanner sc = new Scanner(System.in);

        System.out.println("BANCO EMCRIPTUS G2");
        System.out.println("O Futuro cada mais perto de você.");

        System.out.println("\n1 - CONTA POUPANCA");
        System.out.println("2 - CONTA CORRENTE");
        System.out.println("3 - CONTA ESPECIAL");
        System.out.println("4 - CONTA EMPRESA");
        System.out.println("5 - CONTA ESTUDANTIL");
        System.out.println("6 - SAIR");

        System.out.println("\nDIGITE O CODIGO DA OPÇÃO SELECIONADA: ");
        int op = sc.nextInt();
        sc.nextLine();

        return op;
    }

    public static void tela2(int tipo, Conta conta, int movimentacoes){
        String[] tiposConta = {"POUPANÇA","CORRENTE","ESPECIAL", "EMPRESA", "ESTUDANTIL"};
        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*");
        System.out.println("BANCO EMCRIPTUS G2");	
        System.out.println("O Futuro mais perto de você.");
        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*\n");

        System.out.printf("CONTA %s \n",tiposConta[tipo-1]);

        System.out.printf(conta.getNome() + '|' + conta.getCpf() + "\nNumero Conta: %d | Saldo Atual: %.2f | Movimentações: %d \n",conta.getNumero(),conta.getSaldo(),movimentacoes);
    }
    
    public static boolean continuar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Você deseja continuar? (S/N)");

        String resp = sc.nextLine().toUpperCase().trim();

        return resp.equals("S");
    }



}
