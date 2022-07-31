package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.*;

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
        System.out.println("Digite o número 0 para criar uma nova conta: ");

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
            System.out.println();
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
        System.out.println("Digite o número 0 para criar uma nova conta: ");

        System.out.println("Escolha: ");
        int op = entrada.nextInt();
        entrada.nextLine();

        if (op == 0) {
            System.out.println("Informe o nome do Cliente: ");
            String nome = entrada.nextLine();
            System.out.println("Informe o CPF: ");
            String cpf = entrada.nextLine();
            ContaPoupanca poupanca = new ContaPoupanca(Conta.gerarNumConta(numerosContas), cpf, nome);
            contas.add(poupanca);
            codConta = contas.size() - 1;
            System.out.println();
        }
        else {
        	codConta = (op - 1);
        }

        return codConta;
	}

    public static int SelecionarContaEstudantil(ArrayList<Integer> numerosContas, ArrayList<ContaEstudantil> contas) {
        Scanner entrada = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Estudantis disponíveis: ");
        for (ContaEstudantil cp : contas) {
            System.out.println(count + " > " + cp.getNome() + " | "  + cp.getCpf());
            count++;
        }
        System.out.println("Digite o número 0 para criar uma nova conta: ");

        System.out.println("Escolha: ");
        int op = entrada.nextInt();
        entrada.nextLine();

        if (op == 0) {
            System.out.println("Informe o nome do Cliente: ");
            String nome = entrada.nextLine();
            System.out.println("Informe o CPF: ");
            String cpf = entrada.nextLine();
            ContaEstudantil estudantil = new ContaEstudantil(Conta.gerarNumConta(numerosContas), cpf, nome);
            contas.add(estudantil);
            codConta = contas.size() - 1;
            System.out.println();
        }
        else {
            codConta = (op - 1);
        }

        return codConta;
    }

    public static int SelecionarContaEmpresa(ArrayList<Integer> numerosContas, ArrayList<ContaEmpresa> contas) {
        Scanner entrada = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Empresariais disponíveis: ");
        for (ContaEmpresa cp : contas) {
            System.out.println(count + " > " + cp.getNome() + " | "  + cp.getCpf());
            count++;
        }
        System.out.println("Digite o número 0 para criar uma nova conta: ");

        System.out.println("Escolha: ");
        int op = entrada.nextInt();
        entrada.nextLine();

        if (op == 0) {
            System.out.println("Informe o nome do Cliente: ");
            String nome = entrada.nextLine();
            System.out.println("Informe o CNPJ: ");
            String cnpj = entrada.nextLine();
            ContaEmpresa empresarial = new ContaEmpresa(cnpj,Conta.gerarNumConta(numerosContas),nome);
            contas.add(empresarial);
            codConta = contas.size() - 1;
            System.out.println();
        }//ver como introduzir cnpj
        else {
            codConta = (op - 1);
        }

        return codConta;
    }

    public static int SelecionarContaEspecial(ArrayList<Integer> numerosContas, ArrayList<ContaEspecial> contas) {
        Scanner sc = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Especiais disponíveis: ");
        for (ContaEspecial c : contas) {
            System.out.println(count + " > " + c.getNome() + " | "  + c.getCpf());
            count++;
        }
        System.out.println("Digite o número 0 para criar uma nova conta: ");

        System.out.println("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 0) {
            System.out.println("Informe o nome do Cliente: ");
            String nome = sc.nextLine();
            System.out.println("Informe o CPF: ");
            String cpf = sc.nextLine();

            ContaEspecial especial = new ContaEspecial(Conta.gerarNumConta(numerosContas), cpf, nome);
            contas.add(especial);
            codConta = contas.size() - 1;
            System.out.println();
        }
        else {
            codConta = (op - 1);
        }

        return codConta;
    }}




		
	


