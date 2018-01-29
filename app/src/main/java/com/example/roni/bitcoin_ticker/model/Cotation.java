package com.example.roni.bitcoin_ticker.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by roni on 25/01/18.
 */

public class Cotation extends RealmObject {

    private String period;
    private RealmList<GraphPoint> values;

    public Cotation(){
    }

    public Cotation(String period, RealmList<GraphPoint> values){
        this.period = period;
        this.values = values;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public RealmList<GraphPoint> getValues() {
        return values;
    }

    public void setValues(RealmList<GraphPoint> values) {
        this.values = values;
    }
}
