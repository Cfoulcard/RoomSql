package com.example.roomsql.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.sql.Timestamp

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
class Note (

    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name ="title")
    private var title: String?,

    @ColumnInfo(name ="content")
    private var content: String?,

    @ColumnInfo(name ="timestamp")
    private var timestamp: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    @Ignore
    fun Note() {}

    protected fun Note(`in`: Parcel) {
        title = `in`.readString()
        content = `in`.readString()
        timestamp = `in`.readString()
    }

    @JvmName("getId1")
    fun getId(): Int? {
        return id
    }

    fun setId(): Int? {
        return id
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

    // Prints out Note info into the log
    override fun toString(): String {
        return "Note(id=$id, " +
                "title=$title, " +
                "content=$content, " +
                "timestamp=$timestamp)"
    }



override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeValue(id)
    parcel.writeString(title)
    parcel.writeString(content)
    parcel.writeString(timestamp)
}

override fun describeContents(): Int {
    return 0
}

companion object CREATOR : Parcelable.Creator<Note> {
    override fun createFromParcel(parcel: Parcel): Note {
        return Note(parcel)
    }

    override fun newArray(size: Int): Array<Note?> {
        return arrayOfNulls(size)
    }
}


}