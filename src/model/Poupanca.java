/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author aluno
 */
public class Poupanca extends Banco implements AplicacaoFinanceira {
    
    private int aniversario;
    
    public Poupanca(String nome,String agencia,int numero){
        this(nome,agencia,numero,1);
    }
    public Poupanca(String nome, String agencia, int numero,int aniversario) {
        super(nome, agencia, numero);
        setAniversario(aniversario);
    }
    
        
    public void setAniversario(int aniversario){
        if(aniversario >= 1 && aniversario <= 28) this.aniversario = aniversario;
        else throw new IllegalArgumentException("Fora do intervalo");
    }
    public int getAniversario(){
        return this.aniversario; 
    }
    
    public void sacar(double valor){
        BigDecimal num = BigDecimal.valueOf(valor);
        num.setScale(2, RoundingMode.HALF_UP);
        sacar(num);
    }
    
    @Override
    public void sacar(BigDecimal valor){
        if(valor.compareTo(BigDecimal.ZERO) < 1){
            throw new IllegalArgumentException("Não há Saldo");   
        }
        else if (valor.compareTo(saldo) == 1){
            throw new IllegalArgumentException("Não há Saldo");
        }
        else saldo = saldo.subtract(valor);
    }
    
    @Override
    public void calcularRendimento(String taxa){
        calcularRendimento(new BigDecimal(taxa).setScale(5, RoundingMode.HALF_UP));
    }

    @Override
    public void calcularRendimento(double taxa){
        calcularRendimento(new BigDecimal(taxa).setScale(5, RoundingMode.HALF_UP));
    }

    @Override
    public void calcularRendimento(BigDecimal taxa) {
        BigDecimal juros = saldo.multiply(taxa);
        juros = juros.setScale(2, RoundingMode.HALF_UP);
        this.depositar(juros);
    }
    
    public String toString(){
        return("-------------------Poupança------------------\n" +
               "Banco.................:" + getNome()+ "\n" +
               "Agência...............:" + getAgencia() + "\n" +
               "Conta.................:" + getNumero() + "\n" +
               "Saldo.................:" + getSaldo() + "\n" +
               "Aniversario...........:" + getAniversario()+ "\n" +
               "---------------------------------------------");
    }
    
    
    
   
    
   
}
    
