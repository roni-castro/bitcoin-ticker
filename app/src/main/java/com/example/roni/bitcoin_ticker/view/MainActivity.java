package com.example.roni.bitcoin_ticker.view;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.example.roni.bitcoin_ticker.R;
import com.example.roni.bitcoin_ticker.controller.CotationController;
import com.example.roni.bitcoin_ticker.model.Cotation;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity implements CotationViewInterface {
    private GraphView graph;
    int numHorizontalLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = new CotationController(new CompositeDisposable(), this);
        graph = findViewById(R.id.graph);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((CotationController)controller).getListOfItemsFromDataSource();
    }

    @Override
    public void onCotationRequestSuccess(Cotation cotation) {
        Log.v("Controller", cotation.getPeriod());

    }

    @Override
    public void showErrorMessage(String message) {
        showToastMessage(message);
    }
}
