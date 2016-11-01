package br.com.apesoftware.lanterna.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.apesoftware.lanterna.R;
import br.com.apesoftware.lanterna.factory.FactoryCamera;
import br.com.apesoftware.lanterna.interfaces.Lanterna;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements View.OnClickListener {

    private Lanterna lanterna;

    @BindView(R.id.imagemView)
    protected ImageView imageView;

    @BindView(R.id.adView)
    protected AdView adView;

    @BindString(R.string.descricao_desligada)
    protected String descricaoDesligada;

    @BindString(R.string.descricao_ligada)
    protected String descricaoLigada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.configurarAdMob();
    }

    private void configurarAdMob() {
        AdRequest adRequest = new AdRequest.Builder().build();
        this.adView.loadAd(adRequest);

    }

    @Override
    protected void onPause() {

        if(this.adView != null){
            this.adView.pause();
        }

        super.onPause();


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(this.adView != null) {
            this.adView.resume();
        }
    }

    @OnClick(R.id.imagemView)
    @Override
    public void onClick(View v) {
        try {

            if(this.lanterna == null) {
                this.lanterna = FactoryCamera.criarCamera(this);
            }

            if(this.lanterna.isFlashLigado()) {
                this.lanterna.desligar();
                this.imageView.setImageResource(R.drawable.imagem_desligada);
                this.imageView.setContentDescription(this.descricaoDesligada);
            }
            else {
                this.lanterna.ligar();
                this.imageView.setImageResource(R.drawable.imagem_ligada);
                this.imageView.setContentDescription(this.descricaoLigada);
            }
        }
        catch (Exception ex) {
            this.exibirToast(ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {

        if(this.adView != null) {
            this.adView.destroy();
        }

        super.onDestroy();

        if(this.lanterna.isFlashLigado()) {
            try {
                this.lanterna.desligar();
            }
            catch (Exception ex) {
                this.exibirToast(ex.getMessage());
            }
        }
    }

    private void exibirToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
