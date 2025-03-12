package br.com.matheus.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() {
        super("job not found");
      }
}
