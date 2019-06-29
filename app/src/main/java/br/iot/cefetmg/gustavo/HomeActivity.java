package br.iot.cefetmg.gustavo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Button> botoes = new ArrayList<Button>();

    private String mensagem;
    private MQTTPublish pub;

    private LinearLayout painel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        painel = findViewById(R.id.painel);
        painel.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout painelInterno = new LinearLayout(this);
        painelInterno.setOrientation(LinearLayout.VERTICAL);

        // Coloque em cada loop que processa o JSON para criar a interface com os botões
        for(int i = 0; i < 3; i++) {
            TextView descricaoTextView = new TextView(this);
            descricaoTextView.setText("Texto " + i);
            descricaoTextView.setGravity(Gravity.CENTER_HORIZONTAL);

            final Button btnAtuador = new Button(this);
            botoes.add(i, btnAtuador);
            //btnAtuador.setText("" + i);
            btnAtuador.setId(i);
            btnAtuador.setTag("off");
            btnAtuador.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher_foreground));
            btnAtuador.setLayoutParams(p);
            btnAtuador.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    //Code here executes on main thread after user presses button
                    int id = v.getId();
                    //Button botao = botoes.get(id);
                    if(btnAtuador.getTag().equals("off")) {
                        btnAtuador.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher_foreground_on));
                        mensagem = id + "1";
                        Log.i("Home Ligar", mensagem);
                        btnAtuador.setTag("on");
                    } else {
                        btnAtuador.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher_foreground));
                        mensagem = id + "0";
                        Log.i("Home Desligar", mensagem);
                        btnAtuador.setTag("off");
                    }
                    pub = new MQTTPublish(getApplicationContext(), "PFCEnvia", mensagem);
                    pub.execute();
                }
                /*public void onClick(View v) {
                    //Code here executes on main thread after user presses button
                    int id = v.getId();
                    Button botao = botoes.get(id);
                    if(botao.getTag().equals("off")) {
                        botao.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher_foreground_on));
                        Log.i("teste", "Ligar" + botao.getId());
                        mensagem = id + "1";
                        botao.setTag("on");
                    } else {
                        botao.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_launcher_foreground));
                        Log.i("teste", "Desligar" + botao.getId());
                        mensagem = id + "0";
                        botao.setTag("off");
                    }
                    pub = new MQTTPublish(getApplicationContext(), "PFCEnvia", mensagem);
                    pub.execute();
                }*/
            });

            painelInterno.addView(descricaoTextView);
            painelInterno.addView(btnAtuador);
        }
        painel.addView(painelInterno);
    }
}
