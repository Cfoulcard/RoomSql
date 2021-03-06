package com.example.roomsql.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Used to help vertically space the notes listed on the NoteListActivity
 */
class VerticalSpacingItemDecorator(private var verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
      //  super.getItemOffsets(outRect, view, parent, state)
    }
}