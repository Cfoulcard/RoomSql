package com.example.roomsql.adapters

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsql.R
import com.example.roomsql.models.Note

/**
 * RecyclerView.Adapter references the ViewHolder we just created within this class. Syncs with
 * the note objects and list of notes in the XML. The Data Structure used to hold these notes
 * is an array list
 */
class NotesRecyclerAdapter(arrayList: ArrayList<Note>) :
    RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder>() {

    // Using arraylist to automatically adapt to the size of the notes. Regular arrays cannot
    // accomplish this
    private var mNotes = arrayList

    /**
     * Responsible for holding the view of each individual list item
     */
    class ViewHolder : RecyclerView.ViewHolder {

        var timestamp: TextView? = null
        var title:TextView? = null

        constructor(itemView: View) : super(itemView) {
            title = itemView.findViewById(R.id.note_title)
            timestamp = itemView.findViewById(R.id.note_timestamp)
        }
    }

    /**
     * Responsible for initializing the viewholder object. This method can be used almost
     * universally on any RecyclerView Adapter class with minor refactoring!
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_note_list_item, parent, false)
        return ViewHolder(view)
    }

    /**
     * Called for every entry in your list. Sets attributes to ViewHolder objects
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.timestamp?.text = mNotes[position].getTimestamp()
        holder.title?.text = mNotes[position].getTitle()
    }

    /**
     * Returns size of the arraylist
     */
    override fun getItemCount(): Int {
        return mNotes.size
    }
}