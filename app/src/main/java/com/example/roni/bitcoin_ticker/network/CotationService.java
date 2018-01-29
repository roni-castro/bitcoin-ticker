package com.example.roni.bitcoin_ticker.model;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by roni on 25/01/18.
 */

public interface CotationService {
    // TODO BASE URL MUST BE A PRIVATE SERVER
    public static final String BASE_URL = "https://api.blockchain.info/";

    @GET("charts/market-price?format=json")
    Observable<Cotation> getCotations(@Query("timespan") String timeStamp);

    @GET("ticker")
    Observable<Map<String, Ticker>> getBitcoinTickerValues();

}
