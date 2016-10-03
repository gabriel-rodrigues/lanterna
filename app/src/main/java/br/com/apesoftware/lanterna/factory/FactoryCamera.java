package br.com.apesoftware.lanterna.factory;

import android.content.Context;
import android.os.Build;

import br.com.apesoftware.lanterna.classes.CameraLollipopSuperior;
import br.com.apesoftware.lanterna.classes.CameraNaoSuportada;
import br.com.apesoftware.lanterna.interfaces.Lanterna;

/**
 * Created by gabrielllbsb on 03/10/16.
 */

 public class FactoryCamera {

    public static Lanterna criarCamera(Context context) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new CameraLollipopSuperior(context);
        }
        else {

            return new CameraNaoSuportada(context);
        }
    }

}
