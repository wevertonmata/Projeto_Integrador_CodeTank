package br.com.emcriptus.TiposDeContas;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class ContaPoupanca extends Conta {

    private String[] dtAniversario;

    public ContaPoupanca(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    @Override
    public double debito(double valor) {
        double saldo = super.getSaldo() - valor;
        super.setSaldo(saldo);
        return saldo;
    }

    public int movimento() {
        Scanner entrada = new Scanner(System.in);
        double valor = 0;

        if ((getSaldo() == 0)) {
            System.out.println("MOVIMENTO - C-crédito ou S-ativar/desativar conta:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-crédito ou S-ativar/desativar conta:");
        }

        String movimento = entrada.nextLine().toUpperCase().trim();
        while (!(movimento.equals("C") || movimento.equals("D") || movimento.equals("S"))) {
            movimento = entrada.nextLine().toUpperCase().trim();
        }

        if(!movimento.equals("S")){
            System.out.println("Valor do movimento: R$");
            valor = entrada.nextDouble();
            entrada.nextLine();

            while (valor <= 0){
                valor = entrada.nextDouble();
                entrada.nextLine();
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
        Scanner entrada = new Scanner(System.in);
        if (dtAniversario == null) {
            System.out.println("Informe a Data de Depósito: dd/mm/aaaa");
            dtAniversario = entrada.nextLine().split("/", 3);

        } else {
            System.out.println("Informe a Data de Depósito: dd/mm/aaaa");
            String[] dtAtual= entrada.nextLine().split("/", 3);
            LocalDate aniversario = LocalDate.of(Integer.parseInt(dtAniversario[2]), Integer.parseInt(dtAniversario[1]),Integer.parseInt(dtAniversario[0]));
            LocalDate atual = LocalDate.of(Integer.parseInt(dtAtual[2]), Integer.parseInt(dtAtual[1]),Integer.parseInt(dtAtual[0]));
            Period periodo = Period.between(aniversario, atual);
           for (int i = 0; i <= periodo.getMonths();i++) {
               correcao();
            }
            System.out.printf("Periodo: %d | Novo Saldo: R$%.2f \n", periodo.getMonths(), getSaldo());
        }
       double saldo = super.getSaldo() + valor;
        super.setSaldo(saldo);
    }
    public void correcao() {
        setSaldo(( getSaldo()*0.005)+getSaldo());
    }
}


