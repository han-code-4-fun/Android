package examlple.com.testviewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TestVMFactory extends ViewModelProvider.NewInstanceFactory{
    private final String s;

    public TestVMFactory(String ss) {
        s = ss;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass  ) {
        return (T) new CrossVM(s);
    }
}
