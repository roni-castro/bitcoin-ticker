package com.example.roni.bitcoin_ticker.controller;

import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.CotationService;
import com.example.roni.bitcoin_ticker.model.Ticker;
import com.example.roni.bitcoin_ticker.network.RetofitCotationService;
import com.example.roni.bitcoin_ticker.view.CotationViewInterface;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by roni on 25/01/18.
 */

public class ApiController {
    private CompositeDisposable compositeDisposable;
    private CotationViewInterface cotationViewInterface;

    public ApiController(CompositeDisposable compositeDisposable,
                         CotationViewInterface cotationViewInterface){
        this.compositeDisposable = compositeDisposable;
        this.cotationViewInterface = cotationViewInterface;
    }

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
                .doOnNext(__ -> cotationViewInterface.showLoading(true))
                .doOnEach(__ -> cotationViewInterface.showLoading(false))
                .subscribe(this::handleSuccessCotationResponse,this::handleError));
    }

    /**
     * Get the bitcoin ticker price from Blockchain API every 5 seconds
     */
    public void getLastBitcoinTickerFromAPI() {
        CotationService cotationService = RetofitCotationService.getInstance().create(CotationService.class);
        Observable<Map<String, Ticker>> observable = cotationService.getBitcoinTickerValues();

        compositeDisposable.add(Observable.interval(5, TimeUnit.SECONDS)
                .flatMap(n -> cotationService.getBitcoinTickerValues())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleSuccessTickerResponse,this::handleError));
    }

    private void handleSuccessCotationResponse(Cotation cotation) {
        cotationViewInterface.onCotationRequestSuccess(cotation);
    }

    private void handleSuccessTickerResponse(Map<String, Ticker> ticker) {
        cotationViewInterface.onTickerRequestSuccess(ticker.get("USD"));
    }

    private void handleError(Throwable error) {
        cotationViewInterface.showErrorMessage(error.getLocalizedMessage());
    }



}
