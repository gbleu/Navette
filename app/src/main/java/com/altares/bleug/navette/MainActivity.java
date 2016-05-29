package com.altares.bleug.navette;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.altares.bleug.navette.com.altares.bleug.navette.services.MapsService;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.FROM;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            new UpdateTrafficModifier().execute();

            MainActivity.this.mHandler.postDelayed(m_Runnable, 5000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, 5000);
    }

    private class UpdateTrafficModifier extends AsyncTask<Object, Object, Integer> {

        @Override
        protected Integer doInBackground(Object[] params) {
            return MapsService.getInstance().getTrafficModifier(FROM);
        }

        @Override
        protected void onPostExecute(Integer trafficModifier) {
            TextView textView = (TextView) findViewById(R.id.trafficModifierTextView);
            if (textView != null) {
                if (trafficModifier != null) {
                    int trafficModifierInMinutes = trafficModifier / 60;
                    textView.setText(getString(R.string.traffic_modifier, trafficModifierInMinutes));
                    if (trafficModifierInMinutes < 2) {
                        textView.setTextColor(Color.GREEN);
                    } else if (trafficModifierInMinutes < 3) {
                        textView.setTextColor(Color.YELLOW);
                    } else {
                        textView.setTextColor(Color.RED);
                    }
                } else {
                    textView.setText("-");
                    textView.setTextColor(Color.GRAY);
                }
            }
        }

        @Override
        protected void onCancelled(Integer trafficModifier) {
            TextView textView = (TextView) findViewById(R.id.trafficModifierTextView);
            if (textView != null) {
                textView.setText("-");
                textView.setTextColor(Color.DKGRAY);
            }
        }

    }
}
