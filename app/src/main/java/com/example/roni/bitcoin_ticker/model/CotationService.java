package com.example.roni.bitcoin_ticker.model;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by roni on 25/01/18.
 */

public interface CotationService {
    public static final String BASE_URL = "https://api.blockchain.info/charts/";

    @GET("market-price?timespan=5years&rollingAverage=24hours")
    Observable<Cotation> getCotations(@Query("format") String format);

}
