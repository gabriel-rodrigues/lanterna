package br.com.apesoftware.lanterna.factory;

import android.content.Context;
import android.os.Build;

import br.com.apesoftware.lanterna.classes.CameraLollipopSuperior;
import br.com.apesoftware.lanterna.classes.CameraNaoSuportada;
import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;
import br.com.apesoftware.lanterna.interfaces.Lanterna;


 public class FactoryCamera {

    public static Lanterna criarCamera(Context context) throws FlashNaoDisponivelException, CameraNaoDisponivelException {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new CameraLollipopSuperior(context);
        }
        else {

            return new CameraNaoSuportada(context);
        }
    }

}
