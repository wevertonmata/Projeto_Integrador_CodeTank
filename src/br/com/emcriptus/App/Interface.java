package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;

import java.util.Scanner;

public class Interface {

    public static int telaInicial(){
        Scanner sc = new Scanner(System.in);

        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*");
        System.out.println("BANCO EMCRIPTUS G2");
        System.out.println("O Futuro mais perto de você.");
        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*");

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
        String[] tiposConta = {"POUPANÇA","CORRENTE","ESPECIAL", "EMPRESA", "ESTUDANTIL"}; //array só tem conta corrente
        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*");
        System.out.println("BANCO EMCRIPTUS G2");	
        System.out.println("O Futuro mais perto de você.");
        System.out.println("*x**x**x**x**x**x**x**x**x**x**x**x**x**x*\n");

         System.out.printf("CONTA %s \n", tiposConta[tipo]); //só imprime conta corrente

        String cpf_cnpj;
        if(conta.getCpf().equals("N/A")){
            cpf_cnpj = conta.getCnpj();
        }else{
            cpf_cnpj = conta.getCpf();
        }

        System.out.printf(conta.getNome() + "  |  " + cpf_cnpj + "\nNumero Conta: %d | Saldo Atual: %.2f | Movimentações: %d \n",conta.getNumero(),conta.getSaldo(),conta.getMovimentacoes());

    }

    public static boolean continuar(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Você deseja continuar? (S/N)");

        String resp = sc.nextLine().toUpperCase().trim();

        return resp.equals("S");
    }
}
