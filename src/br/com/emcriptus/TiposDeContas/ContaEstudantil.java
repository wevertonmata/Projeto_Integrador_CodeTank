package br.com.emcriptus.TiposDeContas;

import java.util.Scanner;

public class ContaEstudantil extends Conta {

    private double limiteEstudantil;

    public ContaEstudantil(int numero, String cpf, String nome)
    {
        super(numero, cpf, nome);
        limiteEstudantil = 5000;//todo fazer constante


    }

    @Override
    public int movimento() {
        Scanner sc = new Scanner(System.in);

        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-Crédito:");
        }

        String movimento = sc.nextLine().trim();
        while (!(movimento.equals("C") || movimento.equals("D"))){
            movimento = sc.nextLine().trim();
        }

        System.out.println("Valor movimento: R$");
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
            default -> {
                System.out.println("Opção Invalida!!!\n");
                return 0;
            }
        }
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

    //seta credito se tem limite disponviel
    public boolean usarEstudantil(double valor){
            if(valor > limiteEstudantil)
                return false;
            else
            {
                limiteEstudantil = limiteEstudantil - valor;
                credito(valor);
                return true;
            }
    }


    public boolean Ativo(){
        return this.isAtivo();
    }
}

