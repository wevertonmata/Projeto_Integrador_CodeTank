package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.util.Scanner;

public class ContaEstudantil extends Conta {

    private double limiteEstudantil;

    public ContaEstudantil(int numero, String cpf, String nome)
    {
        super(numero, cpf, nome);
        limiteEstudantil = 5000;

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
            System.out.println("Não é possível fazer mais operações");
            return 0;
        }
        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito || E-Empréstimo: || S-Ativa/Desativa Conta:");
        } else {
            System.out.println("MOVIMENTO - D-Débito || C-Crédito || E-Empréstimo: || S-Ativa/Desativa Conta:");
        }
        movimentoInformado = sc.nextLine().toUpperCase().trim();
        //caso a pessoa digite algo diferente de s e n
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D") || movimentoInformado.equals("E") || (movimentoInformado.equals("S")))) {
            System.out.println(String.format("A opção digitada %s não é válida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        if((movimentoInformado.equals("C")  || movimentoInformado.equals("D")|| movimentoInformado.equals("E")) && !getAtivo()){
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
            System.out.println("Limite máximo de empréstimo R$" + limiteEstudantil);
        }
        //pegando o valor da transaçao informada
        System.out.println("Valor do movimento: R$");

        double valor = sc.nextDouble();
        sc.nextLine();

        while (valor <= 0) {

            valor = sc.nextDouble();
            sc.nextLine();

            while (valor <= 0){
                valor = sc.nextDouble();
                sc.nextLine();
            }
        }


        valorMovimentacao = valor;

        //inserir a movimentacao após a transacao

        if (movimentoInformado.toUpperCase().trim().equals("D")) {
            if (getSaldo() < valor) {
                System.out.println("Valor maior que saldo atual. Não é possível efetuar o débito");
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
            if(usarEstudantil(valor))
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

    //seta credito se tem limite disponviel
    public boolean usarEstudantil(double valor){
            if(valor > limiteEstudantil){
                System.out.println("Limite de empréstimo excedido");
                return false;
            }
            else{
                System.out.println("Empréstimo concedido com sucesso");
                limiteEstudantil = limiteEstudantil - valor;
                return true;
            }
    }
}

