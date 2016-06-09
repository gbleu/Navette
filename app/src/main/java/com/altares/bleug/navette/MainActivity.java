package com.altares.bleug.navette;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.altares.bleug.navette.com.altares.bleug.navette.models.Passage;
import com.altares.bleug.navette.com.altares.bleug.navette.models.Way;
import com.altares.bleug.navette.com.altares.bleug.navette.services.MapsService;
import com.altares.bleug.navette.com.altares.bleug.navette.services.Passages;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.TO;

public class MainActivity extends AppCompatActivity {
    private static final long REFRESH_INTERVAL = 60000;
    private static final Way WAY = TO;

    private Handler mHandler;

    private final Runnable m_Runnable = new Runnable() {
        public void run() {
            new UpdateNextPassage().execute(WAY);
            new UpdateTrafficModifier().execute(WAY);

            MainActivity.this.mHandler.postDelayed(m_Runnable, REFRESH_INTERVAL);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Passages.getInstance().init();

        new UpdateNextPassage().execute(WAY);
        new UpdateTrafficModifier().execute(WAY);

        setContentView(R.layout.activity_main);

        this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable, REFRESH_INTERVAL);
    }

    private class UpdateNextPassage extends AsyncTask<Object, Object, Passage> {

        @Override
        protected Passage doInBackground(Object[] params) {
            return Passages.getInstance().next((Way) params[0]);
        }

        @Override
        protected void onPostExecute(Passage next) {
            TextView textView = (TextView) findViewById(R.id.nextPassageTextView);
            if (textView != null) {
                if (next != null) {
                    textView.setText(next.timeToString());
                }
            }
        }
    }

    private class UpdateTrafficModifier extends AsyncTask<Object, Object, Integer> {

        @Override
        protected Integer doInBackground(Object[] params) {
            return MapsService.getInstance().getTrafficModifier((Way) params[0]);
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
    }

}
