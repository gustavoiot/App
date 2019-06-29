package br.iot.cefetmg.gustavo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MQTTPublish extends AsyncTask<Integer, Integer, Integer> {
    private String topico, menssagem;
    private Context context;

    public MQTTPublish(Context context, String topico, String menssagem) {
        this.topico = topico;
        this.menssagem = menssagem;
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(context, Utilitaria.BROKER,
                        clientId);
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Log.d("teste", "onSuccess");

                    byte[] encodedPayload = new byte[0];
                    try {
                        encodedPayload = menssagem.getBytes("UTF-8");
                        MqttMessage message = new MqttMessage(encodedPayload);

                        message.setRetained(true);
                        client.publish(topico, message);
                    } catch (UnsupportedEncodingException | MqttException e) {
                        e.printStackTrace();
                    }
                    Log.i("teste", "Publicar " + topico + " " + menssagem);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Log.d("teste", "onFailure");

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return null;
    }
}
