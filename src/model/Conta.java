/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public abstract class Conta {
    
    private String nome;
    protected BigDecimal saldo = BigDecimal.ZERO;
    private static ArrayList<Conta> lista = new ArrayList<Conta>(50); 
    private static ArrayList<Despesa> despesas = new ArrayList<Despesa>(50); 

    public void setNome(String nome){
        if (nome.length()>= 5) this.nome = nome;
        else throw new IllegalArgumentException("Nome deve ter mais de 4 letras.");
    }
    public String getNome(){
        return this.nome; 
    }

    public BigDecimal getSaldo(){
        return this.saldo;
    }
    public abstract void sacar(BigDecimal valor);/*{
        if(valor <= 0) throw new 
            IllegalArgumentException("Saque deve ser maior que 0");
        else if(valor > this.saldo) throw new
            RuntimeException("Não há saldo suficiente");
        else this.saldo -= valor;
    }*/
    
    public void depositar(String valor){
        try{
        depositar(new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP));
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("String não pode ser convertida para numero, verifique o formato.");
        }
    }
     public void depositar(double valor){
        BigDecimal num = BigDecimal.valueOf(valor);
        num.setScale(2, RoundingMode.HALF_UP);
        depositar(num);
    }
     
    public void depositar(BigDecimal valor){
        if(valor.doubleValue() > 0){
            this.saldo = saldo.add(valor);
        }
        else throw new 
            IllegalArgumentException("Valor do deposito deve ser maior que 0");
    }
    public void transferir(Conta destino,String valor){
       transferir(destino,new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP));
    }
    public void transferir(Conta destino,double valor){
        transferir(destino,new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP));
    }
    public void transferir(Conta destino,BigDecimal valor){
        this.sacar(valor);
        destino.depositar(valor);
        
    }
    
    private void adicionaLista(){
        lista.add(this);
    }
    public static void listaTodos(){
        for (Conta c: lista){ 
            System.out.println(c);
        }
    }
            
    public Conta(String nome){
        setNome(nome);
        adicionaLista();        
    }
    
    public static void adicionaDespesa(Despesa despesa){
        despesas.add(despesa);
    }
      public static void listaDespesas(){
        for (Despesa d: despesas){ 
            System.out.println(d);
        }
    }
}
