package br.iot.cefetmg.gustavo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MQTTSubscribed extends AsyncTask<Integer, Integer, Integer> {
    private Button btn;

    public MQTTSubscribed(Button btn) {
        this.btn = btn;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        Log.i("teste", "Subscribed ");
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        btn.setBackgroundResource(R.mipmap.lamp_off);
    }
}
