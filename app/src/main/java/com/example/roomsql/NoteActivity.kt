package com.example.roomsql

import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.roomsql.models.Note

/**
 * References the actual individual notes
 */
class NoteActivity : AppCompatActivity(),
    View.OnTouchListener,
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener,
    View.OnClickListener
{

    // UI components
    private var mLinedEditText: LinedEditText? = null
    private var mEditTitle: EditText? = null
    private var mViewTitle: TextView? = null
    private var mCheckContainer: RelativeLayout? = null
    private var mBackArrowContainer: RelativeLayout? = null
    private var mCheck: ImageButton? = null
    private var mBackArrow: ImageButton? = null

    // Variables
    private var mIsNewNote = false
    private var mNoteInitial: Note? = null
    private var mGestureDetector: GestureDetector? = null
    private val EDIT_MODE_ENABLED = 1
    private val EDIT_MODE_DISABLED = 0
    private var mMode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        mLinedEditText = findViewById(R.id.note_text)
        mEditTitle = findViewById(R.id.note_edit_title)
        mViewTitle = findViewById(R.id.note_text_title)
        mCheck = findViewById(R.id.toolbar_check)
        mBackArrow = findViewById(R.id.toolbar_back_arrow)
        mCheckContainer = findViewById(R.id.check_container)
        mBackArrowContainer = findViewById(R.id.back_arrow_container)

        setListeners()

        if (incomingIntent) {
            // this is a new note (EDIT MODE)
            setNewNoteProperties()
            enableEditMode()
        } else {
            // this is note a new note (VIEW MODE)
            setNoteProperties()
        }
    }

    // When Line Edit Text is pressed, this will be passed to the touch listener
    private fun setListeners() {
        mLinedEditText?.setOnTouchListener(this)
        mGestureDetector = GestureDetector(this, this)
        mCheck?.setOnClickListener(this)
        mViewTitle?.setOnClickListener(this)

    }

    private val incomingIntent: Boolean
        get() {
            if (intent.hasExtra("selected note")) {
                mNoteInitial = intent.getParcelableExtra("selected note")

                mMode = EDIT_MODE_ENABLED
                mIsNewNote = false
                return false
            }
            mMode = EDIT_MODE_ENABLED
            mIsNewNote = true
            return true
        }

    private fun enableEditMode() {
        mBackArrowContainer!!.visibility = View.GONE
        mCheckContainer!!.visibility = View.VISIBLE
        mViewTitle!!.visibility = View.GONE
        mEditTitle!!.visibility = View.VISIBLE
        mMode = EDIT_MODE_ENABLED
    }

    private fun disableEditMode() {
        mBackArrowContainer!!.visibility = View.VISIBLE
        mCheckContainer!!.visibility = View.GONE
        mViewTitle!!.visibility = View.VISIBLE
        mEditTitle!!.visibility = View.GONE
        mMode = EDIT_MODE_DISABLED
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
        enableEditMode()

        return false
    }

    override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
        return false
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    companion object {
        private const val TAG = "NoteActivity"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.toolbar_check -> {
                disableEditMode()
            }
            R.id.note_text_title -> {
                enableEditMode()
                mEditTitle!!.requestFocus()
                mEditTitle!!.setSelection(mEditTitle!!.length())
            }
        }
    }

    override fun onBackPressed() {
        if (mMode == EDIT_MODE_ENABLED) {
            onClick(mCheck)
        } else {
            super.onBackPressed()
        }
    }

}