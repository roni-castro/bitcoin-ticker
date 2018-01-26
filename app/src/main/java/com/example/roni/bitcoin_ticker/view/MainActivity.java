package com.example.roni.bitcoin_ticker.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.roni.bitcoin_ticker.R;
import com.example.roni.bitcoin_ticker.controller.Controller;
import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.CotationDataSource;

public class MainActivity extends AppCompatActivity implements ViewInterface {
    private Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new Controller(new CotationDataSource(), this);
    }

    @Override
    public void setUpBtcPrice(Cotation cotation) {

    }
}
