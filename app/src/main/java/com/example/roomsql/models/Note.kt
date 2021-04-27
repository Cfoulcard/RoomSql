package com.example.roomsql.models

import android.os.Parcel
import android.os.Parcelable.Creator
import java.sql.Timestamp

/**
 * Defining the Note being developed. The constructor method inside the class itself
 * allows us to input content (title, content, timestamp) into the class when we are ready to use it.
 *
 *
 */
class Note(
    private var title: String?,
    private var content: String?,
    private var timestamp: String?
) {

    fun Note() {}

    protected fun Note(`in`: Parcel) {
        title = `in`.readString()
        content = `in`.readString()
        timestamp = `in`.readString()
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
        return "Note{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}'
    }

    fun describeContents(): Int {
        return 0
    }

    fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(timestamp)
    }


}