package br.iot.cefetmg.gustavo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Pós Iot Sistemas de Computação.
 */
public class GetUsuario extends AsyncTask<Integer, String, Integer> {
    private URL url;
    private Integer id;
    private Integer retorno = null;
    private  Context context;

    public GetUsuario(Context context, Integer id, String nome , String senha) {
        //this.textRetorno = textRetorno;
        this.id=id;
        this.context=context;
        try {
            this.url = new URL(Utilitaria.URL + ":" + Utilitaria.PORT + "/users/" + nome + "/"+senha+"");
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
    protected Integer doInBackground(Integer... params) {
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

                JSONObject c = leitura.getJSONObject(0);


                retorno = c.getInt("id_usuario");


            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Log.i("Tarefa 1 - status", "InBackground - end");
        Log.i("Background", ""+retorno);
        return retorno;
    }

    @Override
    protected void onPostExecute(Integer id) {
        Log.i("Tarefa 1 - status", "PostExecute");
        //textRetorno.setText(retorno);



        Intent intent;
        intent = new Intent(context, LocalActivity.class);
        intent.putExtra("id_usuario",id);
        context.startActivity(intent);



    }

}