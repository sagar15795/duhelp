package com.example.sysadmin.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;

public class OneTimeMainActivity extends AppCompatActivity {
    Detail mDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time_main);

        final SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        final SharedPreferences prefLastDate = getSharedPreferences("JsonLastDatePREF", Context.MODE_PRIVATE);
        try {
            Resources res = getResources();
            InputStream inputStream = res.openRawResource(R.raw.du_detail);

            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            Gson gson = new Gson();
            mDetail=gson.fromJson(new String(b), Detail.class);
            SharedPreferences.Editor ed = prefLastDate.edit();
            ed.putString("DuDetail", mDetail.getLast_update());
            ed.commit();
            Log.e("mm",prefLastDate.getString("DuDetail","0")+ "onCreate: " );

        } catch (Exception e) {
            Log.e("on", "onCreate: Error: can't show help.");
        }


        if(pref.getBoolean("activity_executed", false)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.commit();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(OneTimeMainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 5000);

        }
    }

}
