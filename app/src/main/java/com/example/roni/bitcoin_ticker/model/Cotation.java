package com.example.roni.bitcoin_ticker.model;

import android.graphics.Point;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by roni on 25/01/18.
 */

public class Cotation {
    public class GraphPoint {
        double x, y;

        public double getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    private String period;
    @SerializedName("values") private ArrayList<GraphPoint> points;

    public Cotation(String period, ArrayList<GraphPoint> points){
        this.period = period;
        this.points = points;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public ArrayList<GraphPoint> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<GraphPoint> points) {
        this.points = points;
    }
}
