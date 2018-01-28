package com.example.roni.bitcoin_ticker.view;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.roni.bitcoin_ticker.controller.Controller;

/**
 * Created by roni on 28/01/18.
 */

public class BaseActivity extends AppCompatActivity {
    protected Controller controller;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        controller.onStop();
    }

    protected void showToastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
