package com.azamovhudstc.bookappwithcache.ui.screens.book

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_show_book.*
@AndroidEntryPoint

class ShowBookScreen : Fragment(R.layout.fragment_show_book) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serializable = arguments?.getSerializable("data") as BookEntities
        show_title.title = serializable.title
        show_author.text = "Muallif: ${serializable.author}"
        show_des.text = serializable.description
        show_page.text = "Hajmi: ${serializable.pageCount} Bet"

    }
}