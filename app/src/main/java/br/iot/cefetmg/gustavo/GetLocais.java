package br.iot.cefetmg.gustavo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetLocais extends AsyncTask<Integer, String, Void> {
    private URL url;
    private LinearLayout painel;
    private ArrayList<Button> botoes = new ArrayList<Button>();
    private int idBotao = 0;
    private Intent intent;
    private Context context;

    public GetLocais(Context context, LinearLayout painel, int id_usuario) {
        //this.textRetorno = textRetorno;
        this.painel = painel;
        this.context = context;
        intent = new Intent(context, ComodoActivity.class);
        try {
            this.url = new URL(Utilitaria.URL + ":" + Utilitaria.PORT + "/locais/" + id_usuario);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i("GetLocais", "PreExecute");
        //textRetorno.setText("iniciando");
    }

    @Override
    protected Void doInBackground(Integer... params) {
        String idLocal, descricao;
        Log.i("GetLocais", "InBackground");

        try {

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONArray leitura = new JSONArray(builder.toString());

            Log.i("Teste", builder.toString());
            for (int i = 0; i < leitura.length(); i++) {
                final JSONObject c = leitura.getJSONObject(i);

                descricao = c.getString("descricao");
                //idLocal = c.getString("id_local");

                final Button btnLocal = new Button(context);
                botoes.add(i, btnLocal);
                btnLocal.setId(idBotao++);
                btnLocal.setText(descricao);
               // btnLocal.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher_foreground));
                btnLocal.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                btnLocal.setOnClickListener(new View.OnClickListener(){
                        public void onClick(View v) {
                            try {
                                Log.i("GetLocais: idLocal",""+c.getString("id_local"));
                                intent.putExtra("idLocal", c.getString("id_local"));
                                context.startActivity(intent);

                            } catch(JSONException e) {
                                e.printStackTrace();
                            }
                        }
                });

            }

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        Log.i("GetLocais", "PostExecute");
        for(Button btn:botoes) {
            painel.addView(btn);
        }

    }

}