package popularmovies.examlple.com.openair.Services;

import android.content.Context;

import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class TestFirebaseJobUtil {
    private static final String FIREBASE_JOB_TAG = "test_firebase";

    synchronized public static void scheduleFirebaseJob(@NonNull final Context context){
        Driver driver = new GooglePlayDriver(context);

        FirebaseJobDispatcher dispatcher =new FirebaseJobDispatcher(driver);

        Job toastJob =dispatcher.newJobBuilder()
                .setService(TestFirebaseJobService.class)
                .setTag(FIREBASE_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0,0))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(toastJob);


    }
}
