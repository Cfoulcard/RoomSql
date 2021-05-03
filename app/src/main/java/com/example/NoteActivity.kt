package com.example

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomsql.LinedEditText
import com.example.roomsql.R
import com.example.roomsql.models.Note

class NoteActivity : AppCompatActivity() {

    // UI components
    private var mLinedEditText: LinedEditText? = null
    private var mEditTitle: EditText? = null
    private var mViewTitle: TextView? = null

    // vars
    private var mIsNewNote = false
    private var mNoteInitial: Note? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        mLinedEditText = findViewById(R.id.note_text)
        mEditTitle = findViewById(R.id.note_edit_title)
        mViewTitle = findViewById(R.id.note_text_title)

        if (incomingIntent) {
            // this is a new note (EDIT MODE)
            setNewNoteProperties()
        } else {
            // this is note a new note (VIEW MODE)
            setNoteProperties()
        }
    }

    private val incomingIntent: Boolean
        get() {
            if (intent.hasExtra("selected note")) {
                mNoteInitial = intent.getParcelableExtra("selected note")
                mIsNewNote = false
                return false
            }
            mIsNewNote = true
            return true
        }

    private fun setNewNoteProperties() {
        mViewTitle!!.text = "Note Title"
        mEditTitle!!.setText("Note Title")
    }

    private fun setNoteProperties() {
        mViewTitle!!.text = mNoteInitial!!.getTitle()
        mEditTitle?.setText(mNoteInitial?.getTitle())
        mLinedEditText!!.setText(mNoteInitial!!.getContent())
    }


    companion object {
        private const val TAG = "NoteActivity"
    }


}