package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Predicate;

public abstract class Conta {

    private int numero;
    private String cpf;

    private String cnpj;
    private double saldo;
    private String nome;

    public ArrayList<Movimentacao> listaMovimentacoes = new ArrayList<Movimentacao>();
    private boolean ativo;

    public Conta(int _numero, String _cpf, String _nome) {
        this.numero = _numero;
        this.cpf = _cpf;
        this.cnpj = "N/A";

        this.nome = _nome;

        this.ativo = true;
    }
    public Conta(String _cnpj, int _numero , String _nome) {
        this.numero = _numero;
        this.cpf = "N/A";
        this.cnpj = _cnpj;
        this.nome = _nome;

        this.ativo = true;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public boolean getAtivo() {

        return this.ativo;
    }

    public String getNome() {

        return this.nome;
	}


	public String getCpf() {

        return this.cpf;
    }

    public int getNumero() {
        return this.numero;
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
                if(!listaMovimentacoes.get(i).getTipoMovimentacao().equals(TipoMovimentacao.SIMULARPOUPANCA)){
                    movimentacoes++;
                }
            }
        }
        return movimentacoes;

    }

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

        for (int i: contas) { //foreach contas[i]
            while(i == numConta){
                numConta = (random.nextInt(99999 - (10000 - 1)) + 10000); //Novo numero aleatorio
            }
        }
        return numConta;
    }


    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void alterarStatus(){
        setAtivo(!getAtivo());
        if (getAtivo()) {
            System.out.println("Conta Ativada");
        } else {
            System.out.println("Conta Desativada");
        }
    }


    public abstract int movimento();



}

