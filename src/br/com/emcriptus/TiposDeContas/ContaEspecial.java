package br.com.emcriptus.TiposDeContas;

public class ContaEspecial extends Conta {
    private double limite = 1000.00;

    public ContaEspecial(int numero, String cpf, String nome) {
        super(numero, cpf, nome);

    }

    @Override
    public int movimento() {
        return 0;
    }

    public void usarLimite (double valor) {




    }

}