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
import java.util.ArrayList;

public class GetLocal extends AsyncTask<Integer, String, ArrayList<IdDesc>> {
    //private TextView textRetorno;
    private URL url;
    private Listener localListener;
    private ArrayList<String> valores = new ArrayList<String>();
    private ArrayList<IdDesc> ids_descs = new ArrayList<IdDesc>();

    public GetLocal(Listener c, int idUsuario) {
        //this.textRetorno = textRetorno;
        localListener = c;

        try {
            this.url = new URL(Utilitaria.URL  + ":" + Utilitaria.PORT + "/locais/" + idUsuario);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        Log.i("Tarefa 1 - status", "PreExecute");
        //textRetorno.setText("Iniciando...");
    }

    @Override
    protected ArrayList<IdDesc> doInBackground(Integer... params) {
        String retorno = "", idLocal, longitude, latitude, descricao;
        Log.i("Tarefa 1 - status", "InBackground - init");

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

                idLocal = c.getString("id_local");
                descricao = c.getString("descricao");
                latitude = c.getString("latitude");
                longitude = c.getString("longitude");
                valores.add(descricao);
                ids_descs.add(new IdDesc(descricao, Integer.parseInt(idLocal)));
                retorno += "Id Local: " + idLocal + " - Descrição: " + descricao + "\n"; //Cria o botao de comodo
            }

            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Log.i("Tarefa 1 - status", "InBackground - end");
        //Log.i("Background", ""+valores);
        Log.i("Background", ""+ids_descs);
        //return valores;
        return ids_descs;
    }

    //@Override
    protected void onPostExecute(ArrayList<IdDesc> strings) {
        Log.i("Tarefa 1 - status", "PostExecute - init");
        //textRetorno.setText(retorno);
        if(localListener != null) {
            Log.i("Tarefa 1 - status", "InBackground - inside");
            localListener.onFinished(strings);
        }
    }
}
