package br.com.matheus.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrosMessageDTO {

    private String message;
    private String field;

}
