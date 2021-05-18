package com.example.roomsql.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsql.R
import com.example.roomsql.models.Note
import com.example.roomsql.util.Timestamp

/**
 * RecyclerView.Adapter references the ViewHolder we just created within this class. Syncs with
 * the note objects and list of notes in the XML. The Data Structure used to hold these notes
 * is an array list
 *
 * @param mNotes - ArrayList used to automatically adapt to the size of the notes. Regular arrays
 * cannot accomplish this
 */
class NotesRecyclerAdapter(private var mNotes: ArrayList<Note>, onNoteListener: OnNoteListener?) :
    RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder>() {

    private var mOnNoteListener: OnNoteListener? = onNoteListener

    /**
     * Responsible for initializing the ViewHolder object. This method can be used almost
     * universally on any RecyclerView Adapter class with minor refactoring!
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_list_item, parent, false)
        return ViewHolder(view, mOnNoteListener)
    }

    /**
     * Responsible for holding the view of each individual list item
     */
    class ViewHolder(itemView: View, onNoteListener: OnNoteListener?) : RecyclerView.ViewHolder(
        itemView), View.OnClickListener {

        // TextViews
        var timestamp: TextView? = null
        var title:TextView? = null

        private var mOnNoteListener: OnNoteListener? = null

        init {
            title = itemView.findViewById(R.id.note_title)
            timestamp = itemView.findViewById(R.id.note_timestamp)
            mOnNoteListener = onNoteListener
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mOnNoteListener?.onNoteClick(bindingAdapterPosition)
        }
    }

    /**
     * Called for every entry in the list. Sets attributes to ViewHolder objects
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        try {
            val month = mNotes[position].getTimestamp()!!.substring(0, 3)
          //  month = timestamp?.getMonthFromNumber(month).toString()
            val year = mNotes[position].getTimestamp()!!.substring(3)
            val timestamp = "$month $year"
            holder.timestamp!!.text = timestamp
            holder.title!!.text = mNotes[position].getTitle()
        } catch (e: NullPointerException) {
            Log.e("Recycler Adapter", "onBindViewHolder: Null Pointer: " + e.message)
        }
    }

    /**
     * Returns size of the arraylist
     */
    override fun getItemCount(): Int {
        return mNotes.size
    }

    /**
     * Detect clicks and send position of items
     */
    interface OnNoteListener {
        fun onNoteClick(position: Int)
    }
}