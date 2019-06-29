package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity{
    private static final String USUARIO = "M";
    private static final String SENHA = "1";

    private Button btnLogar;
    private String usuario, senha;
    private Integer id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void chamaLocal(View view) {
//    Log.i("usuario:", usuario);
        usuario = String.valueOf(((EditText) findViewById(R.id.login)).getText());
        senha = String.valueOf(((EditText) findViewById(R.id.senha)).getText());

        new GetUsuario(this, id, usuario, senha).execute();
        Log.i("Locais", "" + "chamaLocal");
        Log.i("IdUsuario", "" + "onFinished _ init");


        Log.i("IdUsuario", "" + "onFinished _ end");
    }




}