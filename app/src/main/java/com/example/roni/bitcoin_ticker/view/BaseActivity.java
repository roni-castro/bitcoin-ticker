package com.example.roni.bitcoin_ticker.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.roni.bitcoin_ticker.controller.ApiController;
import com.example.roni.bitcoin_ticker.controller.DBCotationController;

/**
 * Created by roni on 28/01/18.
 */

public class BaseActivity extends AppCompatActivity {
    protected ApiController apiController;
    protected DBCotationController dbCotationController;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        apiController.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        apiController.onStop();
    }

    protected void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
