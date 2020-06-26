package com.luna.pagingdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luna.pagingdemo.R
import com.luna.searchbooks.model.Book
import kotlinx.android.synthetic.main.user_list_item.view.*

class BookAdapter : PagedListAdapter<Book, BookAdapter.BookViewHolder>(BOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return BookViewHolder(view)
    }
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it) }
    }


    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.user_avatar
        private val userName = view.user_name
        private val userEmail = view.user_email
        fun bind(book: Book) {
            userName.text = book.title//user.firstName + " " + user.lastName
            userEmail.text = book.publisher//user.email
            Glide.with(imageView.context)
                .load(book.thumbnail)
                .placeholder(R.drawable.loading)
                .into(imageView);
        }
    }
    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.isbn == newItem.isbn
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                newItem.isbn == oldItem.isbn
        }
    }
}