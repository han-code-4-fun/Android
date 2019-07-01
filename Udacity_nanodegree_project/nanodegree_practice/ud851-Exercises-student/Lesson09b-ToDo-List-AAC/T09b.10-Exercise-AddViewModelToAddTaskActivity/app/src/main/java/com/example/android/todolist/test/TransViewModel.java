package com.example.android.todolist.test;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TransViewModel extends AndroidViewModel {

        private static final String TAG = TransViewModel.class.getSimpleName();

        private LiveData<List<TransactionEntry>> transactionEntryList;

        public TransViewModel(@NonNull Application application) {
            super(application);

            TransactionDB database =TransactionDB.getInstance(this.getApplication());

            //the overview fragment only diaplay these (new) transactions that needed to be tagged by user
            transactionEntryList = database.transactionDAO().loadTransactionByLedger("n/a");
        }

        public LiveData<List<TransactionEntry>> getUntaggedTransactions(){
            return transactionEntryList;
        }

}
