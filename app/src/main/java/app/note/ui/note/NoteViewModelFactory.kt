package app.note.ui.note

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.note.ui.note.repository.NoteAppRepository
import app.note.ui.note.viewmodel.NoteViewModel

class NoteViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(NoteAppRepository(activity)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}