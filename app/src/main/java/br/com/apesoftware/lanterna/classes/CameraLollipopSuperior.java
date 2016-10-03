package br.com.apesoftware.lanterna.classes;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;


import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;
import br.com.apesoftware.lanterna.interfaces.Lanterna;


public class CameraLollipopSuperior implements Lanterna {

    private CameraDevice camera;
    private CameraManager cameraManager;
    private boolean flashDisponivel = false;
    private boolean flashLigado = false;
    private String cameraId;


    public CameraLollipopSuperior(final Context contexto)
            throws FlashNaoDisponivelException, CameraNaoDisponivelException {

        this.flashDisponivel = contexto.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(!this.flashLigado)
            throw new FlashNaoDisponivelException();

        this.cameraManager   = (CameraManager)contexto.getSystemService(Context.CAMERA_SERVICE);

        if(this.cameraManager == null)
            throw new CameraNaoDisponivelException();

        this.setCameraId();
    }


    private void  setCameraId() {
        try {
            this.cameraId = this.cameraManager.getCameraIdList()[0];
        }
        catch (CameraAccessException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void ligar() {
        if(!this.flashLigado) {
            try {
                this.cameraManager.setTorchMode(this.cameraId, !this.flashLigado);
                this.flashLigado = true;
            }
            catch (CameraAccessException ex) {
                ex.printStackTrace();
            }
        }

        return;
    }

    @Override
    public void desligar() {
        if(this.flashLigado) {
            try {
                this.cameraManager.setTorchMode(this.cameraId, !this.flashLigado);
                this.flashLigado = false;
            }
            catch (CameraAccessException ex) {
                ex.printStackTrace();
            }
        }

        return;
    }

    @Override
    public boolean isLigada() {
        return this.flashLigado;
    }
}
