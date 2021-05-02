package com.example.roomsql

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsql.adapters.NotesRecyclerAdapter
import com.example.roomsql.models.Note
import com.example.roomsql.util.VerticalSpacingItemDecorator

/**
 * Contains the home activity screen listing every individual note. Uses a RecyclerView
 * to parse the data onto the screen.
 */
class NoteListActivity : AppCompatActivity(), NotesRecyclerAdapter.OnNoteListener {

    // Variables
    private val mNotes = ArrayList<Note>()
    var mNoteRecyclerAdapter: NotesRecyclerAdapter? = null
    private var mRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_list)
         mRecyclerView = findViewById(R.id.recyclerView)

        initRecyclerView()
        insertFakeNotes()

        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        title = "Room SQL"
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

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        mRecyclerView!!.layoutManager = linearLayoutManager
        val itemDecorator = VerticalSpacingItemDecorator(10)
        mRecyclerView!!.addItemDecoration(itemDecorator)
        mNoteRecyclerAdapter = NotesRecyclerAdapter(mNotes, this)
        mRecyclerView!!.adapter = mNoteRecyclerAdapter
    }

    /**
     * Used to return a specified action, such as opening a new activity. In this case notes
     */
    override fun onNoteClick(position: Int) {
        Log.d("ClickMe!", "onNoteClick: $position")
    }

}