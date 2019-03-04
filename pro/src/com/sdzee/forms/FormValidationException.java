package com.sdzee.forms;

@SuppressWarnings("serial")
public class FormValidationException extends Exception {

	/*
     * Constructeur
     */
    public FormValidationException( String message ) {
        super( message );
    }
}