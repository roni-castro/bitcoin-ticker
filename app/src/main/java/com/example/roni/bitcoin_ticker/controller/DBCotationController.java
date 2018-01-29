package com.example.roni.bitcoin_ticker.controller;

import android.content.Context;

import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.view.CotationViewInterface;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * Created by roni on 28/01/18.
 */

public class DBCotationController {
    private CotationViewInterface cotationViewInterface;
    private Context context;
    private Realm realm;
    private RealmAsyncTask transaction;

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
        if (transaction != null && !transaction.isCancelled()) {
            transaction.cancel();
        }
    }

    public void saveCotationInDB(Cotation cotation){
        transaction = realm.executeTransactionAsync((Realm bgRealm) -> {
            bgRealm.deleteAll();
            bgRealm.copyToRealm(cotation);
        }, () -> cotationViewInterface.onDBSaveSuccess(),
                error -> {
                    // Transaction failed and was automatically canceled.
                    cotationViewInterface.showErrorMessage(error.getLocalizedMessage());
                }
        );
    }

    public RealmResults<Cotation> loadCotationFromDB(){
        RealmResults<Cotation> cotations = realm.where(Cotation.class).findAll();
        return cotations;
    }
}
