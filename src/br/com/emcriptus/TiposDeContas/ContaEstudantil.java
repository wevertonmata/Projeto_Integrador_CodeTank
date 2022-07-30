package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.Scanner;

public class ContaEstudantil extends Conta {

    private double limiteEstudantil;

    public ContaEstudantil(int numero, String cpf, String nome)
    {
        super(numero, cpf, nome);
        limiteEstudantil = 5000; //todo fazer constante
    }
    //metodo movimento retorna 1 se ocorreu com sucesso e 0 se houve falha
    @Override
    public int movimento() {
        String movimentoInformado;
        TipoMovimentacao tipoMovimentacao = null;
        double valorMovimentacao = 0;
        Conta contaMovimentacao = this;

        Scanner sc = new Scanner(System.in);
        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-Crédito:");
        }
        movimentoInformado = sc.nextLine().toUpperCase().trim();
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D"))){
            System.out.println(String.format("A opção digitada %s não é valida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        //convertendo valor informado para enumerador do tipo de movimentacao (credito ou debito)
        if (movimentoInformado.equals("C")){
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

        if (valor > 0){
            valorMovimentacao = valor;
        }

        //inserir a movimentacao após a transacao

        if (movimentoInformado.toUpperCase() == "D") {
            if (getSaldo() < valor) {
                System.out.println("Valor maior que saldo atual. Não é possível efeituar o debito");
                return 0;
            }
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao,tipoMovimentacao,contaMovimentacao));
            debito(valor);
            return 1;
        } else if (movimentoInformado.toUpperCase() == "C") {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao,tipoMovimentacao,contaMovimentacao));
            credito(valor);
            return 1;
        } else if (usarEstudantil(valorMovimentacao)) {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
            credito(valor);
            return 1;
            }
        else {
            return 0;
        }
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
}

