package com.example.roni.bitcoin_ticker.controller;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by roni on 28/01/18.
 */

public class BtcApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("btc_cotation.realm").build();
        Realm.setDefaultConfiguration(config);
    }
}
