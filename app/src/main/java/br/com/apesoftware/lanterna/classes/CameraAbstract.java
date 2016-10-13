package br.com.apesoftware.lanterna.classes;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import br.com.apesoftware.lanterna.R;
import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;
import br.com.apesoftware.lanterna.interfaces.Lanterna;
import butterknife.BindString;
import butterknife.ButterKnife;


public abstract class CameraAbstract implements Lanterna {

    private boolean flashDisponivel = false;
    private boolean flashLigado     = false;


    protected final Context contexto;

    @BindString(R.string.erro_padrao_flash_nao_disponivel)
    protected String mensagemParaFlashNaoDisponivel;

    @BindString(R.string.erro_padrao_camera_em_uso)
    protected String mensagemParaCameraEmUso;

    @BindString(R.string.erro_padrao_camera_nao_disponivel)
    protected String mensagemParaCameraNaoDisponivel;

    public CameraAbstract(final Context contexto) throws FlashNaoDisponivelException, CameraNaoDisponivelException {
        this.contexto        = contexto;
        ButterKnife.bind(this, (Activity) this.contexto);

        this.prepararFlash();
        this.prepareCamera();

    }

    @Override
    public abstract void ligar() throws CameraNaoDisponivelException;

    @Override
    public abstract void desligar() throws CameraNaoDisponivelException;

    protected abstract void throwExceptionParaCameraIndisponivel() throws CameraNaoDisponivelException;

    public final void throwExceptionParaFlashIndisponivel() throws FlashNaoDisponivelException {
        if(!this.hasFlash())
            throw new FlashNaoDisponivelException(this.mensagemParaFlashNaoDisponivel);
    }


    private final boolean hasFlash() {
        return this.flashDisponivel;
    }

    public boolean isFlashLigado() {
        return this.flashLigado;
    }

    public void setFlashLigado(boolean flashLigado) {
        this.flashLigado = flashLigado;
    }

    protected void prepararFlash() throws FlashNaoDisponivelException {
        this.flashDisponivel = this.contexto.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        this.throwExceptionParaFlashIndisponivel();
    }

    protected abstract void prepareCamera () throws CameraNaoDisponivelException;
}
