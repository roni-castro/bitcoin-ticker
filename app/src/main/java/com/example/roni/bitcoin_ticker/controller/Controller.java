package com.example.roni.bitcoin_ticker.controller;

import com.example.roni.bitcoin_ticker.model.ApiRequestInterface;
import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.view.ViewInterface;

import java.util.List;

/**
 * Created by roni on 25/01/18.
 */

public class Controller {
    private ApiRequestInterface apiRequestInterface;
    private ViewInterface viewInterface;

    public Controller(ApiRequestInterface apiRequestInterface, ViewInterface viewInterface){
        this.apiRequestInterface = apiRequestInterface;
        this.viewInterface = viewInterface;

        getListOfItemsFromDataSource();
    }

    public void getListOfItemsFromDataSource(){
        apiRequestInterface.getCotationFromAPI();
    }
}
