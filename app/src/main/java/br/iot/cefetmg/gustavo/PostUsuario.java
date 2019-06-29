package br.iot.cefetmg.gustavo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class PostUsuario extends AsyncTask<Integer, String, String> {
    private URL url;
    private JSONObject postData;
    private TextView textViewResponse;
    private  Context context;
    private Integer id_usuario;

    public PostUsuario(Context context, Map<String, String> postData, TextView textViewResponse) {
        this.context=context;
        if (postData != null) {
            this.postData = new JSONObject(postData);
            this.textViewResponse = textViewResponse;
        }

        try {
            this.url = new URL(Utilitaria.URL + ":" + Utilitaria.PORT + "/users/cadastrarUsuario");
            //this.url = new URL("http://35.199.118.151:4001/users/cadastrarUsuario");
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
            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject leitura = new JSONObject(builder.toString());

            id_usuario = leitura.getInt("response");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return retorno;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        textViewResponse.setText(s);
        Log.i("id_usuario",""+id_usuario);
        Intent intent = new Intent(context, CadastroLocal.class);
        intent.putExtra("id_usuario",id_usuario);
        context.startActivity(intent);
    }
}