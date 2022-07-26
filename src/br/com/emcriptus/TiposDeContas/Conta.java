package br.com.emcriptus.TiposDeContas;


public abstract class Conta {

    private int numero;
    private String cpf;
    private double saldo; // getter

    private boolean ativo;

    public Conta(int numero, String cpf) {
        this.numero = numero;
        this.cpf = cpf;
    }

    double debito(double valor){
        saldo -= valor;
        return  saldo;
    };

    public void credito(double valor){
        saldo += valor;
    };
}
