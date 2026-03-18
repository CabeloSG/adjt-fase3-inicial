package br.com.fiap.techchallenge.auth.domain.exception;

public class UsuarioJaExisteException extends RuntimeException {

    public  UsuarioJaExisteException(String mensagem) {
        super(mensagem);
    }

}
