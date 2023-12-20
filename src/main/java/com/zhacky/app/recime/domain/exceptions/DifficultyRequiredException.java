package com.zhacky.app.recime.domain.exceptions;

import org.springframework.web.bind.MissingRequestValueException;

public class DifficultyRequiredException extends MissingRequestValueException {
    public DifficultyRequiredException(String errorMessage) {
        super(errorMessage);
    }
}
