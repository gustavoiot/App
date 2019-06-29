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

public class CadastroComodo extends AppCompatActivity {
    private EditText editTextComodo;
    private TextView textViewResponse;

    private int idSelecionado;
    private Integer id_local;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_comodo);

        editTextComodo = (EditText) findViewById(R.id.comodo);
        textViewResponse = (TextView) findViewById(R.id.textView_response);

        id_local = getIntent().getIntExtra("id_local",0);

        Log.i("CadastroCom: id_local",""+id_local);

    }

    public void cadastrarComodo(View view) {
        Map<String, String> postData = new HashMap<>();

        postData.put("id_local",id_local.toString());

        String comodo = String.valueOf(editTextComodo.getText());
        postData.put("descricao", comodo);

        //String idlocal = "1";
        //postData.put("id_local", idlocal);
       //postData.put("id_local", "" + idSelecionado);

        PostComodo post = new PostComodo(this,postData, textViewResponse);
        post.execute();
    }

    public void chamaAtuador(View view) {
        //new GetComodo(this, 1).execute();
        //Log.i("Comodos", "" + "chamarAtuador");
    }

    public void chamaCadastroLocal(View view) {
        Intent intent = new Intent(this, CadastroLocal.class);
        startActivity(intent);
    }
}
