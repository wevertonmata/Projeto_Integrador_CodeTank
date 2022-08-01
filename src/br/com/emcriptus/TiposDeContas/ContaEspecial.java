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

        System.out.println("MOVIMENTO - C-Crédito || D-Débito || S-Ativa/Desativa Conta:");
        System.out.println("Limite especial disponível: R$" +limiteEspecial);


        movimentoInformado = sc.nextLine().toUpperCase().trim();
        //caso a pessoa digite algo diferente de s e n
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D") || (movimentoInformado.equals("S")))) {
            System.out.println(String.format("A opção digitada %s não é válida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        if((movimentoInformado.equals("C")  || movimentoInformado.equals("D")) && !getAtivo()){
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
        }
        //pegando o valor da transaçao informada
        System.out.println("Valor do movimento: R$");

        double valor = sc.nextDouble();
        sc.nextLine();

            while (valor <= 0){
                valor = sc.nextDouble();
                sc.nextLine();
            }

        valorMovimentacao = valor;

        //inserir a movimentacao após a transacao

        if (movimentoInformado.toUpperCase().trim().equals("D")) {
            if (getSaldo() < valorMovimentacao){ //(100 < 150)
                if( (limiteEspecial + getSaldo()) < valorMovimentacao) { //(1000 +100 < 150)
                    System.out.println("Não foi possível efetuar o débito");
                    return 0;
                }
                System.out.println("Você terá que usar o limite especial.");
                usarEspecialDebito(valorMovimentacao);
                return 1;
            }
            else{
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
                return 1;
            }
        }
        else if (movimentoInformado.toUpperCase().trim().equals("C")) {
            if(limiteEspecial < 1000){
                usarEspecialCredito(valorMovimentacao);
                return 1;
            }else{
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
                return 1;
            }

        }
        else {
            return 0;
        }
        //return 0;

    }

    //seta credito se tem limite disponviel
    public void usarEspecialDebito(double valor) {

        this.listaMovimentacoes.add(new Movimentacao(valor, TipoMovimentacao.DEBITO, this));
        //double valorRetiradoSaldo =   valor - getSaldo(); //150 - 100 = 50
        double limiteAnterior = limiteEspecial;
        limiteEspecial = limiteEspecial + getSaldo(); // 1000 - 50 = 950
        double difenca = limiteAnterior - limiteEspecial;
        this.listaMovimentacoes.add(new Movimentacao(difenca, TipoMovimentacao.CREDITO, this));
        //System.out.println("Valor retirado do saldo R$" + valorRetiradoSaldo);
        System.out.println("Limite após transação R$" + limiteEspecial);
        System.out.println("Saldo após transação R$" + getSaldo());
    }

    public void usarEspecialCredito(double valor) {

        limiteEspecial += valor; // 800 + 300 = 1100
        if(limiteEspecial > 1000){
            double diferenca = limiteEspecial - 1000; //100
            limiteEspecial -= diferenca; //1100 - 100
            this.listaMovimentacoes.add(new Movimentacao(diferenca, TipoMovimentacao.CREDITO, this)); // Coloco no Saldo = 100
        }

        System.out.println("Limite após transação R$" + limiteEspecial);
        System.out.println("Saldo após transação R$" + getSaldo());
    }
}