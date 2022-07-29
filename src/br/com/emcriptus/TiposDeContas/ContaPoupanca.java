package br.com.emcriptus.TiposDeContas;

import java.util.Date;

public class ContaPoupanca extends Conta{

    private int diaAniversarioPoupanca;
    public ContaPoupanca(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    @Override
    public int movimento() {
        return 0;
    }

    public String correcao(){

        return "Correção feita";
    }
}
