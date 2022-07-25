package br.com.emcriptus.TiposDeContas;

public class ContaCorrente extends Conta{

    private int contadorTalao;

    public ContaCorrente(int numero, String cpf) {
        super(numero, cpf);
    }

    public String pediTalao(){

        if(contadorTalao == 3){
            return "Limite de talões excedidos";
        }

        super.debito(30);
        contadorTalao++;

        return "Cheque soclicitado com sucesso!";
    }
}
