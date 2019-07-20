package examlple.com.testjodatime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Weeks;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JodaTimeAndroid.init(this);

        textView = findViewById(R.id.text_view_display);

        Button btnWeek = findViewById(R.id.button_week);

        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String all = DateTimeFormat.forPattern("YYMMdd").print(LocalDate.now());
//                int dayofweek = LocalDate.now().getDayOfWeek();
//                int dayofmonth = LocalDate.now().getDayOfMonth();
//                Log.d("testt", "day of week + day of month is "+dayofweek+"  "+dayofmonth);
//                textView.setText("day of week + day of month is "+dayofweek+"  "+dayofmonth+ "all -> "+all);

             /*   int daysPastOFWeek = LocalDate.now().minusWeeks(2).getDayOfWeek()-1;
                Log.d("test111", "onClick: days past of a week -> "+ daysPastOFWeek );

//                int date = Integer.parseInt(DateTimeFormat.forPattern("YYYYMMdd").print(LocalDate.now().minusWeeks(2)))-daysPastOFWeek;
                int date = Integer.parseInt(DateTimeFormat.forPattern("YYYYMMdd").print(LocalDate.now().minusWeeks(2).dayOfWeek().withMinimumValue()));

                Log.d("test111", "onClick: 2 weeks (week starting) from now is -> "+ date );

                int lastDayOfWeek = Integer.parseInt(
                        DateTimeFormat.forPattern("YYYYMMdd").print(
                                LocalDate.now().minusWeeks(2).dayOfWeek().withMaximumValue()
                        )
                );
                Log.d("test111", "lastDayOfWeek is -> "+ lastDayOfWeek );*/


//                int date = 20190606;
//                LocalDate past = LocalDate.parse("" + date, DateTimeFormat.forPattern("YYYYMMdd"));
//                Log.d("test111", "past is -> " + past.toString());
//                Log.d("test111", "past forPattern(\"YYYYMMM\") -> " + DateTimeFormat.forPattern("YYYY/MMM").print(past));



//
//                int date2 = Integer.parseInt(DateTimeFormat.forPattern("YYYYMMdd").print(past.minusMonths(1)));
//                Log.d("test111", "date2 is -> " + date2);
//
//                Weeks weeks = Weeks.weeksBetween(past, LocalDate.now());
//
//                Log.d("test111", "weeks.toString() -> " + weeks.getWeeks());

//
//
//                int weeks = Integer.parseInt(DateTimeFormat.forPattern("YYYYMM").print(LocalDate.now().minusWeeks(3)));
//                Log.d("test111", "LocalDate 3 weeks ago () -> " + weeks);
//
//                weeks = weeks/100;
//                Log.d("test111", "LocalDate after /100 () -> " + weeks);


                Log.d("testt", "onClick: "+LocalDate.now().minusDays(0));


            }
        });

        final Button btnMonth = findViewById(R.id.button_month);
        btnMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = LocalDate.now().getYear();
//                int month = LocalDate.now().minusMonths(6).getMonthOfYear();
//                Log.d("testt", "6 year+month is "+year+" + "+month);
//                month = LocalDate.now().minusMonths(8).getMonthOfYear();
//                Log.d("testt", "8 year+month is "+year+" + "+month);
//
//                textView.setText("year+month is "+year+" + "+month);
//
//
//                String pastWeek = DateTimeFormat.forPattern("YYMMdd").print(LocalDate.now().minusWeeks(5));
//                String pastWeek1 = DateTimeFormat.forPattern("YYYYMMdd").print(LocalDate.now().minusWeeks(5));
//
//
//                Log.d("test111", "5 weeks ago is "+pastWeek1);


            }
        });


        Button btnPeriod = findViewById(R.id.button_period);

        btnPeriod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int dayofweek = LocalDate.now().getWeekOfWeekyear();
//                int dayofmonth = LocalDate.now().getWeekyear();
//                Log.d("testt", "getWeekOfWeekyear + getWeekyear is "+dayofweek+"  "+dayofmonth);
//
//                textView.setText("getWeekOfWeekyear + getWeekyear is "+dayofweek+"  "+dayofmonth);
            }
        });

        int daysPastCurrentWeek = LocalDate.now().getDayOfWeek();

        int year = (LocalDate.now().getYear()) - 2000;
        int month = LocalDate.now().getMonthOfYear();
        int dayBeginOfWeek = (LocalDate.now().getDayOfMonth()) - daysPastCurrentWeek + 1;

        int startingDate = year * 10000 + month * 100 + dayBeginOfWeek;
        Log.d("test_flow13", "setRevenueDateCurrentWeek: " + startingDate);

        int year1 = (LocalDate.now().getYear()) - 2000;
        int month1 = LocalDate.now().getMonthOfYear();

        int startingDate1 = year1 * 10000 + month1 * 100 + 1;
        Log.d("test_flow13", "setRevenueDateCurrentMonth: " + startingDate1);


        DateTimeFormat.forPattern("YYMMdd").print(LocalDate.now());
        int dayThreeWeekago = Integer.parseInt(DateTimeFormat.forPattern("YYMMdd").print(LocalDate.now().minusWeeks(3)).toString());
        Log.d("test_flow13", "onCreate:   dayThreeWeekago " + dayThreeWeekago);
        Log.d("test_flow13", "onCreate:   LocalDate.now().minusMonths(6) " + LocalDate.now().minusMonths(6).toString());

        int dayOfWeekHistory = LocalDate.parse(LocalDate.now().minusWeeks(3).toString()).getDayOfWeek();
        Log.d("test_flow13", "onCreate:   dayOfWeekHistory " + dayOfWeekHistory);
        int startDayOf3HistoryWeek = dayThreeWeekago - dayOfWeekHistory + 1;
        Log.d("test_flow13", "onCreate: startDayOf3HistoryWeek is " + startDayOf3HistoryWeek);

        /*
            onCreate:   dayThreeWeekago 190620
            onCreate:   LocalDate.now().minusMonths(6) 2019-01-11
            onCreate:   dayOfWeekHistory 4
            onCreate: startDayOf3HistoryWeek is 190617*/

        Log.d("test_flow14", "LocalDate.now().minusMonths(8).toString(): " + LocalDate.now().minusMonths(8).toString());


        int inputDateNum = Integer.parseInt(DateTimeFormat.forPattern("YYMMdd").print(LocalDate.now().minusMonths(1)));

        Log.d("test_flow14", "onCreate: inputDateNum before cutting days " + inputDateNum);
        int startDay = inputDateNum / 100 * 100 + 1;

        Log.d("test_flow14", "onCreate: startDay " + startDay);


        Log.d("test_flow14", "LocalDate.now().plusMonths(1)  " + LocalDate.now().plusMonths(1).toString());


        getStartingEndDateOfAMonth(LocalDate.now().minusMonths(5).plusYears(1));


        Button btnOpen = findViewById(R.id.button_open_another);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EditTextActivity.class));
            }
        });

    }

    public static int[] getStartingEndDateOfAMonth(LocalDate inputDate) {
        int[] output = new int[2];

        int inputDateNum = Integer.parseInt(fromJodaTimeLocalDateToAppDateFormat(inputDate.withDayOfMonth(1)));
        int endofDateNum = Integer.parseInt(fromJodaTimeLocalDateToAppDateFormat(inputDate.dayOfMonth().withMaximumValue()));


        LocalDate input = LocalDate.now().minusMonths(2);

//
//
//        Log.d("test_flow14", "LocalDate.now().withDayOfMonth(1).toString()  "+ inputDateNum);
//
//
//        Log.d("test_flow14", "LocalDate.now().dayOfMonth().withMaximumValue()  "+ endofDateNum);


        return output;
    }

    public static String fromJodaTimeLocalDateToAppDateFormat(LocalDate inputDate) {
        return DateTimeFormat.forPattern("YYMMdd").print(inputDate);
    }
}
