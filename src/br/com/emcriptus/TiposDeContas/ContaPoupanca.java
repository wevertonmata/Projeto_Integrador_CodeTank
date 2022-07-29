package br.com.emcriptus.TiposDeContas;

public class ContaPoupanca extends Conta{

    private int diaAniversarioPoupanca;
    public ContaPoupanca(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    public String correcao(){
        return "Correção feita";
    }
}
