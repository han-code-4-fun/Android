package com.example.android.todolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

//  (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory
public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    //  (2) Add two member variables. One for the database and one for the taskId
    private final AppDatabase appDatabase;
    private final int taskID;

    public AddTaskViewModelFactory(AppDatabase appDatabase, int taskID) {
        this.appDatabase = appDatabase;
        this.taskID = taskID;
    }

    //  (3) Initialize the member variables in the constructor with the parameters received

    //  (4) Uncomment the following method
    // Note: This can be reused with minor modifications
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(appDatabase, taskID);
    }
}
