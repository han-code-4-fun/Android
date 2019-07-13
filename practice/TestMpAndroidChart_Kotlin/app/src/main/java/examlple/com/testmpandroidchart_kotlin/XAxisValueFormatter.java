package examlple.com.testmpandroidchart_kotlin;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

public class XAxisValueFormatter extends IndexAxisValueFormatter {

    private String[] values;

    public XAxisValueFormatter(String[] values) {
        this.values = values;
    }

//    @Override
//    public String getFormattedValue(float value, AxisBase axis) {
//        // "value" represents the position of the label on the axis (x or y)
//        return this.values[(int) value];
//    }

    @Override
    public String getFormattedValue(float value) {
        return this.values[(int) value];
    }
}