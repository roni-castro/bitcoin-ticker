package com.example.roni.bitcoin_ticker.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.roni.bitcoin_ticker.R;
import com.example.roni.bitcoin_ticker.controller.ApiController;
import com.example.roni.bitcoin_ticker.controller.DBCotationController;
import com.example.roni.bitcoin_ticker.model.Cotation;
import com.example.roni.bitcoin_ticker.model.GraphPoint;
import com.example.roni.bitcoin_ticker.model.Ticker;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivity extends BaseActivity implements CotationViewInterface{
    private LineChart graph;
    private TextView tickerUSD;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiController = new ApiController(new CompositeDisposable(), this);
        dbCotationController = new DBCotationController(this,this);
        graph = findViewById(R.id.graph);
        progressBar = findViewById(R.id.progress_bar);
        tickerUSD = findViewById(R.id.ticker_usd);
        setUpGraphView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiController.getListOfItemsFromDataSource();
        apiController.getLastBitcoinTickerFromAPI();
    }

    private void setUpGraphView(){
        // enable touch gestures
        graph.setTouchEnabled(true);
        // enable scaling and dragging
        graph.setDragEnabled(true);
        graph.setScaleEnabled(true);
        graph.setDrawGridBackground(false);
        // Hide the graph description
        Description description = new Description();
        description.setText("");
        graph.setDescription(description);
    }

    @Override
    public void onCotationRequestSuccess(Cotation cotation) {
        Log.v("ApiController", cotation.getPeriod());
        setUpGraphDataToBeShown(cotation.getPoints());
        dbCotationController.saveCotationInDB();
    }

    @Override
    public void onTickerRequestSuccess(Ticker ticker) {

        Animation a = AnimationUtils.loadAnimation(this, R.anim.animation_ticker);
        a.reset();
        tickerUSD.clearAnimation();
        tickerUSD.startAnimation(a);

        //show introduction and logo for Smart Shocks
        a.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationEnd(Animation fade1) {
                tickerUSD.setText(getResources().getString(R.string.ticker_usd_value, ticker.getLast()));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationStart(Animation animation) {}
        });
    }

    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show? View.VISIBLE: View.GONE);
    }

    /**
     * Create a Line graph with the array of graph points and update the view
     * @param cotationXYPoints holds the x and y values, where x is a unix timestamp
     *                         and y is the price in dollars
     */
    private void setUpGraphDataToBeShown(List<GraphPoint> cotationXYPoints){
        // Create an array of entries with of the x and y values
        ArrayList<Entry> points = new ArrayList<>();
        for(GraphPoint graphPoint: cotationXYPoints ){
            points.add(new Entry((float)graphPoint.getX(), (float)graphPoint.getY()));
        }
        // Create a line data set and set it to the graph
        LineDataSet lineDataSet = new LineDataSet(points, getString(R.string.bitcoin_price));
        lineDataSet.setLineWidth(1f);
        lineDataSet.setValueTextSize(10f);
        lineDataSet.setValueTextColor(Color.BLUE);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        graph.setData(lineData);

        // Set up the x axis of the graph to show the date value (max 5 values)
        XAxis xAxis = graph.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(5,true);
        graph.invalidate();
    }

    public String convertUnixTimestampToStringFormattedDate(double timeStamp){
        long value = Double.valueOf(timeStamp).longValue();
        // convert seconds to milliseconds
        Date date = new Date(value*1000L);
        // the format of your date
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    /**
     * Show a string value in the x-axis instead of a float one
     */
    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return convertUnixTimestampToStringFormattedDate(value);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        showToastMessage(message);
    }
}
