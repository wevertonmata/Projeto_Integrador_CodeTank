package br.com.emcriptus.TiposDeContas;

import java.util.Date;
import java.util.Scanner;

public class ContaPoupanca extends Conta{

    private int diaAniversarioPoupanca;
    
    public ContaPoupanca(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    @Override
    public double debito(double valor) {
        double saldo =  super.getSaldo() - valor;
        super.setSaldo(saldo);
        return  saldo;
    }

    public int movimento(){
    	Scanner entrada = new Scanner(System.in);

        if(getSaldo() == 0){
            System.out.println("MOVIMENTO - C-Crédito:");
        }
        else{
            System.out.println("MOVIMENTO - D-debito ou C-Crédito:");
        }

        String movimento = entrada.nextLine();  		
        System.out.println("Valor do movimento: R$");
        double valor = entrada.nextDouble();
        entrada.nextLine();

        while (valor <= 0){
            valor = entrada.nextDouble();
            entrada.nextLine();
           
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
            default ->{
                System.out.println("Opção Invalida!!!\n");
                return 0;
            }
        }
    }




    }

