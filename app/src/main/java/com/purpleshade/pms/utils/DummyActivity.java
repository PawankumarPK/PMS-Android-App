package com.purpleshade.pms.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.purpleshade.pms.R;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);

        checkConnection();
    }

    private void checkConnection(){
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork){
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this, "Wifi enabled", Toast.LENGTH_SHORT).show();
            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this, "Data Network Enabled", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
        }
    }
}