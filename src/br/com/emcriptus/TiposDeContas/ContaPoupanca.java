package br.com.emcriptus.TiposDeContas;

import br.com.emcriptus.App.Movimentacao;
import br.com.emcriptus.App.TipoMovimentacao;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ContaPoupanca extends Conta {

    private LocalDate dtAniversario;

    public ContaPoupanca(int numero, String cpf, String nome) {
        super(numero, cpf, nome);
    }

    @Override
    public double debito(double valor) {
        double saldo = super.getSaldo() - valor;
        super.setSaldo(saldo);
        return saldo;
    }

    public int movimento() {
        Scanner sc = new Scanner(System.in);
        String movimentoInformado;
        TipoMovimentacao tipoMovimentacao = null;
        double valorMovimentacao = 0;
        Conta contaMovimentacao = this;

        if(getMovimentacoes()>=10)
        {
            System.out.println("Nao é possivel fazer mais operacoes");
            return 0;
        }

        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito:");
        } else {
            System.out.println("MOVIMENTO - D-debito ou C-Crédito ou M-Simular Juros Poupança:");
        }

        movimentoInformado = sc.nextLine().toUpperCase().trim();
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D") || movimentoInformado.equals("M"))) {
            System.out.println(String.format("A opção digitada %s não é valida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }

        if (movimentoInformado.equals("C")) {
            tipoMovimentacao = TipoMovimentacao.CREDITO;
        } else if (movimentoInformado.equals("D")) {
            tipoMovimentacao = TipoMovimentacao.DEBITO;
        } else if (movimentoInformado.equals("M")) {
            tipoMovimentacao = TipoMovimentacao.SIMULARPOUPANCA;
        }

        if((movimentoInformado.equals("C") || movimentoInformado.equals("D"))){
            System.out.println("Valor do movimento: R$");
            double valor = sc.nextDouble();
            sc.nextLine();
            while (valor <= 0) {
                valor = sc.nextDouble();
                sc.nextLine();
            }
            valorMovimentacao = valor;
        }



        if (movimentoInformado.toUpperCase().trim().equals("D")) {
            if (getSaldo() < valorMovimentacao) {
                System.out.println("Valor maior que saldo atual. Não é possível efetuar o debito");
                return 0;
            } else if (valorMovimentacao < getSaldo()) {
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
                return 1;
            }
        } else if (movimentoInformado.toUpperCase().trim().equals("C")) {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
            return 1;
        } else if (movimentoInformado.toUpperCase().trim().equals("M")) {
                System.out.println("Informe a Data de Depósito: dd/mm/aaaa");
                String DataTemporaria = sc.nextLine();
                DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DataInformada = LocalDate.parse(DataTemporaria, DATEFORMATTER);
                System.out.println("Projeção do Saldo: R$" + getSaldoProjeçao(DataInformada));
            return 0;
        } else {
            return 0;
        }



        return 0;
    }

    @Override
    public void credito(double valor) {
       double saldo = super.getSaldo() + valor;
        super.setSaldo(saldo);
    }
    public void correcao() {
        setSaldo(( getSaldo()*0.005)+getSaldo());


    }

    public double getSaldoProjeçao(LocalDate dataInformada) {
        double valorSaldo = 0, somaSaldoCorrigido = 0;

        for (int i = 0; i < listaMovimentacoes.size(); i++) {
            if(listaMovimentacoes.get(i).getConta()==this)
            {
               DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate dataDaMovimentacao = listaMovimentacoes.get(i).getDataMovimentacao();
               Period period = Period.between(dataDaMovimentacao, dataInformada);
               somaSaldoCorrigido += listaMovimentacoes.get(i).getValor()+((listaMovimentacoes.get(i).getValor()*0.005) * period.getMonths());
            }
        }
        return somaSaldoCorrigido;
    }

    //getSaldoProjeçao return double, (DateTime)
}


