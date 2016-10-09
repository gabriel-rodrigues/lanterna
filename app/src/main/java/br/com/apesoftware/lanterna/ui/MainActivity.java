package br.com.apesoftware.lanterna.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import br.com.apesoftware.lanterna.R;
import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;
import br.com.apesoftware.lanterna.factory.FactoryCamera;
import br.com.apesoftware.lanterna.interfaces.Lanterna;

public class MainActivity extends Activity implements View.OnClickListener {

    private Lanterna lanterna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button)this.findViewById(R.id.btnLigar);
        button.setOnClickListener(this);

        AdView adView       = (AdView)this.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {

        try {

            if(this.lanterna == null) {
                this.lanterna = FactoryCamera.criarCamera(this);
            }

            if(this.lanterna.isLigada()) {
                this.lanterna.desligar();
            }
            else {
                this.lanterna.ligar();
            }
        }
        catch (FlashNaoDisponivelException ex) {
            this.exibirToast(ex.getMessage());
        }
        catch (CameraNaoDisponivelException ex) {
            this.exibirToast(ex.getMessage());
        }
        catch (Exception ex) {
            this.exibirToast(ex.getMessage());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(this.lanterna.isLigada()) {
            this.lanterna.desligar();
        }
    }

    private void exibirToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
