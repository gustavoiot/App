package br.iot.cefetmg.gustavo;

import android.os.AsyncTask;
import android.util.Log;

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

public class GetComodo extends AsyncTask<Integer, String, ArrayList<IdDesc>> {
    private URL url;
    private Listener comodoListener;
    private ArrayList<IdDesc> valores = new ArrayList<IdDesc>();

    public GetComodo(Listener c, int idComodo) {
        //this.textRetorno = textRetorno;
        comodoListener = c;

        try {
            this.url = new URL(Utilitaria.URL + ":" + Utilitaria.PORT + "/comodos/" + idComodo);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onPreExecute() {
        Log.i("Tarefa 1 - status", "PreExecute");
        //textRetorno.setText("iniciando");
    }

    @Override
    protected ArrayList<IdDesc> doInBackground(Integer... params) {
        String retorno = "", idLocal, idComodo, descricao;
        Log.i("Tarefa 1 - status", "InBackground");

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
                JSONObject c = leitura.getJSONObject(i);

                idComodo = c.getString("id_comodo");
                descricao = c.getString("descricao");
                idLocal = c.getString("id_local");
                //valores.add(descricao);
                valores.add(new IdDesc(descricao, Integer.parseInt(idComodo)));
                retorno += "Id Comodo: " + idComodo + " - Descrição: " + descricao + "\n"; //Cria o botao de comodo
            }

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Log.i("Tarefa 1 - status", "InBackground - end");
        Log.i("Background", ""+valores);
        return valores;
    }

    protected void onPostExecute(ArrayList<IdDesc> strings) {
        Log.i("Tarefa 1 - status", "PostExecute - init");
        //textRetorno.setText(retorno);
        if(comodoListener != null) {
            Log.i("Tarefa 1 - status", "InBackground - inside");
            comodoListener.onFinished(strings);
        }
    }

}