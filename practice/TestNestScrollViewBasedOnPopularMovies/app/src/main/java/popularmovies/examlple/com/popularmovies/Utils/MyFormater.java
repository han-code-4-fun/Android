package popularmovies.examlple.com.popularmovies.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyFormater {

    //method used to covert data to required format

    static private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static private SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM/dd/yyyy");

    public static String reformatDate(String inputDate)
    {
        String output = null;
        try {
            String cutString = inputDate.substring(0,10);
            Date temp = inputDateFormat.parse(cutString);
            output = outputDateFormat.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String createRatingText(String inputRating)
    {
        String output = "<b>Rating: </b>"+ inputRating;
        return output;
    }

    public static String createReleaseDate(String inputDate)
    {
        String temp = reformatDate(inputDate);
        String output = "<b>Release date: </b>"+ temp;
        return output;
    }

    public static String createOverviewText(String inputOverview)
    {
        String output = "<b>Overview: </b>"+inputOverview;
        return output;
    }


}
