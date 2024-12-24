package ru.prorain.exception;

public class NotUniquePlayerNameException extends RuntimeException {
    public NotUniquePlayerNameException(String nameMustBeUnique) {
        super(nameMustBeUnique);
    }
}
