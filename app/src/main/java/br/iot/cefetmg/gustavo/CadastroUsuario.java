package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CadastroUsuario extends AppCompatActivity {

    private EditText editTextNome, editTextsenha, editTextEmail, editTextSexo, editTextDataNasc;
    private TextView textView_response;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //para todos os campos
        editTextNome = (EditText) findViewById(R.id.nome);
        editTextsenha = (EditText) findViewById(R.id.senha);
        editTextEmail = (EditText) findViewById(R.id.email);
        //editTextSexo = (EditText) findViewById(R.id.sexo);
        editTextDataNasc = (EditText) findViewById(R.id.dataNasc);
        textView_response = (TextView) findViewById(R.id.textView_response);
    }

    public void cadastrarUsuario(View view){
        Map<String, String> postData = new HashMap<>();

        //para todos campos
        String nome = String.valueOf(editTextNome.getText());
        postData.put("nome", nome);

        String senha = String.valueOf(editTextsenha.getText());
        postData.put("senha", senha);

        String email = String.valueOf(editTextEmail.getText());
        postData.put("email", email);

        //String sexo = String.valueOf(editTextSexo.getText());
       // postData.put("sexo", sexo);

        String dataNasc = String.valueOf(editTextDataNasc.getText());
        postData.put("dta_nascimento", dataNasc);


        radioSexGroup = (RadioGroup) findViewById(R.id.icone);
        int selectedId = radioSexGroup.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);
        String sexo = radioSexButton.getText().toString();
        postData.put("sexo", sexo);
        Log.i("sexo: ",""+sexo);

        PostUsuario post = new PostUsuario(this, postData, textView_response);
        post.execute();
    }

    public void chamaCadastroLocal(View view)
    {

    }
    public void chamaTelaPrincipal(View view)
    {
        Intent intent = new Intent(this, TelaPrincipalActivity.class);
        startActivity(intent);
    }

}