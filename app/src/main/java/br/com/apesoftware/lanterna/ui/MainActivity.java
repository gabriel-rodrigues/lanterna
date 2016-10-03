package br.com.apesoftware.lanterna.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.apesoftware.lanterna.R;
import br.com.apesoftware.lanterna.exceptions.CameraNaoDisponivelException;
import br.com.apesoftware.lanterna.exceptions.FlashNaoDisponivelException;
import br.com.apesoftware.lanterna.factory.FactoryCamera;
import br.com.apesoftware.lanterna.interfaces.Lanterna;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Lanterna lanterna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)this.findViewById(R.id.btnLigar);
        button.setOnClickListener(this);
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
