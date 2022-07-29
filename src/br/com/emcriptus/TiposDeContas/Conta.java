package br.com.emcriptus.TiposDeContas;

import java.util.ArrayList;
import java.util.Random;

public abstract class Conta {

    private final int numero;
    private final String cpf;
    private double saldo;
    private String nome;

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    private boolean ativo;

    public Conta(int numero, String cpf, String nome) {
        this.numero = numero;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
        return cpf;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double debito(double valor){ //Perguntar o professor se pode ser publico
        saldo -= valor;
        return  saldo;
    }

    public void credito(double valor){
        saldo += valor;
        System.out.println("Saldo efetuado, com sucesso SALDO: R$" + saldo);
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
