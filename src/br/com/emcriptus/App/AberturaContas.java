package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;
import br.com.emcriptus.TiposDeContas.ContaCorrente;

import java.util.ArrayList;
import java.util.Scanner;

public class AberturaContas {
    public static int SelecionarContaCorrente(ArrayList<Integer> numerosContas, ArrayList<ContaCorrente> contas) {
        Scanner sc = new Scanner(System.in);
        int count = 1, codConta;

        System.out.println("Contas Correntes disponíveis: ");
        for (ContaCorrente c : contas) {
            System.out.println(count + " > " + c.getCpf());
            count++;
        }
        System.out.println("0 > Criar um nova conta: ");

        System.out.println("Escolha: ");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 0) {
            System.out.println("Informe CPF da conta? ");
            String cpf = sc.nextLine();

            ContaCorrente corrente = new ContaCorrente(Conta.gerarNumConta(numerosContas), cpf);
            contas.add(corrente);
            codConta = contas.size() - 1;
        }
        else {
            codConta = (op - 1);
        }

        return codConta;
    }

}
