package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TelaPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void chamarCadastro(View view) {
        //Intent intent = new Intent(this, CadastroLocal.class);
        Intent intent = new Intent(this, CadastroUsuario.class);
        startActivity(intent);
    }

    public void chamarLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
