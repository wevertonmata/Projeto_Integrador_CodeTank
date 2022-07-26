package br.com.emcriptus.TiposDeContas;

public class ContaPoupanca extends Conta{

    private int diaAniversarioPoupanca;
    public ContaPoupanca(int numero, String cpf) {
        super(numero, cpf);
    }

    public String correcao(){
        return "Correção feita";
    }
}
