package examlple.com.testviewmodel;

import android.util.Log;
import android.util.SparseBooleanArray;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CrossVM extends ViewModel {
    private MutableLiveData<Integer> countLiveData;

    private int tempCount=0;
    private SparseBooleanArray sparseBooleanArray;
    private MutableLiveData<SparseBooleanArray> temp;

    public CrossVM() {
        super();
        countLiveData = new MutableLiveData<>();
        Log.d("testtest", "CrossVM: create  MutableLiveData");
        temp = new MutableLiveData<>();
        sparseBooleanArray = new SparseBooleanArray();

    }

    public void changeLocalSparseBooleanArray(){
        sparseBooleanArray.put(2, true);
        temp.setValue(sparseBooleanArray);
    }


    public LiveData<Integer> getCountLiveData() {
        if(countLiveData == null){
            countLiveData = new MutableLiveData<>();
            countLiveData.setValue(tempCount);
        }

        return countLiveData;
    }

    public MutableLiveData<SparseBooleanArray> getSparseBooleanArray(){
        return temp;
    }

    public void countAddOne() {
        tempCount++;
        countLiveData.setValue(tempCount);
    }

    public void countLessOne(){
        tempCount--;
        countLiveData.setValue(tempCount);
    }
}
