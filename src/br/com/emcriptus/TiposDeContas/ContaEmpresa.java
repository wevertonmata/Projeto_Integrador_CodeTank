package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.Scanner;

public class ContaEmpresa extends Conta {

    private double emprestimoEmpresa;

    public ContaEmpresa( String _cnpj,int _numero, String _nome) {
        super(_cnpj,_numero, _nome);
       emprestimoEmpresa = 10000;
    }

    @Override
    public int movimento() {
        String movimentoInformado;
        TipoMovimentacao tipoMovimentacao = null;
        double valorMovimentacao = 0;
        Conta contaMovimentacao = this;

        Scanner sc = new Scanner(System.in);

        if(getMovimentacoes()>=10)
        {
            System.out.println("Nao é possivel fazer mais operacoes");
            return 0;
        }
        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito || E-Empréstimo: ||  S-Ativa/Desativa Conta:");
        } else {
            System.out.println("MOVIMENTO - D-debito || C-Crédito || E-Empréstimo: || S-Ativa/Desativa Conta:");
        }
        movimentoInformado = sc.nextLine().toUpperCase().trim();
        //caso a pessoa digite algo diferente de s e n

        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D") || movimentoInformado.equals("E") || (movimentoInformado.equals("S")))) {
            System.out.println(String.format("A opção digitada %s não é valida", movimentoInformado));
            System.out.println("Digite novamente");

            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        if((movimentoInformado.equals("C")  || movimentoInformado.equals("D") || movimentoInformado.equals("E")) && !getAtivo()){
            System.out.println("A conta está inativada.");
            return 0;
        } else if (movimentoInformado.equals("S")) {
            alterarStatus();
            return 1;
        }

        //convertendo valor informado para enumerador do tipo de movimentacao (credito ou debito)
        if (movimentoInformado.equals("C")) {
            tipoMovimentacao = TipoMovimentacao.CREDITO;
        } else if (movimentoInformado.equals("D")) {
            tipoMovimentacao = TipoMovimentacao.DEBITO;
        } else if (movimentoInformado.equals("E")) {
            tipoMovimentacao = TipoMovimentacao.EMPRESTIMO;
           System.out.println("Limite máximo de empréstimo R$" + emprestimoEmpresa);
        }


        //pegando o valor da transaçao informada
        System.out.println("Valor do movimento: R$");

        double valor = sc.nextDouble();
        sc.nextLine();

        while (valor <= 0) {
            valor = sc.nextDouble();
            sc.nextLine();
        }

        valorMovimentacao = valor;

        //inserir a movimentacao após a transacao

        if (movimentoInformado.toUpperCase().trim().equals("D")) {
            if (getSaldo() < valor) {
                System.out.println("Valor maior que saldo atual. Não é possível efetuar o debito");
                return 0;
            } else if (valorMovimentacao < getSaldo()) {
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
//                debito(valor);
                return 1;
            }
        } else if (movimentoInformado.toUpperCase().trim().equals("C")) {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
//            credito(valor);
            return 1;
        } else if (movimentoInformado.toUpperCase().trim().equals("E")) {
            if(pedirEmprestimo(valor))
            {
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, TipoMovimentacao.CREDITO, contaMovimentacao));
                return 1;
            }
            return 0;
        } else {
            return 0;
        }
        return 0;
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
    public boolean pedirEmprestimo (double valor){
        if ( valor > emprestimoEmpresa) {
            System.out.println("Limite de emprestimo excedido");
            return false;
        }
        else {
            emprestimoEmpresa = emprestimoEmpresa - valor;
            System.out.println("Empréstimo concedido com sucesso");
            return true;
        }

    }
}

