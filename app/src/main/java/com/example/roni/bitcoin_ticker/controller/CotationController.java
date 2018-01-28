package com.example.roni.bitcoin_ticker.controller;

import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.CotationService;
import com.example.roni.bitcoin_ticker.view.CotationViewInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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

    private void getCotationFromAPI() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CotationService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        CotationService cotationService = retrofit.create(CotationService.class);

        Observable<Cotation> observable = cotationService.getCotations("json");
        compositeDisposable.add(
                observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(Cotation cotation) {
        cotationViewInterface.onCotationRequestSuccess(cotation);
    }

    private void handleError(Throwable error) {
        cotationViewInterface.showErrorMessage(error.getLocalizedMessage());
    }

}
