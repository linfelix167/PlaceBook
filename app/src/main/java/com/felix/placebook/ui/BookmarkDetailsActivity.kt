package com.felix.placebook.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.felix.placebook.R
import kotlinx.android.synthetic.main.activity_bookmark_detail.*

class BookmarkDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmark_detail)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }
}
