package br.com.emcriptus.TiposDeContas;

import java.util.ArrayList;
import java.util.Random;

public abstract class Conta {

    private int numero;
    private String cpf;
    private double saldo;

    private boolean ativo;

    public Conta(int numero, String cpf) {
        this.numero = numero;
        this.cpf = cpf;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    private double debito(double valor){
        saldo -= valor;
        return  saldo;
        //Fazer verificações: Valor a ser debitado não pode ser maior que saldo
    }

    public void credito(double valor){
        saldo += valor;
        //Fazer retorno do Saldo atual do cliente
    };

    public static int gerarNumConta(ArrayList<Integer> contas) {
        Random random = new Random();

        int numConta = (random.nextInt(99999 - (10000 - 1)) + 10000);

        for (int conta: contas) {
            while(conta == numConta){
                numConta = (random.nextInt(99999 - (10000 - 1)) + 10000);
            }
        }
        return numConta;
    }

}
