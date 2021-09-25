/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 *
 * @author Lucas Hype
 */
public class Despesa {

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    private Date data;
    private String descricao;
    private BigDecimal valor = BigDecimal.ZERO;

    public MeioDePagamento getConta() {
        return conta;
    }

    public void setConta(MeioDePagamento conta) {
        this.conta = conta;
    }
    private MeioDePagamento conta;
    
    public Despesa(Date data, String descricao,double valor) {
        this(data,descricao,new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP));
    }
    public Despesa(Date data, String descricao,BigDecimal valor) {
        setData(data);
        setDescricao(descricao);
        setValor(valor);
    }
    public String toString(){
        Conta ct = (Conta) conta;
        return("Data: "+getData() + " Descricao: " + getDescricao() + " Valor: " + getValor() + " Paga com " + ct.getNome());
    }
    
    

}
