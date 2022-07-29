package br.com.emcriptus.TiposDeContas;

public class ContaEspecial extends Conta {
    private double limite = 1000.00;

    public ContaEspecial(int numero, String cpf) {
        super(numero, cpf);

    }

    @Override
    public int movimento() {
        return 0;
    }

    public void usarLimite (double valor) {




    }

}