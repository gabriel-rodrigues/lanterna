package br.com.apesoftware.lanterna.classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;

import br.com.apesoftware.lanterna.interfaces.Lanterna;



@SuppressWarnings("deprecation")
public class CameraNaoSuportada implements Lanterna {

    private Camera camera;
    private boolean flashLigado = false;
    private boolean flashDisponivel = false;
    private Camera.Parameters parameters;

    public CameraNaoSuportada(final Context contexto) {
        this.flashDisponivel = contexto.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        this.prepareCamera();
    }


    private void prepareCamera ()  {

        if (this.camera == null) {
            try {
                this.camera     = Camera.open();
                this.parameters = this.camera.getParameters();
                if (this.flashDisponivel) {
                    this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    this.camera.setParameters(this.parameters);
                    this.camera.startPreview();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public void ligar() {

        if(!this.flashLigado) {
            this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            this.camera.setParameters(this.parameters);
            this.flashLigado = true;
        }
    }

    @Override
    public void desligar() {

        if(this.flashLigado) {
            this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            this.camera.setParameters(this.parameters);
            this.flashLigado = false;
        }
    }

    @Override
    public boolean isLigada() {
        return this.flashLigado;
    }

}
