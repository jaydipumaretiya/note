package app.note.ui.start.splash

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import app.note.R
import app.note.databinding.ActivitySplashBinding
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding
import app.note.ui.note.NoteActivity
import app.note.ui.start.AuthViewModelFactory
import app.note.ui.start.login.LoginActivity
import app.note.ui.start.viewmodel.AuthViewModel

class SplashActivity : BaseActivity(R.layout.activity_splash) {

    private val binding by viewBinding(ActivitySplashBinding::inflate)
    private lateinit var authViewModel: AuthViewModel

    override fun setContent() {
//        authViewModel = ViewModelProvider(
//            this,
//            AuthViewModelFactory(this)
//        ).get(AuthViewModel::class.java)
//
//        authViewModel.getUserLiveData()!!.observe(this, {
//            if (it != null) {
//                startActivity(Intent(this@SplashActivity, NoteActivity::class.java))
//            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            }
            finishAffinity()
//        })
    }
}