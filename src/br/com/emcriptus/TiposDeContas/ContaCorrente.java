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
        System.out.println("Debito efetuado com sucesso R$" + valor);
        double saldo =  super.getSaldo() - valor;
        super.setSaldo(saldo);
        System.out.println("Saldo Atual R$" + saldo);
        return  saldo;
    }

    public void pediTalao(){
        if(super.getSaldo() < 30){
            System.out.println("Saldo insuficiente para ter talão de cheque. Saldo Atual: R$" + super.getSaldo());
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
        double valor = 0;

        if ((getSaldo() == 0)) {
            System.out.println("MOVIMENTO - C-crédito ou S-ativar/desativar conta:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-crédito ou S-ativar/desativar conta:");
        }

        String movimento = sc.nextLine().toUpperCase().trim();
        while (!(movimento.equals("C") || movimento.equals("D") || movimento.equals("S"))){ //Adicionei a função "S"
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
                if(getSaldo() < valor){
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

}
