package com.techub.api.exception;

public class EmailAlredyExistsExeception extends RuntimeException{
    public EmailAlredyExistsExeception () {
        super("Email já está em uso");
    }
}
