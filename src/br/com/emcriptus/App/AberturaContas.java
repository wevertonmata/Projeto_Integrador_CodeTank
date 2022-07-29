package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;
import br.com.emcriptus.TiposDeContas.ContaCorrente;
import br.com.emcriptus.TiposDeContas.ContaPoupanca;

import java.util.ArrayList;
import java.util.Scanner;

public class AberturaContas {
    public static int SelecionarContaCorrente(ArrayList<Integer> numerosContas, ArrayList<ContaCorrente> contas) {
        Scanner sc = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Correntes disponíveis: ");
        for (ContaCorrente c : contas) {
            System.out.println(count + " > " + c.getNome() + " | "  + c.getCpf());
            count++;
        }
        System.out.println("0 > Criar um nova conta: ");

        System.out.println("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 0) {
        	System.out.println("Informe o nome do Cliente: ");
            String nome = sc.nextLine();
            System.out.println("Informe o CPF: ");
            String cpf = sc.nextLine();

            ContaCorrente corrente = new ContaCorrente(Conta.gerarNumConta(numerosContas), cpf, nome);
            contas.add(corrente);
            codConta = contas.size() - 1;
        }
        else {
            codConta = (op - 1);
        }

        return codConta;
    }
        
	public static int SelecionarContaPoupanca(ArrayList<Integer> numerosContas, ArrayList<ContaPoupanca> contas) {
        Scanner entrada = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Poupanças disponíveis: ");
        for (ContaPoupanca cp : contas) {
            System.out.println(count + " > " + cp.getNome() + " | "  + cp.getCpf());
            count++;
        }
        System.out.println("0 > Criar um nova conta: ");

        System.out.println("Escolha: ");
        int op = entrada.nextInt();
        entrada.nextLine();

        if (op == 0) {
            System.out.println("Informe o nome do Cliente: ");
            String nome = entrada.nextLine();
            System.out.println("Informe o CPF: ");
            String cpf = entrada.nextLine();
            ContaPoupanca corrente = new ContaPoupanca(Conta.gerarNumConta(numerosContas), cpf, nome);
            contas.add(corrente);
            codConta = contas.size() - 1;
            System.out.println();
        }
        else {
            codConta = (op - 1);
        }

        return codConta;
	}
}

		
	


