package br.com.apesoftware.lanterna.exceptions;


public class CameraNaoDisponivelException extends Exception {

    public static final String MENSAGEM_CAMERA_EM_USO = "Feche outro aplicativo que esteja fazendo uso da câmera e tente novamente.";

    public CameraNaoDisponivelException() {
        super("Não foi possível localizar uma câmera neste dispositivo.");

    }

    public CameraNaoDisponivelException(String message) {
        super(message);
    }

}
