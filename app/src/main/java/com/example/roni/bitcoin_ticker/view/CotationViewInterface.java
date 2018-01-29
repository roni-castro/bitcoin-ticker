package com.example.roni.bitcoin_ticker.view;

import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.Ticker;

/**
 * Created by roni on 25/01/18.
 */

public interface CotationViewInterface {
    void onCotationRequestSuccess(Cotation cotation);
    void onTickerRequestSuccess(Ticker ticker);
    void onDBSaveSuccess();
    void showLoading(boolean show);
    void showErrorMessage(String message);
}
