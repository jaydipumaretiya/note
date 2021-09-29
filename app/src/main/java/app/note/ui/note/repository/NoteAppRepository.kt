package app.note.ui.note.repository

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import app.note.model.Note
import app.note.util.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*

class NoteAppRepository(activity: Activity) {

    private var activity: Activity? = null
    private var database: DatabaseReference
    private var storage: FirebaseStorage
    private var storageReference: StorageReference
    private var noteLiveData: MutableLiveData<Note?>? = null

    init {
        this.activity = activity
        database = Firebase.database.reference
        noteLiveData = MutableLiveData()

        storage = FirebaseStorage.getInstance()
        storageReference = storage.reference
    }

    fun addNote(context: Context, uid: String, note: String, image: Uri?) {
        var imageName = ""
        if (image != null) {
            imageName = uid + System.currentTimeMillis()
            uploadImage(context, imageName, image)
        }

        database
            .child(Constants.DATABASE_USER)
            .child(uid)
            .child(Constants.DATABASE_NOTE)
            .child(UUID.randomUUID().toString())
            .setValue(Note(note, imageName))
//        noteLiveData!!.postValue(firebaseUser)
    }

    fun uploadImage(context: Context, imageName: String, filePath: Uri?) {
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Uploading...")
        progressDialog.show()

        val reference =
            storageReference.child(Constants.DATABASE_IMAGES + "/" + imageName)

        reference.putFile(filePath!!).addOnSuccessListener {
            progressDialog.dismiss()
        }.addOnFailureListener { e ->
            progressDialog.dismiss()
        }.addOnProgressListener { taskSnapshot ->
            val progress = (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
            progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
        }
    }

    fun fetchNote(context: Context, uid: String) {
        database.child(Constants.DATABASE_USER)
            .child(uid)
            .child(Constants.DATABASE_NOTE)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val notes = dataSnapshot.getValue<ArrayList<Note>>()
//                    notes!!.forEach {
//                        Log.e("Data", "" + it.note)
//                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                }
            })
    }

    fun deleteNote() {

    }

    fun updateNote() {

    }
}