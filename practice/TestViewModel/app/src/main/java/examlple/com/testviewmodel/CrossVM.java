package examlple.com.testviewmodel;

import android.util.Log;
import android.util.SparseBooleanArray;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CrossVM extends ViewModel {
    private MutableLiveData<Integer> countLiveData= new MutableLiveData<>();

    private int tempCount=0;
    private SparseBooleanArray sparseBooleanArray= new SparseBooleanArray();
    private MutableLiveData<SparseBooleanArray> temp= new MutableLiveData<>();

    public CrossVM() {
        Log.d("testtest", "CrossVM: create  MutableLiveData");

    }

    public CrossVM(String in){
        Log.d("testtest", "CrossVM: constructor "+ in);
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
