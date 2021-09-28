package app.note.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.note.R
import app.note.util.NetworkUtils
import app.note.util.SessionManager

/**
 * @author Enlistech.
 * Email - enlistechs@gmail.com
 */
abstract class BaseFragment(layoutResId: Int) : Fragment(layoutResId) {

    private var rootView: View? = null
    private lateinit var dialog: Dialog

    var sessionManager: SessionManager? = null

    val isNetworkConnected: Boolean
        get() {
            return if (!NetworkUtils.isNetworkConnected(requireContext())) {
                showErrorToast(getString(R.string.error_internet))
                false
            } else {
                true
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootView = view
        sessionManager = SessionManager(requireContext())
        super.onViewCreated(view, savedInstanceState)
    }

    fun showErrorToast(errorMsg: String) {
        Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_LONG).show()
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
                requireActivity(),
                orientation,
                reverseLayout
            )
        recyclerView.setHasFixedSize(hasFixedSize)
        recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
        recyclerView.layoutManager = linearLayoutManager
        return recyclerView
    }

    protected fun setGridRecyclerView(
        recyclerView: RecyclerView,
        orientation: Int,
        reverseLayout: Boolean,
        spanCount: Int,
        hasFixedSize: Boolean,
        nestedScrollingEnabled: Boolean
    ): RecyclerView {
        val gridLayoutManager =
            GridLayoutManager(
                requireActivity(),
                spanCount,
                orientation,
                reverseLayout
            )
        recyclerView.setHasFixedSize(hasFixedSize)
        recyclerView.isNestedScrollingEnabled = nestedScrollingEnabled
        recyclerView.layoutManager = gridLayoutManager
        return recyclerView
    }
}
