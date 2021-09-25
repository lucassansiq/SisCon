/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.sql.Date;
import javax.swing.JOptionPane;
import model.Carteira;
import model.Conta;
import model.ContaCorrente;
import model.Despesa;
import model.Poupanca;

/**
 *
 * @author aluno
 */
public class Principal {
    
    public static void main(String[] args) {
     
    //Para  12/05    
    //1)Implementar os try/catch para os metodos sacar,depositar,tranferir e set limite
    //2)Alterar metodos q recebem string para um bigdecimal para lancar IllegalArgumentException
    //3)Implementar verificacao de taxa de rendimento negativa para poupanca - lancar illegalArgumentException   
    //Extra:criar classe despesa:data / descricao / valor
    
        Carteira cart;
        Poupanca poupa = new Poupanca("Poupança Caixa","1112",122345,23);  
        ContaCorrente cc = new ContaCorrente("Banco Itaú","1000",2000,new BigDecimal("1000"));
        //o cc é o endereço onde será armazenado o objeto, o 'new' cria o objeto
        
        cart = new Carteira();       
        cart.depositar(new BigDecimal("100"));
        try{
            cart.sacar(new BigDecimal("50"));
        }catch(RuntimeException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        
  
        cc.depositar(10);       
        cc.sacar(new BigDecimal("100"));
        cc.transferir(poupa, new BigDecimal("500"));
        //System.out.println(cc);
        
        
        poupa.depositar(new BigDecimal("32"));
        poupa.sacar(new BigDecimal("22"));
        poupa.calcularRendimento(new BigDecimal("0.2"));
        //System.out.println(poupa);
        Conta.listaTodos();
        
                
        Despesa desp1 = new Despesa(Date.valueOf("2020-05-12"),"Almoco",30);
        System.out.println(cart);
        if (cart.pagar(desp1)) System.out.println(desp1);
        else System.out.println("Não conseguiu pagar a despesa");
        System.out.println(cart);
        
        Conta.adicionaDespesa(desp1);

    }
    
}
