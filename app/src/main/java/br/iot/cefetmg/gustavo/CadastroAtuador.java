package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CadastroAtuador extends AppCompatActivity {

    private EditText editTextAtuador;
    private EditText editTextTipo;
    private TextView textView_response;
    private Integer id_comodo;
    private Integer id_dispositivo;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    Spinner sistemas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_atuador);

        editTextAtuador = (EditText) findViewById(R.id.atuador);
        textView_response = (TextView) findViewById(R.id.textView_response);

        id_comodo = getIntent().getIntExtra("id_comodo",0);
        id_dispositivo = getIntent().getIntExtra("id_dispositivo",0);
        Log.i("ComodoAct: id_comodo", "" + id_comodo);

    }


    public void chamaCadastroAtuador(View view) {

        Map<String, String> postData = new HashMap<>();


        radioSexGroup = (RadioGroup) findViewById(R.id.icone);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);
        String tipo = radioSexButton.getText().toString();
        postData.put("tipo", tipo);
        Log.i("tipo do atuador: ",""+tipo);

        postData.put("id_comodo",id_comodo.toString());
        postData.put("id_dispositivo",id_dispositivo.toString());

        //postData.put("id_dispositivo",id_dispositivo.toString());

        String atuador = String.valueOf(editTextAtuador.getText());
        postData.put("descricao", atuador);

        postData.put("estado", "desligado");

        postData.put("icone", "");


        PostAtuador post = new PostAtuador( postData, textView_response);
        post.execute();

    }

    public void chamaLocal(View view) {
        Intent intent = new Intent(this, LocalActivity.class);
        startActivity(intent);
    }

    public void chamaCadastroDispositivo(View view) {

    }

}
