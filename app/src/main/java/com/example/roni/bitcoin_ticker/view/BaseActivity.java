package com.example.roni.bitcoin_ticker.view;

import android.content.Context;
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
        dbCotationController.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        apiController.onStop();
        dbCotationController.onStop();
    }

    protected void showToastMessage(Context context, String message){
        if(!this.isFinishing()) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
