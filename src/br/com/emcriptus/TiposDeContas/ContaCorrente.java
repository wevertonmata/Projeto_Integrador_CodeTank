package br.com.emcriptus.TiposDeContas;
/*
* Author: Weverton Mata
*/

import java.util.Scanner;

public class ContaCorrente extends Conta{

    private int contadorTalao;

    public ContaCorrente(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    @Override
    public double debito(double valor){
        double saldo =  super.getSaldo() - valor;
        super.setSaldo(saldo);
        return  saldo;
    }

    public void pediTalao(){
        if(super.getSaldo() < 30){
            System.out.println("Saldo insuficiente para ter talão de cheque R$" + super.getSaldo());
            return;
        }

        if(contadorTalao == 3){
            System.out.println("Limite de talões excedidos");
            return;
        }

        contadorTalao++;
        System.out.println("Cheque solicitado com sucesso! \n Seu novo saldo é: R$" + debito(30));
    }

    public int movimento(){
        Scanner sc = new Scanner(System.in);

        if ((getSaldo() == 0)) {
            System.out.println("MOVIMENTO - C-Crédito:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-Crédito:");
        }

        String movimento = sc.nextLine().toUpperCase().trim();
        while (!(movimento.equals("C") || movimento.equals("D"))){
            movimento = sc.nextLine().toUpperCase().trim();
        }

        System.out.println("Valor do movimento: R$");

        double valor = sc.nextDouble();
        sc.nextLine();

        while (valor <= 0){
            valor = sc.nextDouble();
            sc.nextLine();
        }

        switch (movimento.toUpperCase()) {
            case ("D") -> {
                if(getSaldo() < valor){
                    System.out.println("Valor maior que saldo atual. Não é possível efeituar o debito");
                    return 0;
                }
                debito(valor);
                return 1;
            }
            case ("C") -> {
                credito(valor);
                return 1;
            }
        }
        return 0;
    }

}
