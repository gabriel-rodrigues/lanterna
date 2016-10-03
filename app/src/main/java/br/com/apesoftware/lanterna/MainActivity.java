package br.com.apesoftware.lanterna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}
