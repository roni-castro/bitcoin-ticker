package com.example.roni.bitcoin_ticker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by roni on 25/01/18.
 */

public class Cotation extends RealmObject {

    private String period;
    @SerializedName("values") private RealmList<GraphPoint> points;

    public Cotation(){
    }

    public Cotation(String period, RealmList<GraphPoint> points){
        this.period = period;
        this.points = points;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public RealmList<GraphPoint> getPoints() {
        return points;
    }

    public void setPoints(RealmList<GraphPoint> points) {
        this.points = points;
    }
}
