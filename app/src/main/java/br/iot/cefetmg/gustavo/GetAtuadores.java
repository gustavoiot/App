package br.iot.cefetmg.gustavo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class GetAtuadores extends AsyncTask<Integer, String, Void> {
    private URL url;
    private LinearLayout painel;
    private ArrayList<Button> botoes = new ArrayList<Button>();
    private int idDispositivo;
    private Context context;
    private String mensagem;
    private MQTTPublish pub;

    public GetAtuadores(Context context, LinearLayout painel, int id_comodo) {
        this.painel = painel;
        this.context = context;
        try {
            this.url = new URL(Utilitaria.URL + ":" + Utilitaria.PORT + "/atuadores/" + id_comodo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i("GetAtuadores", "PreExecute");
    }

    @Override
    protected Void doInBackground(Integer... params) {
        String idAtuador, descricao;
        Log.i("GetAtuadores", "InBackground");

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

            for (int i = 0; i < leitura.length(); i++) {
                final JSONObject c = leitura.getJSONObject(i);
                descricao = c.getString("descricao");
                idDispositivo = c.getInt("id_dispositivo");
                TextView descricaoTextView = new TextView(context);
                descricaoTextView.setText(descricao);
                descricaoTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                final Button btnAtuador = new Button(context);
                botoes.add(i, btnAtuador);
                btnAtuador.setId(i);
                btnAtuador.setTag("off");
                btnAtuador.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher_foreground));
                btnAtuador.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                btnAtuador.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v) {
                        //Code here executes on main thread after user presses button
                        int id = v.getId();
                        if(btnAtuador.getTag().equals("off")) {
                            btnAtuador.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher_foreground_on));
                            mensagem = id + "1";
                            Log.i("GetAtuadores", "Ligar " + mensagem);
                            btnAtuador.setTag("on");
                        } else {
                            btnAtuador.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_launcher_foreground));
                            mensagem = id + "0";
                            Log.i("GetAtuadores", "Ligar " + mensagem);
                            btnAtuador.setTag("off");
                        }
                        //pub = new MQTTPublish(context.getApplicationContext(), "comandoproatuador", mensagem);
                        pub = new MQTTPublish(context.getApplicationContext(), "gustavoiot_" + idDispositivo, mensagem);
                        pub.execute();
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
        Log.i("GetAtuadores", "PostExecute");
        for(Button btn:botoes) {
            painel.addView(btn);
        }
    }

}
