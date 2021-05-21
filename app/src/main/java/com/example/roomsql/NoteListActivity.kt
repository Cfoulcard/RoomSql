package com.example.roomsql

import android.content.Intent
import android.os.Bundle
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

        // View Data
        mRecyclerView = findViewById(R.id.recyclerView)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener(this)

        // Context
        mNoteRepository = NoteRepository(this)

        initRecyclerView()
        retrieveNotes()
        //   insertFakeNotes()

        setSupportActionBar(findViewById(R.id.toolbar))
        title = "Room SQL Notes"

     //   Log.d("notethread", Thread.currentThread().name)
    }

    private fun retrieveNotes() {
        mNoteRepository?.retrieveNotesTask()?.observe(this, { notes ->

            if (mNotes.size > 0) {
                mNotes.clear()
            }
            if (notes != null) {
                mNotes.addAll(notes)
            }
            mNoteRecyclerAdapter!!.notifyDataSetChanged()
        })
    }

    // Note data testing for recycler view
    private fun insertFakeNotes() {
        for (i in 0..999) {
            val note = Note("", "", "")
            note.setTitle("title #$i")
            note.setContent("content #: $i")
            note.setTimestamp("Jan 2019")
            mNotes.add(note)
        }
        mNoteRecyclerAdapter!!.notifyDataSetChanged()
    }

    // RecyclerView Properties upon initialization
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
     //   Log.d("ClickMe!", "onNoteClick: $position")
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("selected note", mNotes[position])
        this.startActivity(intent)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
    }

    private fun deleteNote(note: Note) {
        mNotes.remove(note)
        mNoteRecyclerAdapter!!.notifyDataSetChanged()

        mNoteRepository?.deleteNoteTask(note)
    }

    private var itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
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