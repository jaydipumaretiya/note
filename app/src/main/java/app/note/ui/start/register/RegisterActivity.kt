package app.note.ui.start.register

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import app.note.R
import app.note.databinding.ActivityRegisterBinding
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding
import app.note.ui.note.NoteActivity
import app.note.ui.start.AuthViewModelFactory
import app.note.ui.start.login.LoginActivity
import app.note.ui.start.viewmodel.AuthViewModel
import app.note.util.AppUtils.isValidEmail

class RegisterActivity : BaseActivity(R.layout.activity_register) {

    private val binding by viewBinding(ActivityRegisterBinding::inflate)
    private lateinit var authViewModel: AuthViewModel

    override fun setContent() {
        setupViewModel()

        binding.btnRegister.setOnClickListener {
            validate()
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
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
                sessionManager!!.userUID = it.uid
                sessionManager!!.isLogin = true
                showToast(getString(R.string.message_register_success))
                startActivity(Intent(this@RegisterActivity, NoteActivity::class.java))
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
        val firstName: String = binding.edtFirstName.text.toString()
        val lastName: String = binding.edtLastName.text.toString()
        val email: String = binding.edtEmail.text.toString()
        val password: String = binding.edtPassword.text.toString()

        authViewModel.register(email, password, firstName, lastName)
    }
}