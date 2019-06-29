package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CadastroLocal extends AppCompatActivity {
    private EditText editTextDescricao, editTextLatitude, editTextLongitude;
    private TextView textViewResponse;
    private Button btnSalvar;
    private Integer id_usuario;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);

        //para todos os campos
        editTextDescricao = (EditText) findViewById(R.id.descricao);
        editTextLatitude = (EditText) findViewById(R.id.latitude);
        editTextLongitude = (EditText) findViewById(R.id.longitude);
        textViewResponse = (TextView) findViewById(R.id.textView_response);

        id_usuario = getIntent().getIntExtra("id_usuario",0);
    }

    public void cadastrarLocal(View view){
        Map<String, String> postData = new HashMap<>();

        postData.put("id_usuario",id_usuario.toString());
        //para todos campos
        String descricao = String.valueOf(editTextDescricao.getText());
        postData.put("descricao", descricao);

        String latitude = String.valueOf(editTextLatitude.getText());
        postData.put("latitude", latitude);

        String longitude = String.valueOf(editTextLongitude.getText());
        postData.put("longitude", longitude);

        PostLocal post = new PostLocal(this ,postData, textViewResponse);
        post.execute();
    }



    public void chamaCadastroComodo(View view) {
        //Intent intent = new Intent(this, ComodoActivity.class);
        //startActivity(intent);

    }

    public void chamaCadastro(View view) {
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }
}
