package com.angolar.ao.locadora.domain.exception;


public class EntidadeMovieComMesmoTitulo extends RuntimeException {

    public EntidadeMovieComMesmoTitulo(String titulo) {
        super(titulo);
    }
}
