package br.com.atividade.sistemabancario.Enum;

public enum Role {
    ADMIN("Administrator"),
    USER("User"),
    MANAGER("Manager");

    private final String description;

    Role(String description) {
        this.description = description;
    }

}
