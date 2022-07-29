package br.com.emcriptus.TiposDeContas;

public class ContaEstudantil extends Conta {

    public ContaEstudantil(int numero, String cpf, String nome)
    {
        super(numero, cpf, nome);

    }

    @Override
    public int movimento() {
        return 0;
    }

    public String emprestimoEstudantil(double valor) {


        if (valor > 5000) {
            return "Limite de empréstimo excedido :(";
        }

        super.credito(valor);
        ++ valor;

        return "Seu empréstimo chegou :)";
    }
}

