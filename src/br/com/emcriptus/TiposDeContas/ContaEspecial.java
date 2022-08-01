package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.Scanner;

public class  ContaEspecial extends Conta {
    private double limiteEspecial;

    public ContaEspecial(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
        this.limiteEspecial = 1000;
    }

    /*
    public void usarLimite (double valor) { LOGICA DA CONTA
     */

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
            System.out.println("MOVIMENTO - C-Crédito || MOVIMENTO - D-debito");
        }

        movimentoInformado = sc.nextLine().toUpperCase().trim();
        //caso a pessoa digite algo diferente de s e n
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D"))) {
            System.out.println(String.format("A opção digitada %s não é valida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        //convertendo valor informado para enumerador do tipo de movimentacao (credito ou debito)
        if (movimentoInformado.equals("C")) {
            tipoMovimentacao = TipoMovimentacao.CREDITO;
        } else if (movimentoInformado.equals("D")) {
            tipoMovimentacao = TipoMovimentacao.DEBITO;
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
            // if (valorMovimentacao < getSaldo()) {


            if (getSaldo() < 0){
                if(limiteEspecial < valorMovimentacao) {
                    System.out.println("Não foi possivel efetuar o débito");
                    return 0;
                }
                usarEspecial();
                return 1;
            }
            else{
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
                return 1;
            }


        } else if (movimentoInformado.toUpperCase().trim().equals("C")) {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));

            return 1;
        }
        else {
            return 0;
        }
        //return 0;
    }
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
    public void usarEspecial() {

        double limiteAnterior =  limiteEspecial; //1000
        limiteEspecial = limiteEspecial + getSaldo(); //diminuir no limite > 900
        //double diferenca = limiteAnterior - limiteEspecial; //1000 - 900 = 100
       // this.listaMovimentacoes.add(new Movimentacao(diferenca, TipoMovimentacao.LIMITEESPECIAL, this));
        System.out.println("Limite Especial: R$" +limiteEspecial);

    }
}