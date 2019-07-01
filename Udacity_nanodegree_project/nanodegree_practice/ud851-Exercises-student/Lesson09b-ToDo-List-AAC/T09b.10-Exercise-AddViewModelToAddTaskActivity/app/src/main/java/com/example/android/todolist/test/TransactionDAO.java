package com.example.android.todolist.test;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TransactionDAO {

    @Query("SELECT * FROM transactions ORDER BY time DESC, id DESC")
    LiveData<List<TransactionEntry>> loadAllTransactions();

    @Insert
    void insertTransaction(TransactionEntry transactionEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTransaction(TransactionEntry transactionEntry);

    @Delete
    void deleteTask(TransactionEntry transactionEntry);

    @Query("SELECT * FROM transactions WHERE id = :id")
    LiveData<TransactionEntry> loadTransactionByID(int id);

    @Query("SELECT * FROM transactions WHERE ledger = :leger ORDER BY time DESC, id DESC")
    LiveData<List<TransactionEntry>> loadTransactionByLedger(String leger);
    /*
        select account_id, total,  standard_qty from orders

        order by account_id desc, total desc limit 100

     */
}

