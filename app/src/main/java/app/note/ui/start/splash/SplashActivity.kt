package app.note.ui.start.splash

import android.content.Intent
import app.note.R
import app.note.ui.base.BaseActivity
import app.note.ui.note.NoteActivity
import app.note.ui.start.login.LoginActivity

class SplashActivity : BaseActivity(R.layout.activity_splash) {

    override fun setContent() {
        if (sessionManager!!.isLogin) {
            startActivity(Intent(this@SplashActivity, NoteActivity::class.java))
        } else {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        }
        finishAffinity()
    }
}