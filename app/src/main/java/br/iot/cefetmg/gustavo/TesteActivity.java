package br.iot.cefetmg.gustavo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TesteActivity extends AppCompatActivity {
    private Button btn, btnLigar0, btnLigar1;//, btnDesligar0, btnDesligar1, btnLigar1, btnDesligar2, btnLigar2, btnDesligar3, btnLigar3, btnDesligar4, btnLigar4, btnLigar5, btnDesligar5;
    //int cont = 0;
    private boolean btn0_ligado = false, btn1_ligado = false;
    private String mensagem;

    private MQTTPublish pub;

    private LinearLayout painelHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste_activity);

        painelHome = (LinearLayout) findViewById(R.id.painelHome);
        Button myButton = new Button(this);
        RelativeLayout myLayout = new RelativeLayout(this);
        myLayout.addView(myButton);
        setContentView(myLayout);
    }
}
