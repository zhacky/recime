package com.zhacky.app.recime.domain.exceptions;

public class DifficultyNotFoundException extends IllegalArgumentException {
    public DifficultyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
