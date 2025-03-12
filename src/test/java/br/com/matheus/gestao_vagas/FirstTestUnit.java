package br.com.matheus.gestao_vagas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FirstTestUnit {
    
    @Test
    public void cal_num_two () {
        var result = calculate(2, 4);
        assertEquals(result, 6);
    }

    public static int calculate(int num1, int num2){
        return num1 + num2;
    }
}
