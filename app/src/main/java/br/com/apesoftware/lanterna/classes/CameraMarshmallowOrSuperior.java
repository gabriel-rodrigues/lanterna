package br.com.apesoftware.lanterna.classes;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;


import br.com.apesoftware.lanterna.classes.callbacks.DisponibilidadeCameraMarshmallowOrSuperior;
import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;


@TargetApi(Build.VERSION_CODES.M)
public class CameraMarshmallowOrSuperior extends CameraAbstract {

    private CameraManager cameraManager;
    private String cameraId;


    public CameraMarshmallowOrSuperior(Context contexto) throws FlashNaoDisponivelException, CameraNaoDisponivelException {
        super(contexto);
    }

    @Override
    public void ligar() throws CameraNaoDisponivelException {
        try {

            this.cameraManager.setTorchMode(this.cameraId, true);
            this.setFlashLigado(true);
        }
        catch (CameraAccessException ex) {
            throw new CameraNaoDisponivelException(ex.getMessage());
        }
    }

    @Override
    public void desligar() throws CameraNaoDisponivelException {
        try {
            this.cameraManager.setTorchMode(this.cameraId, false);
            this.setFlashLigado(false);
        }
        catch (CameraAccessException ex) {
            throw new CameraNaoDisponivelException(ex.getMessage());
        }
    }

    @Override
    protected void throwExceptionParaCameraIndisponivel() throws CameraNaoDisponivelException {
        this.cameraManager   = (CameraManager)this.contexto.getSystemService(Context.CAMERA_SERVICE);


        if(this.cameraManager == null)
            throw new CameraNaoDisponivelException();

        this.throwExceptionParaCameraEmUso();
    }


    @Override
    protected void prepareCamera() throws CameraNaoDisponivelException {

        this.throwExceptionParaCameraIndisponivel();
        try {
            this.cameraId = this.cameraManager.getCameraIdList()[0];
        }
        catch (CameraAccessException ex) {
            throw new CameraNaoDisponivelException(ex.getMessage());
        }
    }


    private void throwExceptionParaCameraEmUso() throws CameraNaoDisponivelException {

        DisponibilidadeCameraMarshmallowOrSuperior callbackDispobibilidade = new DisponibilidadeCameraMarshmallowOrSuperior();
        this.cameraManager.registerAvailabilityCallback(callbackDispobibilidade, null);
        this.cameraManager.unregisterAvailabilityCallback(callbackDispobibilidade);

        if(callbackDispobibilidade.isCameraEmUso()) {
            throw new CameraNaoDisponivelException("Feche outro aplicativo que esteja fazendo uso da câmera e tente novamente.");
        }

    }

}
