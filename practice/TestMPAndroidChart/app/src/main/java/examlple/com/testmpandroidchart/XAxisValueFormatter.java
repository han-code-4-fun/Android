package examlple.com.testmpandroidchart;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class XAxisValueFormatter extends IndexAxisValueFormatter {

    private String[] values;

    public XAxisValueFormatter(String[] values) {
        this.values = values;
    }

    @Override
    public String getFormattedValue(float value) {
        // "value" represents the position of the label on the axis (x or y)
        Log.d("test_flow", "getFormattedValue: calleddddddd");
        return this.values[(int) value];
    }


    @Override
    public String[] getValues() {
        return values;
    }
}
