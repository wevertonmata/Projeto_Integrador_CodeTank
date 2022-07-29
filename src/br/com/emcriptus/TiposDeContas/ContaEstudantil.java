package br.com.emcriptus.TiposDeContas;

public class ContaEstudantil extends Conta {

    private double limiteEstudantil;

    public ContaEstudantil(int numero, String cpf, String nome)
    {
        super(numero, cpf, nome);
        limiteEstudantil = 5000;//todo fazer constante


    }

    @Override
    public int movimento() {
        return 1;
    }

    @Override
    public void credito(double valor) {
        super.credito(valor);
    }

    @Override
    public double debito(double valor) {
        return super.debito(valor);
    }

    @Override
    public double getSaldo() {
        return super.getSaldo();
    }

    //seta credito se tem limite disponviel
    public boolean usarEstudantil(double valor){
            if(valor > limiteEstudantil)
                return false;
            else
            {
                limiteEstudantil = limiteEstudantil - valor;
                credito(valor);
                return true;
            }
    }

    public boolean Ativo(){
        return this.isAtivo();
    }
}

