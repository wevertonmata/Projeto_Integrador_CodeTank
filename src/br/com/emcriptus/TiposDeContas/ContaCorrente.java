package br.com.emcriptus.TiposDeContas;
/*
* Author: Weverton Mata
*/

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.Scanner;

public class    ContaCorrente extends Conta{



    public ContaCorrente(int numero, String cpf, String nome) {

        super(numero, cpf, nome);
    }

    @Override
    public double debito(double valor){
//        double saldo =  super.getSaldo() - valor;
//        super.setSaldo(saldo);
//        return  saldo;
        return 0;
    }

    public int getTaloes() {
        int contadorTalao = 0;
        for (int i = 0; i < listaMovimentacoes.size(); i++) {
            if(listaMovimentacoes.get(i).getConta()==this) ;
            {
                if (listaMovimentacoes.get(i).getTipoMovimentacao().equals(TipoMovimentacao.SOLICITARTALAO)) {
                    contadorTalao++;
                }

            }
        }
        return contadorTalao;

    }
    public void pediTalao(){
        if(super.getSaldo() < 30){
            System.out.println("Saldo insuficiente para ter talão de cheque R$" + super.getSaldo());
            return;
        }

        if(getTaloes() >= 3){
            System.out.println("Limite de talões excedidos");
            return;
        }

        listaMovimentacoes.add(new Movimentacao(30, TipoMovimentacao.SOLICITARTALAO, this));
        System.out.println("Cheque solicitado com sucesso! \n Seu novo saldo é: R$" + this.getSaldo());
    }

    public int movimento(){
        Scanner sc = new Scanner(System.in);
        double valor = 0;
        int movimentacoes = this.getMovimentacoes();
        boolean solicitaInput = false;
        if(movimentacoes ==10) {
            System.out.println("MOVIMENTO - T: Solicitar talao");
        }
        else if ((getSaldo() == valor)) {
            solicitaInput = true;
            System.out.println("MOVIMENTO - C-Crédito:");
        }
        else if((getSaldo() > valor)) {
            solicitaInput = true;
            System.out.println("MOVIMENTO - D-debito ou C-Crédito:");
        }
        String movimento = sc.nextLine().toUpperCase().trim();
        while (!(movimento.equals("C") || movimento.equals("D") || ( movimento.equals("T")) && getMovimentacoes()>=10)){
            movimento = sc.nextLine().toUpperCase().trim();
        }

        if(solicitaInput)
        {
            System.out.println("Valor do movimento: R$");

            valor = sc.nextDouble();
            sc.nextLine();

            while (valor <= 0){
                valor = sc.nextDouble();
                sc.nextLine();
            }

            switch (movimento.toUpperCase()) {
                case ("D") -> {
                    if(getSaldo() < valor){
                        System.out.println("Valor maior que saldo atual. Não é possível efeituar o debito");
                        return 0;
                    }
//                debito(valor);
                    listaMovimentacoes.add(new Movimentacao(valor, TipoMovimentacao.DEBITO, this));
                    return 1;
                }
                case ("C") -> {
                    listaMovimentacoes.add(new Movimentacao(valor, TipoMovimentacao.CREDITO, this));
                    return 1;
                }
            }
        }
        else{
            if(movimento.toUpperCase()=="T")
            {
                this.pediTalao();
                return 1;
            }
        }

        return 0;
    }

}
