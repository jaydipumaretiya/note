package app.note.ui.note

import androidx.lifecycle.ViewModelProvider
import app.note.R
import app.note.databinding.ActivityAddNoteBinding
import app.note.databinding.LayoutToolbarBinding
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding
import app.note.ui.note.viewmodel.NoteViewModel

class AddNoteActivity : BaseActivity(R.layout.activity_add_note) {

    private val binding by viewBinding(ActivityAddNoteBinding::inflate)
    private lateinit var layoutToolbarBinding: LayoutToolbarBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun setContent() {
        layoutToolbarBinding = LayoutToolbarBinding.bind(binding.root)

        layoutToolbarBinding.toolbar.title = getString(R.string.add_note)
        setSupportActionBar(layoutToolbarBinding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setupViewModel()

        binding.btnAddNote.setOnClickListener {
            noteViewModel.addNote(
                this@AddNoteActivity,
                sessionManager!!.userUID!!,
                binding.edtNote.text.toString(),
                null
            )
        }
    }

    private fun setupViewModel() {
        noteViewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(this)
        ).get(NoteViewModel::class.java)

//        noteViewModel.addNote(Constants.currentFirebaseUser, )!!.observe(this, {
//            if (it != null) {
//                showToast(getString(R.string.message_login_success))
//                startActivity(Intent(this@LoginActivity, NoteActivity::class.java))
//                finishAffinity()
//            }
//        })
    }


}