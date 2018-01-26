package com.example.roni.bitcoin_ticker.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by roni on 25/01/18.
 */

public interface CotationService {
    public static final String BASE_URL = "https://api.blockchain.info/charts/";

    @GET("market-price")
    Call<Cotation> getCotations(@Query("timeSpan") String timeStamp,
                      @Query("format") String format);

}
