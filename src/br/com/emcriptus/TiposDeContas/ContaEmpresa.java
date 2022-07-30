package br.com.emcriptus.TiposDeContas;

import java.util.Scanner;

public class ContaEmpresa extends Conta {

    private double emprestimoEmpresa;

    public ContaEmpresa( String _cnpj,int _numero, String _nome) {
        super(_cnpj,_numero, _nome);
        this.emprestimoEmpresa = 10000;
    }

    @Override
    public int movimento() {
        Scanner sc = new Scanner(System.in);

        if (getSaldo() == 0) {
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

        while (valor <= 0) {
            valor = sc.nextDouble();
            sc.nextLine();
        }

        switch (movimento.toUpperCase()) {
            case ("D") -> {
                if (getSaldo() < valor) {
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

    @Override
    public void credito(double valor) {
        super.credito(valor);
    }

    @Override
    public double debito(double valor) {
        return super.debito(valor);
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }
    public boolean pedirEmprestimo (double valor){
        if ( valor > emprestimoEmpresa)
            return false;
        else {
            emprestimoEmpresa = emprestimoEmpresa - valor;
            credito(valor);
             return true;
        }

    }
}

