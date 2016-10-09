package br.com.apesoftware.lanterna.interfaces;


import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;

public interface Lanterna {

    void ligar() throws CameraNaoDisponivelException;
    void desligar() throws CameraNaoDisponivelException;
    boolean isFlashLigado();
}
