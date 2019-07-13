package examlple.com.testmpandroidchart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    HorizontalBarChart mBarChart;
    BarDataSet barDataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = findViewById(R.id.id_horizontal_bar_chart);

        setSkillGraph();

        //Set bar entries and add necessary formatting
        setGraphData();

        populateDatainBarChart();

        //Add animation to the graph
        mBarChart.animateY(2000);

    }




    private void setSkillGraph(){


        mBarChart.setDrawBarShadow(false);

        Description description = new Description();
        description.setText("this is test description");
        //todo, important
        description.setYOffset(-6f);

        mBarChart.setDescription(description);
//        mBarChart.setDescription(null);


        //todo, need
        mBarChart.getLegend().setEnabled(false);


        mBarChart.setDrawValueAboveBar(false);
        //Display the axis on the left (contains the labels 1*, 2* and so on)

        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setDrawGridLines(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setEnabled(true);

        xAxis.setDrawAxisLine(false);

        xAxis.setLabelCount(5);

        String[] values = new String[]{"t1","t2","t3","t4","t5"};

        xAxis.setValueFormatter(new XAxisValueFormatter(values));

        YAxis yLeftAxis = mBarChart.getAxisLeft();
//Set the minimum and maximum bar lengths as per the values that they represent

        yLeftAxis.setAxisMaximum(1500f);

        yLeftAxis.setAxisMinimum(0f);

        yLeftAxis.setEnabled(false);




        YAxis yRight = mBarChart.getAxisRight();
        yRight.setDrawAxisLine(true);
        yRight.setDrawGridLines(false);
        yRight.setEnabled(false);

    }


    private void setGraphData() {
        BarEntry revenue = new BarEntry(0f,1250);
        revenue.setData("test1 ");
        BarEntry spend = new BarEntry(1f,700);
        spend.setData("test2");

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(revenue);
        entries.add(spend);
        entries.add(new BarEntry(2f, 65f));
        entries.add(new BarEntry(3f, 77f));
        entries.add(new BarEntry(4f, 93f));

        barDataSet = new BarDataSet(entries, "Bar data test");

        barDataSet.setColors(
                ContextCompat.getColor(mBarChart.getContext(), R.color.color1),
                ContextCompat.getColor(mBarChart.getContext(), R.color.color2),
                ContextCompat.getColor(mBarChart.getContext(), R.color.color3),
                ContextCompat.getColor(mBarChart.getContext(), R.color.color4),
                ContextCompat.getColor(mBarChart.getContext(), R.color.color5));
        //todo, important
        barDataSet.setBarShadowColor(Color.CYAN);

    }

    private void populateDatainBarChart() {

        //Set bar shadows
        mBarChart.setDrawBarShadow(true);
//        barDataSet.setBarShadowColor(R.color.color_barset_shadow_color);

        BarData barData = new BarData(barDataSet);


        //Set the bar width
        //Note : To increase the spacing between the bars set the value of barWidth to < 1f


        barData.setBarWidth(0.9f);

//        mBarChart.setGridBackgroundColor(R.color.color_barset_shadow_color);

//        mBarChart.setFitBars(true);
//        mBarChart.getXAxis().setDrawGridLines(false);
        //todo, important

//        mBarChart.getXAxis().setEnabled(false);


//        mBarChart.getAxisLeft().setEnabled(true);
//        mBarChart.getAxisLeft().setDrawGridLines(false);
        //todo, important
        mBarChart.getAxisRight().setEnabled(false);
//        mBarChart.getAxisRight().setDrawGridLines(false);

        mBarChart.setTouchEnabled(false);
        mBarChart.setDragEnabled(false);
        mBarChart.setScaleEnabled(false);
        mBarChart.setScaleXEnabled(false);
        mBarChart.setScaleYEnabled(false);
        mBarChart.setPinchZoom(false);
        mBarChart.setDoubleTapToZoomEnabled(false);

        //Finally set the data and refresh the graph
        mBarChart.setData(barData);
        mBarChart.invalidate();


    }
}
