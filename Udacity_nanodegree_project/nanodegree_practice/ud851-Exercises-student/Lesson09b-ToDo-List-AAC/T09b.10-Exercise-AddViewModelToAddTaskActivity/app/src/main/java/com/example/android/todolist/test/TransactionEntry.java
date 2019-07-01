package com.example.android.todolist.test;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "transactions")
public class TransactionEntry {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String ledger;


    private int time;

    private double amount;

    private String catogory;

    private String remark;



    @Ignore
    public TransactionEntry(String ledger, int time, double amount, String catogory, String remark) {
        this.ledger = ledger;
        this.time = time;
        this.amount = amount;
        this.catogory = catogory;
        this.remark = remark;
    }

    public TransactionEntry(int id, String ledger, int time, double amount, String catogory, String remark) {
        this.id = id;
        this.ledger = ledger;
        this.time = time;
        this.amount = amount;
        this.catogory = catogory;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLedger() {
        return ledger;
    }

    public void setLedger(String ledger) {
        this.ledger = ledger;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCatogory() {
        return catogory;
    }

    public void setCatogory(String catogory) {
        this.catogory = catogory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

