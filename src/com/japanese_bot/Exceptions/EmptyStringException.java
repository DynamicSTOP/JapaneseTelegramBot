package com.japanese_bot.Exceptions;

public class EmptyStringException extends Exception {
    @Override
    public String getMessage() {
        return "empty string";
    }
}
