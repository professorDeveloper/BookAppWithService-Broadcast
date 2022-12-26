package com.azamovhudstc.bookappwithcache.ui.screens.book

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.utils.showSnack
import com.azamovhudstc.bookappwithcache.utils.showToast
import com.azamovhudstc.bookappwithcache.viewmodel.EditScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.EditScreenViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_edit.*
@AndroidEntryPoint

class EditBookScreen : Fragment(R.layout.fragment_edit) {
    private val viewModel: EditScreenViewModel by viewModels<EditScreenViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.successBackLiveData.observe(this) {
            findNavController().popBackStack()
            showSnack("Muffaqyatli qo`shildi !")
        }
        viewModel.progressStatusLiveData.observe(this) {
            if (it) editBookProgress.show() else editBookProgress.hide()
        }
        viewModel.errorLiveData.observe(this) {
            showToast(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val serializable = arguments?.getSerializable("data") as BookEntities
        editBookAuthor.setText(serializable.author)
        editBookDes.setText(serializable.description)
        editBookName.setText(serializable.title)
        editBookPage.setText(serializable.pageCount.toString())

        toolbar2_edit.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        edit.setOnClickListener {
            if (editBookAuthor.text.trim().toString().isEmpty() || editBookDes.text.toString()
                    .trim()
                    .isEmpty() || editBookName.text.trim().toString().isEmpty() ||
                editBookPage.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Maydonlar bo`sh", Toast.LENGTH_SHORT).show()
            } else {
                serializable.description = editBookDes.text.toString()
                serializable.author = editBookAuthor.text.toString()
                serializable.pageCount = editBookPage.text.toString().toInt()
                serializable.title = editBookName.text.toString()
                serializable.state = 2
                viewModel.editBook(
                    serializable
                )
            }
        }

    }
}