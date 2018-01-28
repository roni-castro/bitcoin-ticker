package com.example.roni.bitcoin_ticker.model;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by roni on 28/01/18.
 */

public class Ticker {
        private String symbol;
        private double last;

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public double getLast() {
            return last;
        }

        public void setLast(double last) {
            this.last = last;
        }

}
