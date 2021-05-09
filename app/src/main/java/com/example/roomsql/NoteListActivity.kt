package com.example.roomsql

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsql.adapters.NotesRecyclerAdapter
import com.example.roomsql.models.Note
import com.example.roomsql.persistence.NoteRepository
import com.example.roomsql.util.VerticalSpacingItemDecorator
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Contains the home activity screen listing every individual note. Uses a RecyclerView
 * to parse the data onto the screen.
 */
class NoteListActivity :
    AppCompatActivity(),
    NotesRecyclerAdapter.OnNoteListener,
    View.OnClickListener
{

    // Variables
    private val mNotes = ArrayList<Note>()
    private var mNoteRecyclerAdapter: NotesRecyclerAdapter? = null
    private var mRecyclerView: RecyclerView? = null
    private var mNoteRepository: NoteRepository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)

         mRecyclerView = findViewById(R.id.recyclerView)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this)

        mNoteRepository = NoteRepository(this)

        initRecyclerView()
      //  insertFakeNotes()

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        title = "Room SQL"
    }

    // Observe changes to the live data object
    fun retreiveNotes() {
        mNoteRepository?.retrieveNotesTask()?.observe(this) {
            // If notes are more than 0, clear data
            if (mNotes.size > 0) {
                mNotes.clear()
            }
            // Add all notes to the list
            if (notes != null) {
                mNotes.addAll(notes)
            }
            mNoteRecyclerAdapter!!.notifyDataSetChanged()
        }
    }




    // Note data testing for recycler view
    private fun insertFakeNotes() {
        for (i in 0..999) {
            val note = Note(0, "", "", "")
            note.setTitle("title #$i")
            note.setContent("content #: $i")
            note.setTimestamp("Jan 2019")
            mNotes.add(note)
        }
        mNoteRecyclerAdapter!!.notifyDataSetChanged()
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = linearLayoutManager
        val itemDecorator = VerticalSpacingItemDecorator(10)
        mRecyclerView!!.addItemDecoration(itemDecorator)
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView)
        mNoteRecyclerAdapter = NotesRecyclerAdapter(mNotes, this)
        mRecyclerView!!.adapter = mNoteRecyclerAdapter
    }

    /**
     * Used to return a specified action, such as opening a new activity. In this case notes
     */
    override fun onNoteClick(position: Int) {
        Log.d("ClickMe!", "onNoteClick: $position")
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("selected note", mNotes.get(position))
        this.startActivity(intent)
    }

    override fun onClick(v: View?) {
        val intent: Intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
    }

    private fun deleteNote(note: Note) {
        mNotes.remove(note)
        mNoteRecyclerAdapter!!.notifyDataSetChanged()
    }

    var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteNote(mNotes[viewHolder.adapterPosition])
            }
        }

}