package com.felix.placebook.repository

import android.arch.lifecycle.LiveData
import android.content.Context
import com.felix.placebook.db.BookmarkDao
import com.felix.placebook.db.PlaceBookDatabase
import com.felix.placebook.model.Bookmark

class BookmarkRepo(context: Context) {

    private var db = PlaceBookDatabase.getInstance(context)
    private var bookmarkDao: BookmarkDao = db.bookmarkDao()

    fun addBookmark(bookmark: Bookmark): Long? {
        val newId = bookmarkDao.insertBookmark(bookmark)
        bookmark.id = newId
        return newId
    }

    fun createBookmark(): Bookmark {
        return Bookmark()
    }

    val allBookmarks: LiveData<List<Bookmark>>
        get() {
            return bookmarkDao.loadAll()
        }
}