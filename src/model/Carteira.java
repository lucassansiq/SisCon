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
public class Carteira extends Conta implements MeioDePagamento{
    
    public Carteira(){
        this("Carteira");
    }

    public Carteira(String nome) {
        super(nome);
    }
    
    public void pagar (){
        
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
    
    public String toString(){
        return(getNome() + ":R$" + getSaldo());
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
