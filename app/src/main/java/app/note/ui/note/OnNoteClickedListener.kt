package app.note.ui.note

import app.note.model.Note

interface OnNoteClickedListener {
    fun onNoteClicked(note: Note)
}