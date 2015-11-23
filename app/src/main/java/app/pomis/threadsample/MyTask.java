package app.pomis.threadsample;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by romanismagilov on 23.11.15.
 */
public class MyTask extends AsyncTask {

    String responseString;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.w("threadTest: ", "onPreExecute in " + Thread.currentThread().getName() + " thread");

    }


    @Override
    protected Object doInBackground(Object[] params) {
        try {
            URL url = new URL((String) params[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String response = Converter.convertStreamToString(in);
            responseString = response;
            connection.disconnect();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        try {
            JSONObject jsonObject = new JSONObject(responseString.substring(responseString.indexOf("{")));
            JSONObject query = jsonObject.getJSONObject("query");
            JSONObject results = query.getJSONObject("results");
            JSONArray rate = results.getJSONArray("rate");

            String result = "";
            result += "$= "+rate.getJSONObject(0).get("Rate")+ " p.\n";
            result += "EUR= "+rate.getJSONObject(1).get("Rate")+ " p.\n";
            MainActivity.instance.onCurrenciesLoaded(result);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
