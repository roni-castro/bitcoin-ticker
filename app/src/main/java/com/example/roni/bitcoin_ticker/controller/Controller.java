package com.example.roni.bitcoin_ticker.controller;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by roni on 28/01/18.
 */

public class Controller {
    protected CompositeDisposable compositeDisposable;

    public void onDestroy() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void onStop() {
        if(compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

}
