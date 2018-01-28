package com.example.roni.bitcoin_ticker.network;

import com.example.roni.bitcoin_ticker.model.CotationService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by roni on 28/01/18.
 */

public class RetofitCotationService {

    private static Retrofit retrofit;

    private RetofitCotationService(){

    }

    public static synchronized Retrofit getInstance() {
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(CotationService.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
