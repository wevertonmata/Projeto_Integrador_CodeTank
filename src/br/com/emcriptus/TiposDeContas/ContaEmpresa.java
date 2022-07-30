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
        double valor = 0;

        if ((getSaldo() == 0)) {
            System.out.println("MOVIMENTO - C-crédito ou S-ativar/desativar conta:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-crédito ou S-ativar/desativar conta:");
        }

        String movimento = sc.nextLine().toUpperCase().trim();
        while (!(movimento.equals("C") || movimento.equals("D") || movimento.equals("S"))){
            movimento = sc.nextLine().toUpperCase().trim();
        }

        if(!movimento.equals("S")){
            System.out.println("Valor do movimento: R$");
            valor = sc.nextDouble();
            sc.nextLine();

            while (valor <= 0){
                valor = sc.nextDouble();
                sc.nextLine();
            }
        }

        switch (movimento.toUpperCase()) {
            case ("D") -> {
                if (getSaldo() < valor) {
                    System.out.println("Valor maior que saldo atual. Não é possível efeituar o debito");
                    return 0;
                }

                if(super.getAtivo()){
                    debito(valor);
                    return 1;
                }else{
                    System.out.println("CONTA DESATIVADA, não é possível fazer credito ou debito.");
                    return 0;
                }
            }
            case ("C") -> {
                if(super.getAtivo()){
                    credito(valor);
                    return 1;
                }else{
                    System.out.println("CONTA DESATIVADA, não é possível fazer credito ou debito.");
                    return 0;
                }
            }
            case ("S") -> {
                alterarStatus();
                return 0;
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

