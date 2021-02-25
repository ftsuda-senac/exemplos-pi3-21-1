/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplogit;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        
        System.out.println("================");
        System.out.println("Projeto exemplo-git");
        System.out.println("================");
        
        
        int i = 1;
        int j = 1;
        System.out.println("i++ = " + i++);
        System.out.println("++j = " + ++j);
        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite a tabuada que será calculada: ");
        String val = entrada.nextLine();
        
        int tab = Integer.parseInt(val);
        System.out.println("Tabuada do " + tab);
        for (int k = 1; k < 11; k++) {
            System.out.println("" + tab + " x " + k + " = " + tab * k);
        }
        System.out.println("Alteração 2 - Alexsandro");
        
        System.out.println("Mais uma mensagem");
    }
    
}
