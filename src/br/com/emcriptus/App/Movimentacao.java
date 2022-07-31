package br.com.emcriptus.App;

import br.com.emcriptus.TiposDeContas.Conta;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


public class Movimentacao {
    //UUID - guid (código único de identificacao - https://en.wikipedia.org/wiki/Universally_unique_identifier#History
    //com objetivo de certificar que a movimentacao é única e válida, sempre precisar codificar uma validacao
    private UUID numeroMovimentacao ;
    private double valorMovimentacao = 0; //garantir que nao seja nulo
    private TipoMovimentacao tipoMovimentacao;
    private Conta contaMovimentacao;

    //gerada pelo java quando a movimentacao é criada
    private LocalDateTime dataMovimentacao;

    //garantindo que a instancia da classe nao pode ser sem parâmetros
    public Movimentacao() {
        if (this.valorMovimentacao <= 0 || this.tipoMovimentacao == null || this.contaMovimentacao == null) {
            throw new RuntimeException("Não é possível criar uma movimentação sem informar valor, tipo e conta");
        }
    }


    public Movimentacao(double _valorMovimentacao, TipoMovimentacao _tipoMovimentacao, Conta _contaMovimentacao){
        //validando se os dados foram informados corretamente
        if(_valorMovimentacao <= 0 )
            throw new RuntimeException("Valor de movimentação informado incorretamente");
        if(_tipoMovimentacao == null )
            throw new RuntimeException("Tipo de movimentação informado incorretamente");
        if(_contaMovimentacao == null )
            throw new RuntimeException("Conta informada incorretamente");
        //setando atributos para movimentacoes
        this.numeroMovimentacao = UUID.randomUUID();
        this.dataMovimentacao = LocalDateTime.now();

        //setando atributos do objeto passados por parâmetro no construtor
        this.tipoMovimentacao = _tipoMovimentacao;
        this.contaMovimentacao = _contaMovimentacao;

        //aqui ocorre validaçoes de crédito e armazena a data da movimentação
        if (this.tipoMovimentacao == TipoMovimentacao.CREDITO){
            this.valorMovimentacao = _valorMovimentacao * (+1); //transforma em positivo
        } else if (this.tipoMovimentacao == TipoMovimentacao.DEBITO) {
            this.valorMovimentacao = _valorMovimentacao * (-1);//garantir valor negativo
        }//garantir que a classe especializada de movimentacao trate os tipos de operacoes implementados

    }


    public Conta getConta(){
        return this.contaMovimentacao;
    }

    public double getValor(){
        return this.valorMovimentacao;
    }




}
