package app.note.util

import android.content.Context
import android.os.Build
import android.util.Patterns
import java.util.*

object AppUtils {

    fun setLocale(context: Context) {
        val language = SessionManager(context).languageISO!!
        val resources = context.resources
        val dm = resources.displayMetrics
        val config = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(Locale(language.toLowerCase()))
        } else {
            config.locale = Locale(language.toLowerCase())
        }
        resources.updateConfiguration(config, dm)
    }

    fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}