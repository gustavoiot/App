package br.iot.cefetmg.gustavo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Pós Iot Sistemas de Computação.
 */

public class PostAtuador extends AsyncTask<Integer, String, String> {
    private URL url;
    private JSONObject postData;
    private TextView textViewResponse;

    public PostAtuador(Map<String, String> postData, TextView textViewResponse) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
            this.textViewResponse = textViewResponse;
        }

        try {
            //this.url = new URL("http://35.199.118.151:4001/cadastroComodo");
            this.url = new URL(Utilitaria.fullURL + "/atuadores/cadastrarAtuador");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i("Tarefa 1 - status", "PreExecute");
        textViewResponse.setText("Conectando...");
    }

    @Override
    protected String doInBackground(Integer... params) {
        String retorno = null;
        Log.i("Tarefa 1 - status", "InBackground");

        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");

            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();
            if (statusCode == 200) {
                retorno = "Enviado!";
            } else {
                retorno = "Erro ao Enviar!";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textViewResponse.setText(s);
    }
}