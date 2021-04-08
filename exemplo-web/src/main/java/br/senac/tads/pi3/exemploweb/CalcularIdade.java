/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3.exemploweb;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ftsuda
 */
public class CalcularIdade {
    
    public long calcular(String data) {
        long idade = -1;
        if (data != null) {
            idade = ChronoUnit.YEARS.between(LocalDate.parse(data), LocalDate.now());
        }
        return idade;
    }
    
}
