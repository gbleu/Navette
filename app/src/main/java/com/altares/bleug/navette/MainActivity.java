package com.altares.bleug.navette;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.altares.bleug.navette.com.altares.bleug.navette.services.MapsService;

import static com.altares.bleug.navette.com.altares.bleug.navette.models.Way.FROM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new AsyncTask<Object, Object, Integer>() {
            @Override
            protected Integer doInBackground(Object[] params) {
                return MapsService.getInstance().getTrafficModifier(FROM);
            }

            @Override
            protected void onPostExecute(Integer integer) {
                Log.d("MainActivity", integer != null ? integer.toString() : "NULL");
            }
        }.execute();

        setContentView(R.layout.activity_main);
    }
}
