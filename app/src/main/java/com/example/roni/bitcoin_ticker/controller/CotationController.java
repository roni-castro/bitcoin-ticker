package com.example.roni.bitcoin_ticker.controller;

import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.CotationService;
import com.example.roni.bitcoin_ticker.network.RetofitCotationService;
import com.example.roni.bitcoin_ticker.view.CotationViewInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by roni on 25/01/18.
 */

public class CotationController extends Controller {
    private CotationViewInterface cotationViewInterface;

    public CotationController(CompositeDisposable compositeDisposable,
                              CotationViewInterface cotationViewInterface){
        this.compositeDisposable = compositeDisposable;
        this.cotationViewInterface = cotationViewInterface;
    }

    public void getListOfItemsFromDataSource(){
        getCotationFromAPI();
    }

    /**
     * Get the bitcoin cotation of 1 year from Blockchain API
     */
    private void getCotationFromAPI() {
        CotationService cotationService = RetofitCotationService.getInstance().create(CotationService.class);

        Observable<Cotation> observable = cotationService.getCotations("1year");
        compositeDisposable.add(
                observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleSuccessCotationResponse,this::handleErrorCotation));
    }

    private void handleSuccessCotationResponse(Cotation cotation) {
        cotationViewInterface.onCotationRequestSuccess(cotation);
    }

    private void handleErrorCotation(Throwable error) {
        cotationViewInterface.showErrorMessage(error.getLocalizedMessage());
    }

}
