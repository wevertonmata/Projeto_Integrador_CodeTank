package br.com.emcriptus.TiposDeContas;

import java.util.Scanner;

public class ContaEspecial extends Conta {
    private double limiteEspecial = 1000.00;

    public ContaEspecial(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    /*
    public void usarLimite (double valor) { LOGICA DA CONTA
     */

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
                    return usarEspecial(valor);
                } //Tirar essa validação
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
    public int usarEspecial(double valor) {

        if(valor > limiteEspecial) {
            System.out.println("Limite Especial Indisponível para cliente");
            System.out.println("Limite Especial Disponível: R$" +limiteEspecial);
            return 0;
        }

        debito(valor);
        if(getSaldo() < 0){ // -100 negativo
            double limiteAnterior =  limiteEspecial; //1000
            limiteEspecial = limiteEspecial + getSaldo(); //diminuir no limite > 900
            double diferenca = limiteAnterior - limiteEspecial; //1000 - 900 = 100
            credito(diferenca);//ajusta no saldo fica 0 (zero)
            System.out.println("Novo Limite Especial: R$" +limiteEspecial);
        }

        return 1;
    }
}