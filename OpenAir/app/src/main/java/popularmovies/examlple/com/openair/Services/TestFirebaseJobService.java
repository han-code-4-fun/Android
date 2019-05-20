package popularmovies.examlple.com.openair.Services;

import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import java.util.concurrent.TimeUnit;

public class TestFirebaseJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters job) {


        Log.e("TEST", "->>>>>>>");

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
