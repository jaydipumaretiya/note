package app.note.util

import android.content.Context
import android.content.SharedPreferences

/**
 * @author Enlistech.
 */
class SessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor

    private val preferencesName = "note"
    private val login = "login"
    private val uid = "uid"
    private val language = "language"
    private val user = "user"

    init {
        sharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.apply()
    }

//    var storedUser: User?
//        get() {
//            val json = sharedPreferences.getString(user, "")
//            val type = object : TypeToken<User>() {
//            }.type
//            return Gson().fromJson(json, type)
//        }
//        set(storedAppData) {
//            editor.putString(user, Gson().toJson(storedAppData))
//            editor.commit()
//        }

    var isLogin: Boolean
        get() = sharedPreferences.getBoolean(login, false)
        set(isLogin) {
            editor.putBoolean(login, isLogin)
            editor.commit()
        }

    var userUID: String?
        get() = sharedPreferences.getString(uid, "")
        set(userUID) {
            editor.putString(uid, userUID)
            editor.commit()
        }

    var languageISO: String?
        get() = sharedPreferences.getString(language, "en")
        set(languageISO) {
            editor.putString(language, languageISO)
            editor.commit()
        }
}
