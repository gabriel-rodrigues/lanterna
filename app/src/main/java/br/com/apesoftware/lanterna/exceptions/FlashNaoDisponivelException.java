package br.com.apesoftware.lanterna.exceptions;


public class FlashNaoDisponivelException extends Exception {

    public FlashNaoDisponivelException() {
        super("A camera não possui um flash para ser utilizado.");
    }

    public FlashNaoDisponivelException(String message) {
        super(message);
    }
}
