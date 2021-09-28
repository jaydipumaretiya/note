package app.note.ui.start.repository

import android.app.Activity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import app.note.model.User
import app.note.util.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AuthAppRepository(activity: Activity) {

    private var activity: Activity? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var database: DatabaseReference
    private var userLiveData: MutableLiveData<FirebaseUser?>? = null
    private var loggedOutLiveData: MutableLiveData<Boolean>? = null

    init {
        this.activity = activity
        firebaseAuth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        userLiveData = MutableLiveData()
        loggedOutLiveData = MutableLiveData()
        if (firebaseAuth!!.currentUser != null) {
            userLiveData!!.postValue(firebaseAuth!!.currentUser)
            loggedOutLiveData!!.postValue(false)
        }
    }

    fun login(email: String?, password: String?) {
        firebaseAuth!!.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData!!.postValue(firebaseAuth!!.currentUser)
                } else {
                    Toast.makeText(
                        activity,
                        "Login Failure: " + task.exception!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun register(email: String?, password: String?, firstName: String?, lastName: String?) {
        firebaseAuth!!.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    writeNewUser(firebaseAuth!!.currentUser, firstName, lastName)
                } else {
                    Toast.makeText(
                        activity,
                        "Registration Failure: " + task.exception!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    fun logOut() {
        firebaseAuth!!.signOut()
        loggedOutLiveData!!.postValue(true)
    }

    fun getUserLiveData(): MutableLiveData<FirebaseUser?>? {
        return userLiveData
    }

    fun getLoggedOutLiveData(): MutableLiveData<Boolean>? {
        return loggedOutLiveData
    }

    private fun writeNewUser(firebaseUser: FirebaseUser?, firstName: String?, lastName: String?) {
        val user = User(firstName, lastName)
        database.child(Constants.DATABASE_USER).child(firebaseUser!!.uid).setValue(user)
        userLiveData!!.postValue(firebaseUser)
    }
}