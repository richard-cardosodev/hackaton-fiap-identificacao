package br.fiap.projeto.identificacao.entity.vo;

import br.fiap.projeto.identificacao.usecase.exception.EntradaInvalidaException;

import java.util.regex.Pattern;

public class Email {

    private static final String PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String EMAIL_INVALIDO = "E-mail inválido!";
    private final String endereco;

    public Email(String endereco) {
        this.endereco = endereco;
    }

    public void validar() throws EntradaInvalidaException {
        if (Pattern.matches(PATTERN, endereco)) {
            return;
        }
        throw new EntradaInvalidaException(String.format("%s(%s)", EMAIL_INVALIDO, endereco));
    }

    public static Email fromString(String endereco) {
        return new Email(endereco);
    }

    public String getEndereco() {
        return endereco;
    }
}

