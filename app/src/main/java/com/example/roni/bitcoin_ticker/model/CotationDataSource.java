package com.example.roni.bitcoin_ticker.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by roni on 25/01/18.
 */

public class CotationDataSource implements ApiRequestInterface {

    @Override
    public void getCotationFromAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CotationService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CotationService cotationService = retrofit.create(CotationService.class);
        Call<Cotation> requestCotation = cotationService.getCotations("5weeks", "json");
        requestCotation.enqueue(new Callback<Cotation>() {
            @Override
            public void onResponse(Call<Cotation> call, Response<Cotation> response) {
                if(!response.isSuccessful()){
                    Log.e("Error", String.valueOf(response.code()));
                } else{
                    response.body();
                }
            }

            @Override
            public void onFailure(Call<Cotation> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}
