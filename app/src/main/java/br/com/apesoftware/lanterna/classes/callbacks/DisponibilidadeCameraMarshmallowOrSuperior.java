package br.com.apesoftware.lanterna.classes.callbacks;

import android.annotation.TargetApi;
import android.hardware.camera2.CameraManager;
import android.os.Build;



@TargetApi(Build.VERSION_CODES.M)
public class DisponibilidadeCameraMarshmallowOrSuperior extends CameraManager.AvailabilityCallback {

    private boolean cameraEmUsoPorOutroApp = false;

    @Override
    public void onCameraAvailable(String cameraId) {
        super.onCameraAvailable(cameraId);
        this.cameraEmUsoPorOutroApp = false;
    }

    @Override
    public void onCameraUnavailable(String cameraId)  {
        super.onCameraUnavailable(cameraId);
        this.cameraEmUsoPorOutroApp = true;
    }

    public boolean isCameraEmUso() {
        return cameraEmUsoPorOutroApp;
    }
}
