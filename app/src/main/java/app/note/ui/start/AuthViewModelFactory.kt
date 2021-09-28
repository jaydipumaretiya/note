package app.note.ui.start

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.note.ui.start.repository.AuthAppRepository
import app.note.ui.start.viewmodel.AuthViewModel

class AuthViewModelFactory (private val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(AuthAppRepository(activity)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}