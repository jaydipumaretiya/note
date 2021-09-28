package app.note.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val firstName: String? = null, val lastName: String? = null)