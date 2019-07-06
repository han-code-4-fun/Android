package com.example.android.todolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

//  (5) Make this class extend ViewModel
public class AddTaskViewModel extends ViewModel {
    public static final String TAG = AddTaskViewModel.class.getSimpleName();
    private LiveData<TaskEntry> taskEntry;

    //  (6) Add a task member variable for the TaskEntry object wrapped in a LiveData

    //  (8) Create a constructor where you call loadTaskById of the taskDao to initialize the tasks variable
    // Note: The constructor should receive the database and the taskId

    public AddTaskViewModel(AppDatabase mDB, int taskID) {
        Log.d(TAG, "AddTaskViewModel: created instance");
        taskEntry = mDB.taskDao().loadTaskById(taskID);
    }


    //  (7) Create a getter for the task variable

    public LiveData<TaskEntry> getTaskEntry() {
        return taskEntry;
    }


}
