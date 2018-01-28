package com.example.roni.bitcoin_ticker.controller;

import android.content.Context;

import com.example.roni.bitcoin_ticker.view.CotationViewInterface;

import io.realm.Realm;

/**
 * Created by roni on 28/01/18.
 */

public class DBCotationController {
    private CotationViewInterface cotationViewInterface;
    private Context context;
    private Realm realm;

    public DBCotationController(Context context, CotationViewInterface cotationViewInterface){
        this.context = context;
        this.cotationViewInterface = cotationViewInterface;
        setUpRealmDB();
    }

    private void setUpRealmDB(){
        Realm.init(context.getApplicationContext());
        realm = Realm.getDefaultInstance();

    }

    public void onDestroy() {
        realm.close();
    }

    public void onStop() {

    }

    public void saveCotationInDB(){

    }

    public void loadCotationFromDB(){

    }
}
