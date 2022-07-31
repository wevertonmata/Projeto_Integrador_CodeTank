package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

public abstract class Conta {

    private final int numero;
    private final String cpf;

    private final String cnpj;
    private double saldo;
    private final String nome;

    public ArrayList<Movimentacao> listaMovimentacoes = new ArrayList<Movimentacao>();

    private boolean ativo;


    public Conta(int numero, String cpf, String nome) {
        this.numero = numero;
        this.cpf = cpf;
        this.cnpj = "N/A";
        this.nome = nome;
    }
    public Conta(String cnpj, int numero , String nome) {
        this.numero = numero;
        this.cpf = "N/A";
        this.cnpj = cnpj;
        this.nome = nome;
    }


    public String getCnpj() {
        return cnpj;
    }


    public boolean isAtivo() {
        return ativo;
    }

    public String getNome() {
		return nome;
	}


	public String getCpf() {
        return cpf;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        double valorSaldo = 0;
        for (int i = 0; i < listaMovimentacoes.size(); i++) {
            if(listaMovimentacoes.get(i).getConta()==this)
            {
                valorSaldo += listaMovimentacoes.get(i).getValor();
            }
        }
        return valorSaldo;



    }
    public int getMovimentacoes() {
        int movimentacoes = 0;
        for (int i = 0; i < listaMovimentacoes.size(); i++) {
            if(listaMovimentacoes.get(i).getConta()==this)
            {
                movimentacoes++;
            }
        }
        return movimentacoes;

    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }// pode setar??

    public double debito(double valor){ //Perguntar o professor se pode ser publico
//        saldo -= valor;
        return  0;
    }

    public void credito(double valor){
    	System.out.println("Crédito efetuado com sucesso R$" + valor);
//        saldo += valor;
//        System.out.println("Saldo Atual R$" + saldo);
    };

    public static int gerarNumConta(ArrayList<Integer> contas) {
        Random random = new Random();

        int numConta = (random.nextInt(99999 - (10000 - 1)) + 10000); //Valor aleatorio 10000 até 99999

//        for(int i = 0; i <= contas.size(); i++ ){
//            while(contas.get(i) == numConta){
//                numConta = (random.nextInt(99999 - (10000 - 1)) + 10000); //Novo numero aleatorio
//            }
//        }

        for (int i: contas) { //foreach contas[i]
            while(i == numConta){
                numConta = (random.nextInt(99999 - (10000 - 1)) + 10000); //Novo numero aleatorio
            }
        }

        return numConta;
    }



    public abstract int movimento();

}

