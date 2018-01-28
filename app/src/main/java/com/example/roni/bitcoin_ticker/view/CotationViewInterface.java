package com.example.roni.bitcoin_ticker.view;

import com.example.roni.bitcoin_ticker.model.Cotation;

import java.util.List;

/**
 * Created by roni on 25/01/18.
 */

public interface CotationViewInterface {
    void onCotationRequestSuccess(Cotation cotation);
    void showErrorMessage(String message);
}
