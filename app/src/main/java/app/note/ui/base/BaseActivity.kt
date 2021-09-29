package app.note.ui.base

import android.app.Dialog
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.note.R
import app.note.util.AppUtils
import app.note.util.NetworkUtils
import app.note.util.SessionManager

/**
 * @author Enlistech.
 * Email - enlistechs@gmail.com
 */
abstract class BaseActivity(layoutResId: Int) : AppCompatActivity(layoutResId) {

    var sessionManager: SessionManager? = null
    var dialog: Dialog? = null

    val isNetworkConnected: Boolean
        get() {
            return if (!NetworkUtils.isNetworkConnected(this@BaseActivity)) {
                showToast(getString(R.string.error_internet))
                false
            } else {
                true
            }
        }

    override fun setContentView(layoutResID: Int) {
        AppUtils.setLocale(this)
        super.setContentView(layoutResID)
        sessionManager = SessionManager(this)
        setContent()
    }

    protected abstract fun setContent()

    fun showToast(errorMsg: String) {
        Toast.makeText(this@BaseActivity, errorMsg, Toast.LENGTH_LONG).show()
    }

    protected fun setLinearRecyclerView(
        recyclerView: RecyclerView,
        orientation: Int,
        reverseLayout: Boolean,
        hasFixedSize: Boolean,
        nestedScrollingEnabled: Boolean
    ): RecyclerView {
        val linearLayoutManager =
            LinearLayoutManager(
                this,
                orientation,
                reverseLayout
            )
        recyclerView.setHasFixedSize(hasFixedSize)
        recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
        recyclerView.layoutManager = linearLayoutManager
        return recyclerView
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
