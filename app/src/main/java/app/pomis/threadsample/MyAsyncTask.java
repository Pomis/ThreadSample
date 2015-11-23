package app.pomis.threadsample;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by romanismagilov on 23.11.15.
 */
public class MyAsyncTask extends AsyncTask {
    String TAG = "my_tag";


    @Override
    protected void onPreExecute() { //UI
        super.onPreExecute();
        Log.d(TAG, "onPreExecute() "+Thread.currentThread().getName());
    }

    @Override
    protected Object doInBackground(Object[] params) { //Background
        Log.d(TAG, "doInBackground() started in "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        publishProgress();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "doInBackground() finished in "+Thread.currentThread().getName());


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d(TAG, "onPostExecute() " + Thread.currentThread().getName());

    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate() "+ Thread.currentThread().getName());
    }
}
