package app.note.ui.note.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.note.ui.note.repository.NoteAppRepository
import com.google.firebase.auth.FirebaseUser

class NoteViewModel(
    private val noteAppRepository: NoteAppRepository
) : ViewModel() {

    private var userLiveData: MutableLiveData<FirebaseUser?>? = null

    init {
//        userLiveData = noteAppRepository.getUserLiveData()
    }

    fun addNote(context: Context, uid: String, note: String, image: Uri?) {
        noteAppRepository.addNote(context, uid, note, image)
    }

    fun fetchNote(context: Context, uid: String) {
        noteAppRepository.fetchNote(context, uid)
    }
}