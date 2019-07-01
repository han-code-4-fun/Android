package com.example.android.todolist.database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "transactions")
public class TransactionEntry {


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "ledger")
    private String mLedgerName;


    @ColumnInfo(name = "time")
    private int mTime;

    @ColumnInfo(name = "amount")
    private double mAmount;

    @ColumnInfo(name = "catogory")
    private String mCatogory;

    @ColumnInfo(name = "remark")
    private String mRemark;

    public TransactionEntry(int mID, String mLedgerName, int mTime, double mAmount, String mCatogory, String mRemark) {
        this.id = mID;
        this.mLedgerName = mLedgerName;
        this.mTime = mTime;
        this.mAmount = mAmount;
        this.mCatogory = mCatogory;
        this.mRemark = mRemark;
    }

    @Ignore
    public TransactionEntry(int mTime, double mAmount, String mCatogory, String mRemark, String mLedger) {
        this.mTime = mTime;
        this.mAmount = mAmount;
        this.mCatogory = mCatogory;
        this.mRemark = mRemark;
        this.mLedgerName = mLedger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmLedgerName() {
        return mLedgerName;
    }

    public void setmLedgerName(String mLedgerName) {
        this.mLedgerName = mLedgerName;
    }

    public int getmTime() {
        return mTime;
    }

    public void setmTime(int mTime) {
        this.mTime = mTime;
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    public String getmCatogory() {
        return mCatogory;
    }

    public void setmCatogory(String mCatogory) {
        this.mCatogory = mCatogory;
    }

    public String getmRemark() {
        return mRemark;
    }

    public void setmRemark(String mRemark) {
        this.mRemark = mRemark;
    }

//    }
//        this.tagged = tagged;
//    public void setTagged(int tagged) {
//
//    }
//        return tagged;
//    public int getTagged() {
//    }
//        this.mRecordTime = mRecordTime;
//    public void setmRecordTime(long mRecordTime) {
//
//    }
//        return mRecordTime;
//    public long getmRecordTime() {
//
    /*    public TransactionEntry(int id, int mTime, double mAmount, String mCatogory, String mRemark, String mLedger) {
            this.id = id;
            this.mTime = mTime;
            this.mAmount = mAmount;
            this.mCatogory = mCatogory;
            this.mRemark = mRemark;
            this.mLedgerName = mLedger;
    //        this.mRecordTime = currentTime;
        }*/
//    private int tagged;
//    @ColumnInfo(name = "is_tagged")
//     */
//     *   untagged transactions marked as 0, tagged will be 1
//     *   tracks whether a transaction is tagged or not
//    /*
    /*
        mLedgerName == "n/a" means it's an untagged transaction
        that need to be tagged by user
    */
//    /*
//       this app will use only one table, so a transaction will
//       not have a timeStamp until user "tag" it. And it will
//       be used for sorting purpose (instead of the 'id' in table)
//       as this best fits user's memory
//
//       e.g. a user may received a msg from bank about a transaction,
//            but the user may not open the app to tag it,
//     */
//    @ColumnInfo(name = "timestamp_in_table")
//    private long mRecordTime;

}

