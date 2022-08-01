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

    public int movimento() {

        Scanner sc = new Scanner(System.in);
        String movimentoInformado;
        TipoMovimentacao tipoMovimentacao = null;
        double valorMovimentacao = 0;
        Conta contaMovimentacao = this;

        if(getMovimentacoes()>=10)
        {
            System.out.println("Não é possível fazer mais operações");
            return 0;
        }

        if (getSaldo() == 0) {
            System.out.println("MOVIMENTO - C-Crédito: || S-Ativa/Desativa Conta:");
        } else {
            System.out.println("MOVIMENTO - D-Débito || C-Crédito || M-Simular Juros Poupança: || S-Ativa/Desativa Conta:");
        }

        movimentoInformado = sc.nextLine().toUpperCase().trim();
        while (!(movimentoInformado.equals("C") || movimentoInformado.equals("D") || movimentoInformado.equals("M") || (movimentoInformado.equals("S")))) {
            System.out.println(String.format("A opção digitada %s não é válida", movimentoInformado));
            System.out.println("Digite novamente");
            movimentoInformado = sc.nextLine().toUpperCase().trim();
        }
        if((movimentoInformado.equals("C")  || movimentoInformado.equals("D") || movimentoInformado.equals("M")) && !getAtivo()){
            System.out.println("A conta está inativada.");
            return 0;
        } else if (movimentoInformado.equals("S")) {
            alterarStatus();
            return 1;
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
                System.out.println("Digite um valor maio que zero:");
                valor = sc.nextDouble();
                sc.nextLine();
            }
            valorMovimentacao = valor;
        }

        if (movimentoInformado.toUpperCase().trim().equals("D")) {
            if (getSaldo() < valorMovimentacao) {
                System.out.println("Valor maior que o saldo atual. Não é possível efetuar o débito");
                return 0;
            } else if (valorMovimentacao < getSaldo()) {
                this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
                return 1;

            }
        } else if (movimentoInformado.toUpperCase().trim().equals("C")) {
            this.listaMovimentacoes.add(new Movimentacao(valorMovimentacao, tipoMovimentacao, contaMovimentacao));
            return 1;
        } else if (movimentoInformado.toUpperCase().trim().equals("M")) {
                System.out.println("Informe a Data de Aniversário: dd/mm/aaaa");
                String DataTemporaria = sc.nextLine();
                DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate DataInformada = LocalDate.parse(DataTemporaria, DATEFORMATTER);
                System.out.println("Projeção do Saldo: R$" + correcao(DataInformada));
            return 0;
        } else {
            return 0;
        }

        return 0;
    }

    public double correcao(LocalDate dataInformada) {
        double valorSaldo = 0, somaSaldoCorrigido = 0;

        for (int i = 0; i < listaMovimentacoes.size(); i++) {
            if(listaMovimentacoes.get(i).getConta()==this)
            {
               LocalDate dataDaMovimentacao = listaMovimentacoes.get(i).getDataMovimentacao();
               Period period = Period.between(dataDaMovimentacao, dataInformada);
               somaSaldoCorrigido += listaMovimentacoes.get(i).getValor()+((listaMovimentacoes.get(i).getValor()*0.005) * period.getMonths());
            }
        }

        return somaSaldoCorrigido;

    }
}


