package br.com.matheus.gestao_vagas.exceptions;

public class UserFoundExcetion extends RuntimeException {
    public UserFoundExcetion() {
        super("usuario ja existe");
    }
}
