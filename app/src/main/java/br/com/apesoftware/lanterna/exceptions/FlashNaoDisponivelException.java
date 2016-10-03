package br.com.apesoftware.lanterna.exceptions;

/**
 * Created by gabrielllbsb on 03/10/16.
 */

public class FlashNaoDisponivelException extends Exception {

    private final String MENSAGEM = "A camera não possui um flash para ser utilizado.";

    @Override
    public String getMessage() {
        return MENSAGEM;
    }
}
