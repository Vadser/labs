package com.example.vadimserebrennikov.labs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button1);
        final TextView txtView=(TextView)findViewById(R.id.textview1);
        final TextView txtView1=(TextView)findViewById(R.id.textview3);
        final String IMEI = xximei();
        final String versionName = BuildConfig.VERSION_NAME;
        txtView.setText(IMEI);
        txtView1.setText(versionName);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txtView1.setText(IMEI);
                txtView.setText(versionName);
            }
        });
    }

    public String xximei()
    {
        String imeinum="";



        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // We do not have this permission. Let's ask the user

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},0);

            return "imei error";

        }else {


            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            imeinum = tm.getDeviceId();

            return imeinum;


        }

    }
}
