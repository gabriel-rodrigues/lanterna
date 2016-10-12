package br.com.apesoftware.lanterna.exceptions;


public class CameraNaoDisponivelException extends Exception {


    public CameraNaoDisponivelException() {
        super("Não foi possível localizar uma camera neste dispositivo.");

    }

    public CameraNaoDisponivelException(String message) {
        super(message);
    }

}
