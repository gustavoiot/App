package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AtuadorActivity extends AppCompatActivity {

    private EditText editTextComodo;
    private String localSelecionado = null; // Indicará que comodo foi selecionado
    private String idComodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atuador);

        idComodo = getIntent().getStringExtra("idComodo");
        int id = Integer.parseInt(idComodo);
        Log.i("ComodoAct: id_comodo", "" + id);

        LinearLayout painel = findViewById(R.id.painel);
        painel.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //LinearLayout painelInterno = new LinearLayout(this);
        //painelInterno.setOrientation(LinearLayout.VERTICAL);

        new GetAtuadores(this, painel, id).execute();
    }

    public void cadastrarAtuador(View view) {
        Intent intent = new Intent(this, CadastroAtuador.class);
        int id = Integer.parseInt(idComodo);
        intent.putExtra("id_comodo", id);

        this.startActivity(intent);

    }
}
