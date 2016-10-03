package br.com.apesoftware.lanterna.classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import br.com.apesoftware.lanterna.interfaces.Lanterna;


/**
 * Created by gabrielllbsb on 03/10/16.
 */

@SuppressWarnings("deprecation")
public class CameraNaoSuportada implements Lanterna {

    private Camera camera;
    private boolean flashLigado = false;
    private boolean flashDisponivel = false;
    private Camera.Parameters parameters;

    public CameraNaoSuportada(final Context contexto) {
        this.flashDisponivel = contexto.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        this.setCamera();
        this.setParameters();
    }

    private void setCamera  () {
        this.camera  = Camera.open();

    }

    private void setParameters() {
        this.parameters = this.camera.getParameters();
    }

    @Override
    public void ligar() {

        if(!this.flashLigado) {
            this.parameters = this.camera.getParameters();
            this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
            this.camera.setParameters(this.parameters);
            this.camera.startPreview();
            this.flashLigado = true;
        }

        return;
    }

    @Override
    public void desligar() {
        if(this.flashLigado) {
            this.parameters = camera.getParameters();
            this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            this.camera.setParameters(this.parameters);
            this.camera.stopPreview();
            this.flashLigado = false;
        }

        return;
    }

    @Override
    public boolean isLigada() {
        return this.flashLigado;
    }

}
