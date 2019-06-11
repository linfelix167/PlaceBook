package com.felix.placebook.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.felix.placebook.R
import com.felix.placebook.ui.MapsActivity
import com.felix.placebook.viewmodel.MapsViewModel
import kotlinx.android.synthetic.main.bookmark_item.view.*

class BookmarkListAdapter(
    private var bookmarkData: List<MapsViewModel.BookmarkView>?,
    private val mapsActivity: MapsActivity) : RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setBookmarkData(bookmarks: List<MapsViewModel.BookmarkView>) {
        this.bookmarkData = bookmarks
        notifyDataSetChanged()
    }

    class ViewHolder(v: View, private val mapsActivity: MapsActivity) : RecyclerView.ViewHolder(v) {
        val nameTextView: TextView = v.findViewById(R.id.bookmarkNameTextView) as TextView
        val categoryImageView: ImageView = v.findViewById(R.id.bookmarkIcon) as ImageView
    }
}