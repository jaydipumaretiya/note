package app.note.ui.start.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import app.note.R
import app.note.databinding.ActivityLoginBinding
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding
import app.note.ui.note.NoteActivity
import app.note.ui.start.AuthViewModelFactory
import app.note.ui.start.viewmodel.AuthViewModel
import app.note.ui.start.register.RegisterActivity
import app.note.util.AppUtils.isValidEmail

class LoginActivity : BaseActivity(R.layout.activity_login) {

    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private lateinit var authViewModel: AuthViewModel

    override fun setContent() {
        setupViewModel()

        binding.btnLogin.setOnClickListener {
            validate()
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finishAffinity()
        }
    }

    private fun setupViewModel() {
        authViewModel = ViewModelProvider(
            this,
            AuthViewModelFactory(this)
        ).get(AuthViewModel::class.java)

        authViewModel.getUserLiveData()!!.observe(this, {
            if (it != null) {
                showToast(getString(R.string.message_login_success))
                startActivity(Intent(this@LoginActivity, NoteActivity::class.java))
                finishAffinity()
            }
        })
    }

    private fun validate() {
        when {
            !binding.edtEmail.text!!.isValidEmail() -> {
                showToast(getString(R.string.message_valid_email))
                return
            }
            binding.edtPassword.text!!.isEmpty() -> {
                showToast(getString(R.string.message_valid_password))
                return
            }
            (binding.edtPassword.text!!.length < 6) -> {
                showToast(getString(R.string.message_valid_password_length))
                return
            }
            else -> {
                signIn()
            }
        }
    }

    private fun signIn() {
        val email: String = binding.edtEmail.text.toString()
        val password: String = binding.edtPassword.text.toString()
        authViewModel.login(email, password)
    }
}