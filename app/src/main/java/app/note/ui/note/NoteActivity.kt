package app.note.ui.note

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import app.note.R
import app.note.databinding.ActivityNoteBinding
import app.note.databinding.LayoutToolbarBinding
import app.note.model.Note
import app.note.ui.base.BaseActivity
import app.note.ui.base.delegate.viewBinding
import app.note.ui.note.viewmodel.NoteViewModel

class NoteActivity : BaseActivity(R.layout.activity_note), OnNoteClickedListener {

    private val binding by viewBinding(ActivityNoteBinding::inflate)
    private lateinit var layoutToolbarBinding: LayoutToolbarBinding
    private lateinit var noteViewModel: NoteViewModel

    override fun setContent() {
        layoutToolbarBinding = LayoutToolbarBinding.bind(binding.root)

        layoutToolbarBinding.toolbar.title = getString(R.string.note)
        setSupportActionBar(layoutToolbarBinding.toolbar)

        setupViewModel()
    }

    private fun setupViewModel() {
        noteViewModel = ViewModelProvider(
            this,
            NoteViewModelFactory(this)
        ).get(NoteViewModel::class.java)

        noteViewModel.fetchNote(this@NoteActivity, sessionManager!!.userUID!!)

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNote)
    }

    var simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.DOWN or ItemTouchHelper.UP
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            Toast.makeText(this@NoteActivity, "on Move", Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            Toast.makeText(this@NoteActivity, "on Swiped ", Toast.LENGTH_SHORT).show()
            //Remove swiped item from list and notify the RecyclerView
            val position = viewHolder.adapterPosition
//            arrayList.remove(position)
//            adapter.notifyDataSetChanged()
        }
    }

    override fun onNoteClicked(note: Note) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAddNote -> {
                startActivity(
                    Intent(this@NoteActivity, AddNoteActivity::class.java)
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}