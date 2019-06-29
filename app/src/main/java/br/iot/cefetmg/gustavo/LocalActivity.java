package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalActivity extends AppCompatActivity {
    private EditText editTextComodo;
    private String localSelecionado = null; // Indicará que comodo foi selecionado
    private int idUsuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

      //  editTextComodo = (EditText) findViewById(R.id.comodo);

        idUsuario = getIntent().getIntExtra("id_usuario",0);
        Log.i("idUsuario",""+idUsuario);
        LinearLayout painel = findViewById(R.id.painel);
        painel.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        new GetLocais(this, painel, idUsuario).execute();


    }

    public void cadastrarLocal(View view){
        Intent intent = new Intent(this, CadastroLocal.class);
        intent.putExtra("id_usuario",idUsuario);
        this.startActivity(intent);
    }


}
