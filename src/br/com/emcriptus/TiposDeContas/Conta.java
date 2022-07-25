package br.com.emcriptus.TiposDeContas;

public  class Conta {
    private int numero;
    private String cpf;
    private double saldo;
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
