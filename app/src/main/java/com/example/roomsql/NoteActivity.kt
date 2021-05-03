package com.example.roomsql

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomsql.models.Note

/**
 * References the actual individual notes
 */
class NoteActivity : AppCompatActivity(),
    View.OnTouchListener,
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener
{

    // UI components
    private var mLinedEditText: LinedEditText? = null
    private var mEditTitle: EditText? = null
    private var mViewTitle: TextView? = null

    // Variables
    private var mIsNewNote = false
    private var mNoteInitial: Note? = null
    private var mGestureDetector: GestureDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        mLinedEditText = findViewById(R.id.note_text)
        mEditTitle = findViewById(R.id.note_edit_title)
        mViewTitle = findViewById(R.id.note_text_title)

        setListeners()

        if (incomingIntent) {
            // this is a new note (EDIT MODE)
            setNewNoteProperties()
        } else {
            // this is note a new note (VIEW MODE)
            setNoteProperties()
        }
    }

    // When Line Edit Text is pressed, this will be passed to the touch listener
    private fun setListeners() {
        mLinedEditText?.setOnTouchListener(this)
        mGestureDetector = GestureDetector(this, this)
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

    // View.OnTouchListener Implemented Member
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        v?.performClick()
        return mGestureDetector!!.onTouchEvent(event)
    }

    // Gesture Detector Implemented Members
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        return
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        return
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
    }

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        Log.d("onDoubleTap", "double tapped!")
        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private const val TAG = "NoteActivity"
    }

}