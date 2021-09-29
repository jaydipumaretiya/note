package app.note.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Note(val id: String? = null, val note: String? = null, val image: String? = null)