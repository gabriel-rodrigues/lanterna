package br.com.apesoftware.lanterna.exceptions;

/**
 * Created by gabrielllbsb on 03/10/16.
 */

public class CameraNaoDisponivelException extends Exception {

    private final String MENSAGEM = "Não foi possível localizar uma camera.";

    @Override
    public String getMessage() {
        return MENSAGEM;
    }
}
