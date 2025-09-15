package br.com.atividade.sistemabancario.Exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
