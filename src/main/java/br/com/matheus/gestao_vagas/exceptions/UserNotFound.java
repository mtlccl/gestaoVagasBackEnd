package br.com.matheus.gestao_vagas.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("user not found");
      }
}
