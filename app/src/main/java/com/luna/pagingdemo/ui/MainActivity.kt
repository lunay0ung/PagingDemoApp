package com.luna.pagingdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.luna.pagingdemo.R
import com.luna.pagingdemo.viewmodel.BookViewModel
import com.luna.pagingdemo.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val adapter = UserAdapter()
        val adapter = BookAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        val itemViewModel = ViewModelProviders.of(this)
            .get(BookViewModel::class.java)
        //.get(UserViewModel::class.java)
        itemViewModel.bookPagedList.observe(this, Observer {
            adapter.submitList(it)
        })
       /* itemViewModel.userPagedList.observe(this, Observer {
            adapter.submitList(it)
        })*/
        recyclerView.adapter = adapter
    }
}