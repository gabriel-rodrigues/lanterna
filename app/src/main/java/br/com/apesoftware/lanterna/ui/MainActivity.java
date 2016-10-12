package br.com.apesoftware.lanterna.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;



import br.com.apesoftware.lanterna.R;
import br.com.apesoftware.lanterna.factory.FactoryCamera;
import br.com.apesoftware.lanterna.interfaces.Lanterna;

public class MainActivity extends Activity implements View.OnClickListener {

    private Lanterna lanterna;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.imageView = (ImageView)this.findViewById(R.id.imagemView);
        this.imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            if(this.lanterna == null) {
                this.lanterna = FactoryCamera.criarCamera(this);
            }

            if(this.lanterna.isFlashLigado()) {
                this.lanterna.desligar();
                this.imageView.setImageResource(R.drawable.imagem_desligada);
            }
            else {
                this.lanterna.ligar();
                this.imageView.setImageResource(R.drawable.imagem_ligada);
            }
        }
        catch (Exception ex) {
            this.exibirToast(ex.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
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
