package br.com.apesoftware.lanterna.classes;

import android.content.Context;
import android.hardware.Camera;

import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;



@SuppressWarnings("deprecation")
public class CameraNaoSuportada extends CameraAbstract {

    private Camera camera;
    private Camera.Parameters parameters;

    public CameraNaoSuportada(Context contexto) throws FlashNaoDisponivelException, CameraNaoDisponivelException {
        super(contexto);
    }

    @Override
    public void ligar() throws CameraNaoDisponivelException {
        if(this.camera == null && this.parameters == null) {
           this.prepareCamera();
        }

        this.configurarParameters(Camera.Parameters.FLASH_MODE_TORCH);
        this.setFlashLigado(true);
    }

    @Override
    public void desligar() {
        this.configurarParameters(Camera.Parameters.FLASH_MODE_OFF);
        this.setFlashLigado(false);
        this.camera.release();
        this.camera     = null;
        this.parameters = null;
    }

    @Override
    protected void throwExceptionParaCameraIndisponivel() throws CameraNaoDisponivelException {
        try {
            this.camera = Camera.open();

            if(this.camera == null)
                throw  new CameraNaoDisponivelException(this.mensagemParaCameraNaoDisponivel);
        }
        catch (RuntimeException ex) {
            throw new CameraNaoDisponivelException(this.mensagemParaCameraEmUso);
        }
        catch (Exception ex)  {
            throw new CameraNaoDisponivelException(ex.getMessage());
        }
    }

    @Override
    protected void prepareCamera() throws CameraNaoDisponivelException {
        this.throwExceptionParaCameraIndisponivel();
        this.parameters = this.camera.getParameters();
        this.parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        this.camera.setParameters(this.parameters);
        this.camera.startPreview();
    }

    private void configurarParameters(String modo) {
        this.parameters.setFlashMode(modo);
        this.camera.setParameters(this.parameters);

    }


}
