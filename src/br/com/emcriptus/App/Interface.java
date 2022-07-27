package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;

import java.util.Scanner;

public class Interface {

    public static void telaInicial(){
        System.out.println("BANCO EMCRIPTUS G2");
        System.out.println("O Futuro cada mais perto de você.");

        System.out.println("\n1 - CONTA POUPANCA");
        System.out.println("2 - CONTA CORRENTE");
        System.out.println("3 - CONTA ESPECIAL");
        System.out.println("4 - CONTA EMPRESA");
        System.out.println("5 - CONTA ESTUDANTIL");
        System.out.println("6 - SAIR");

        System.out.println("\nDIGITE O CODIGO DA OPÇÃO SELECIONADA: ");
    }

    public static void tela2(int tipo, Conta conta){
        String[] tiposConta = {"CONTA POUPANÇA","CONTA CORRENTE","CONTA ESPECIAL", "CONTA EMPRESA", "CONTA ESTUDANTIL"};

        System.out.println("BANCO EMCRIPTUS G2");
        System.out.println("O Futuro cada mais perto de você.\n");

        System.out.printf("CONTA %s \n\n",tiposConta[tipo-1]);

        System.out.printf("Numero Conta:  %d | Saldo Atual: %.2f \n",conta.getNumero(),conta.getSaldo());
    }

    public static boolean continuar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Você deseja continuar? (S/N)");
        String resp = sc.nextLine();

        return resp.equals("S") || resp.equals("s");
    }
}
