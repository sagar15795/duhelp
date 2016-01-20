package com.example.sysadmin.myapplication;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DUService {
  @GET("/")
  Call<Detail> getDetail(@Query("from") String x);

}