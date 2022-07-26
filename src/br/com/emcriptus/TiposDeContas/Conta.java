package br.com.emcriptus.TiposDeContas;

public abstract class Conta {

    private int numero;
    private String cpf;
    private double saldo;

    private boolean ativo;

    public Conta(int numero, String cpf) {
        this.numero = numero;
        this.cpf = cpf;
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
    }

    public void credito(double valor){
        saldo += valor;
    };

}
