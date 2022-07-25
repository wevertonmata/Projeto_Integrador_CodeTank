package entities;

public class ContaCorrente extends Conta{

    private double limiteEstudantil;

    public ContaCorrente(int numero, String cpf) {
        super(numero, cpf);
    }

    public void usarEstudantil(double valor){
        super.credito(valor);
        limiteEstudantil -= valor;
    }

}
