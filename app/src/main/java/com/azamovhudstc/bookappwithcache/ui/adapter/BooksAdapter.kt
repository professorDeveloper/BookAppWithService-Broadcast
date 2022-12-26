package com.azamovhudstc.bookappwithcache.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import kotlinx.android.synthetic.main.books_item.view.*

class BooksAdapter(var setLongClickListener: ContactItemCallBack.SetLongClickListener) :
    ListAdapter<BookEntities, BooksAdapter.Wh>(ContactItemCallBack) {
    inner class Wh(view: View) : RecyclerView.ViewHolder(view) {
        @SuppressLint("NewApi", "SetTextI18n")
        fun onBind(contact: BookEntities, position: Int) {
            itemView.name.text = contact.title
            itemView.author.text = contact.author
            itemView.delete.setOnClickListener {
                setLongClickListener.deleteClick(contact)
            }
            itemView.edit.setOnClickListener {
                setLongClickListener.editItemClick(contact)
            }
            itemView.setOnClickListener {
                setLongClickListener.showClick(contact)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Wh {
        return Wh(LayoutInflater.from(parent.context).inflate(R.layout.books_item, parent, false))
    }

    override fun onBindViewHolder(holder: Wh, position: Int) {
        holder.onBind(getItem(position), position)
    }

    object ContactItemCallBack : DiffUtil.ItemCallback<BookEntities>() {
        override fun areItemsTheSame(
            oldItem: BookEntities,
            newItem: BookEntities
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BookEntities,
            newItem: BookEntities
        ): Boolean {
            return oldItem == newItem
        }


        interface SetLongClickListener {
            fun deleteClick(contact: BookEntities)
            fun showClick(contact: BookEntities)
            fun editItemClick(contact: BookEntities)
            fun likedClick(contact: BookEntities)
        }

    }

}