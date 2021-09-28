package app.note.ui.start.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.note.ui.start.repository.AuthAppRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(
    private val authAppRepository: AuthAppRepository
) : ViewModel() {

    private var userLiveData: MutableLiveData<FirebaseUser?>? = null

    init {
        userLiveData = authAppRepository.getUserLiveData()
    }

    fun login(email: String?, password: String?) {
        authAppRepository.login(email, password)
    }

    fun register(email: String?, password: String?, firstName: String?, lastName: String?) {
        authAppRepository.register(email, password, firstName, lastName)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?>? {
        return userLiveData
    }

    fun logOut() {
        authAppRepository.logOut()
    }
}