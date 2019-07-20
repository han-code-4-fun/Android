package examlple.com.kotlinviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class UserRepo{
    fun searchUserWithName(name  : String) : LiveData<List<User>>{
      //  .... logic for search user

    }
}
class UserViewModel : ViewModel() {
    private val query = MutableLiveData<String>()
    private val userRepo = UserRepo()
    val userNameResult: LiveData<List<User>> = Transformations.switchMap(
        query,
        ::temp
    )
    private fun temp(name: String) = userRepo.searchUserWithName(name)
    fun searchUserByName(name: String) = apply { query.value = name }
}