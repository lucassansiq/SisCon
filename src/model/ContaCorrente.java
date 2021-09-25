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
public class ContaCorrente extends Banco implements MeioDePagamento{
    
    private BigDecimal limite = BigDecimal.ZERO;
    
    public void pagar(double valor){
        if(valor < 0) throw new
            IllegalArgumentException("Valor negativo");
        
    }
    
    public void setLimite(String limite){
        setLimite(new BigDecimal(limite).setScale(2, RoundingMode.HALF_UP));
    }
    public void setLimite(double limite){
        BigDecimal num = BigDecimal.valueOf(limite);
        num.setScale(2, RoundingMode.HALF_UP);
        setLimite(num);
        }
        
    public void setLimite(BigDecimal limite){
        if (limite.compareTo(BigDecimal.ZERO) > -1){
            this.limite = limite;
        }
        else throw new IllegalArgumentException("Limite negativo");
    }
    public BigDecimal getLimite(){
        return this.limite;
    }
        
    public ContaCorrente(String nome,String agencia,int numero){
        this(nome,agencia,numero,BigDecimal.ZERO);
    }
            
    public  ContaCorrente(String nome,String agencia,int numero,BigDecimal limite){
        super(nome,agencia,numero);
        setLimite(limite);
    }
     public  ContaCorrente(String nome,String agencia,int numero,double limite){
        super(nome,agencia,numero);
        setLimite(limite);
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
        else if (valor.compareTo(saldo.add(limite)) == 1){
            throw new IllegalArgumentException("Não há Saldo");
        }
        else saldo = saldo.subtract(valor);
    }
    
    public String toString(){
        return("---------------Conta Corrente----------------\n" +
               "Banco.................:" + getNome()+ "\n" +
               "Agência...............:" + getAgencia() + "\n" +
               "Conta.................:" + getNumero() + "\n" +
               "Saldo.................:" + getSaldo() + "\n" +
               "Limite de Crédito.....:" + getLimite()+ "\n" +
               "---------------------------------------------");
    }
    @Override
    public boolean pagar(Despesa despesa) {
        try{
        this.sacar(despesa.getValor());
        }catch (RuntimeException ex){
            return false;//deu erro - nao tem saldo - nao registrou despesa 
        }
        despesa.setConta(this);
        Conta.adicionaDespesa(despesa);//tudo ok - pagou - registrou despesa
        return true;
    }
    
}
