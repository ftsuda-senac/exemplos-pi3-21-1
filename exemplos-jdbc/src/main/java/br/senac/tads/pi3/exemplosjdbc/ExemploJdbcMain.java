/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemplosjdbc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ftsuda
 */
public class ExemploJdbcMain {

    public static void main(String[] args) {
        System.out.println("Exemplo JDBC com Maven");
        PessoaDao dao = new PessoaDao();
        
        Scanner entrada = new Scanner(System.in);
        
        do {
            System.out.println("******** DIGITE UMA OPÇÃO ********");
            System.out.println("(1) Listar");
            System.out.println("(2) Incluir novo");
            System.out.println("(9) SAIR");
            System.out.println("Opção: ");
            
            String opcaoStr = entrada.nextLine();
            try {
                int opcao = Integer.parseInt(opcaoStr);
                
                switch (opcao) {
                    case 1:
                        List<Pessoa> resultados = dao.listar();
                        for (Pessoa p : resultados) {
                            System.out.println("ID: " + p.getId() +
                                    ", Nome: " + p.getNome() + 
                                    ", Data nascimento: " + p.getDataNascimento() + 
                                    ", Data cadastro: " + p.getDataCadastro());
                        }
                        break;
                    case 2:
                        System.out.println("Digite o nome completo: ");
                        String nome = entrada.nextLine();
                        System.out.println("Digite a data de nascimento no formato aaaa-MM-dd: ");
                        String dtNascStr = entrada.nextLine();
                        
                        Pessoa p = new Pessoa();
                        p.setNome(nome);
                        p.setDataNascimento(LocalDate.parse(dtNascStr));
                        p.setDataCadastro(LocalDateTime.now());
                        dao.incluirComTransacao(p);
                        System.out.println("======== PESSOA INCLUIDA COM SUCESSO ========");
                        break;
                    case 9:
                        entrada.close();
                        System.exit(0);
                    default:
                        System.err.println("======== OPÇÃO INVÁLIDA ========");
                }
            } catch(NumberFormatException ex) {
                System.err.println("======== OPÇÃO INVÁLIDA ========");
            }
            
        } while(true);
        
        

    }
    
}
