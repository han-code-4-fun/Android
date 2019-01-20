package utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public final class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //use BufferedReader to read inputstream
    private static String readFromSteam(InputStream inputStream) throws IOException {
        StringBuilder outputString = new StringBuilder();
        if(inputStream != null)
        {
            InputStreamReader sReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bReader = new BufferedReader(sReader);
            String line = bReader.readLine();
            while(line != null)
            {
                outputString.append(line);
                line = bReader.readLine();
            }
        }else
        {
            Log.e(LOG_TAG," inputStream is null in readFromSteam");
        }

        return outputString.toString();
    }


    //output String which can be used to build JSONObject
    //by getting data from URL
    private static String getJSONFromURL(URL url) throws IOException
    {
        String JsonOutput = "";
        if(url == null){return JsonOutput; }

        HttpURLConnection urlConnect = null;
        InputStream inputStream = null;
        try
        {
            urlConnect = (HttpURLConnection)url.openConnection();
            urlConnect.setReadTimeout(10000);
            urlConnect.setConnectTimeout(15000);
            urlConnect.setRequestMethod("GET");
            urlConnect.connect();

            if(urlConnect.getResponseCode() == 200)
            {
                inputStream = urlConnect.getInputStream();
                JsonOutput=readFromSteam(inputStream);
            }else
            {
                Log.e(LOG_TAG,"internet connection error: "+
                        urlConnect.getResponseCode());
            }

        }catch (IOException e){
            Log.e(LOG_TAG,"error reading inputstream");
        }finally {
            if (urlConnect != null){urlConnect.disconnect();}
            if(inputStream != null){inputStream.close();}
        }
        return JsonOutput;

    }
}
