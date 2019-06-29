package br.iot.cefetmg.gustavo;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetAtuador extends AsyncTask<Integer,String,String> {
    private TextView textRetorno;
    private URL url;

    public GetAtuador(TextView textRetorno, int idAtuador){
        this.textRetorno = textRetorno;

        try {
            this.url = new URL(Utilitaria.URL + "/atuadores/" + idAtuador);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute(){
        Log.i("Tarefa 1 - status", "PreExecute");
        textRetorno.setText("iniciando");
    }

    @Override
    protected String doInBackground(Integer... params) {
        String idAtuador, tipo, estado, descricao, idComodo, idDispositivo, retorno = "";
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

                //id_atuador, tipo, estado, descricao, id_comodo, id_dispositivo;
                idAtuador = c.getString("id_atuador");
                tipo = c.getString("tipo");
                estado = c.getString("estado");
                descricao = c.getString("descricao");
                idComodo = c.getString("id_comodo");
                idDispositivo = c.getString("id_dispositivo");
                retorno += "Id Atuador: "+idAtuador + " - Tipo: " + tipo + "\n"; //Cria o botao de comodo
            }

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    @Override
    protected void onPostExecute(String retorno){
        Log.i("Tarefa 1 - status", "PostExecute");
        textRetorno.setText(retorno);
    }

}