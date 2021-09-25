/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author aluno
 */
public abstract class Banco extends Conta {
    
    private String agencia;
    private int numero;
    
    public void setAgencia(String agencia){
        if(agencia.length()>=4) this.agencia = agencia;
        else throw new IllegalArgumentException("A agência deve conter mais de 3 letras");
    }
    public String getAgencia(){
        return this.agencia;
    }
    public void setNumero(int numero){
        if(numero > 0) this.numero = numero;
        else throw new IllegalArgumentException("Numero menor que 1");
    }
    public int getNumero(){
        return this.numero;
    }
    public Banco(String nome,String agencia,int numero){
        super(nome);
        setAgencia(agencia);
        setNumero(numero);
        
    }
}

