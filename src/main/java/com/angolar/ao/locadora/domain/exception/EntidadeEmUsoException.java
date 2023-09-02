package com.angolar.ao.locadora.domain.exception;

import org.springframework.stereotype.Component;


public class EntidadeEmUsoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public EntidadeEmUsoException( String msg ) {
        super(msg);
    }
}
