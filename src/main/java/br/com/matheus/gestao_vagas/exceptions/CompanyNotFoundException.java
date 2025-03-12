package br.com.matheus.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException () {
        super("job not found");
      }
}
