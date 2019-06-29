package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastroDispositivo extends AppCompatActivity{
    private EditText editTextDispositivo;
    private TextView textViewResponse;
    private String dispositivoSelecionado = null; // Indicará que comodo foi selecionado
    private Integer id_comodo;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_dispositivo);

        editTextDispositivo = (EditText) findViewById(R.id.dispositivo);
        textViewResponse = (TextView) findViewById(R.id.textViewResponse);

        id_comodo = getIntent().getIntExtra("id_comodo",0);


    }

    public void cadastrarDispositivo(View view) {
        Map<String, String> postData = new HashMap<>();

        postData.put("id_comodo",id_comodo.toString());

        String dispositivo = String.valueOf(editTextDispositivo.getText());
        postData.put("descricao", dispositivo);

        //PostLocal post = new PostLocal(postData, textViewResponse);

        PostDispositivo post = new PostDispositivo(this,postData, textViewResponse);
        post.execute();
    }

    public void chamaAtuador(View view) {
        //Intent intent = new Intent(this, CadastroAtuador.class);
        //startActivity(intent);
    }

    public void chamaComodo(View view) {

    }
}
