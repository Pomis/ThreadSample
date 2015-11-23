package app.pomis.threadsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String TAG = "my_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new MyAsyncTask().execute();
        new MyTask().execute(this, "https://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.xchange+where+pair+=+%22USDRUB,EURRUB%22&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");

    }
    public void onCurrenciesLoaded(String result){
        ((TextView)findViewById(R.id.my_text_view)).setText(result);
    }

    private void testThreads() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, Thread.currentThread().getName());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, Thread.currentThread().getName());


            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
    }
}
