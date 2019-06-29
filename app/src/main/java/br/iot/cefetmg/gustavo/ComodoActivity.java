package br.iot.cefetmg.gustavo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ComodoActivity extends AppCompatActivity {

    private EditText editTextComodo;
    private String localSelecionado = null; // Indicará que comodo foi selecionado
    private String idLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comodo);

        idLocal = getIntent().getStringExtra("idLocal");
        int id = Integer.parseInt(idLocal);
        Log.i("ComodoAct: id_local", ""+id);
        //idLocal=1;
        LinearLayout painel = findViewById(R.id.painel);
        painel.setOrientation(LinearLayout.HORIZONTAL);

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        new GetComodos(this, painel, id).execute();
    }

    public void cadastrarComodo(View view){
        Intent intent = new Intent(this, CadastroComodo.class);
        intent.putExtra("id_Local",idLocal);
        this.startActivity(intent);
    }
}
