package com.example.sysadmin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    Detail mDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences prefLastDate = getSharedPreferences("JsonLastDatePREF", Context.MODE_PRIVATE);

        File file = new File(getCacheDir(), "image.json");
        if (!file.exists()) {
            try {
                Log.e("check", "file doesnot exits in cache");
                Resources res = getResources();
                InputStream inputStream = res.openRawResource(R.raw.du_detail);

                byte[] b = new byte[inputStream.available()];

                inputStream.read(b);

                Gson gson = new Gson();
                mDetail = gson.fromJson(new String(b), Detail.class);
                Log.e("aa", mDetail.toString() );
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("check", "file  exits in cache");
            File tempFile = new File(getCacheDir(), "image.json");
            String strLine = "";
            StringBuilder text = new StringBuilder();

            try {
                FileReader fReader = new FileReader(tempFile.getAbsoluteFile());
                BufferedReader bReader = new BufferedReader(fReader);
                while ((strLine = bReader.readLine()) != null) {
                    text.append(strLine + "\n");
                }
                Gson gson = new Gson();
                mDetail = gson.fromJson(new String(text), Detail.class);
                Log.e("aa", mDetail.toString() );
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        Log.e("xsgfhjsdg", (getData(new Detail())).getLast_update());
        RestClient restClient = new RestClient();
        Call<Detail> detailCall = restClient.getDUService().getDetail("du_details");
        detailCall.enqueue(new Callback<Detail>() {
            @Override
            public void onResponse(Response<Detail> response) {
                if (prefLastDate.getString("DuDetail", "0").equals(response.body().getLast_update())) {


                } else {
                    Log.e("check", "do something");
                    File file = new File(getCacheDir(), "image.json");
                    try {
                        if (!file.exists())
                            file.createNewFile();
                        FileWriter fw = null;
                        fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        Gson gson = new Gson();
                        bw.write(gson.toJson(response.body()));
                        bw.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("TAG", "Please Check You Internet Connection");
            }
        });
    }


}
