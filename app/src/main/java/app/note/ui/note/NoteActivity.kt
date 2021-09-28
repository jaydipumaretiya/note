package app.note.ui.note

import app.note.R
import app.note.databinding.ActivityNoteBinding
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding

class NoteActivity : BaseActivity(R.layout.activity_note) {

    private val binding by viewBinding(ActivityNoteBinding::inflate)

    override fun setContent() {

    }
}