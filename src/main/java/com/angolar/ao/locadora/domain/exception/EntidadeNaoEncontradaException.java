package com.angolar.ao.locadora.domain.exception;

import org.springframework.stereotype.Component;


public class EntidadeNaoEncontradaException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EntidadeNaoEncontradaException( String msg ) {
        super(msg);
    }
}
