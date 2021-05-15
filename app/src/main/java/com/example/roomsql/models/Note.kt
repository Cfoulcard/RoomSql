package com.example.roomsql.models

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Defining the Note being developed. The constructor method inside the class itself
 * allows us to input content (title, content, timestamp) into the class when we are ready to use it.
 *
 * This class implements Parcelable to package the Note object and add it to a bundle.
 *
 * This class also serves as a model for the data (entity). Essentially a table with a name,
 * columns(field info), and a primary key to identify what column to add/use in Room
 */
@Entity(tableName = "notes")
open class Note : Parcelable {

    @PrimaryKey(autoGenerate = true)
    private var id = 0

    @ColumnInfo(name = "title")
    private var title: String? = null

    @ColumnInfo(name = "content")
    private var content: String? = null

    @ColumnInfo(name = "timestamp")
    private var timestamp: String? = null

    constructor(title: String?, content: String?, timestamp: String?) {
        this.title = title
        this.content = content
        this.timestamp = timestamp
    }

    @Ignore
    constructor() {
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        title = `in`.readString()
        content = `in`.readString()
        timestamp = `in`.readString()
    }

    fun getId(): Int {
        return id
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getContent(): String? {
        return content
    }

    fun setContent(content: String?) {
        this.content = content
    }

    fun getTimestamp(): String? {
        return timestamp
    }

    fun setTimestamp(timestamp: String?) {
        this.timestamp = timestamp
    }

    override fun toString(): String {
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}'
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(timestamp)
    }

    companion object CREATOR : Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }


}


