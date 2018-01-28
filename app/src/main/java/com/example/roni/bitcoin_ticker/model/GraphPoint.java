package com.example.roni.bitcoin_ticker.model;

import io.realm.RealmObject;

/**
 * Created by roni on 28/01/18.
 */

public class GraphPoint extends RealmObject {
    private double x, y;

    public GraphPoint(){
    }

    public GraphPoint(double x, double y){
        this.x = x;
        this.y = y;
    }

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
