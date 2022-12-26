package com.azamovhudstc.bookappwithcache.ui.screens.book

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.utils.showSnack
import com.azamovhudstc.bookappwithcache.utils.showToast
import com.azamovhudstc.bookappwithcache.viewmodel.AddScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.HomeScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.AddScreenViewModelImp
import com.azamovhudstc.bookappwithcache.viewmodel.imp.HomeScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_book_screen.*
import kotlinx.android.synthetic.main.fragment_home.*
@AndroidEntryPoint

class AddBookScreen : Fragment(R.layout.fragment_add_book_screen) {
    private val viewModel: AddScreenViewModel by viewModels<AddScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.successBackLiveData.observe(this) {
            findNavController().popBackStack()
            showSnack("Muffaqyatli qo`shildi !")
        }
        viewModel.progressStatusLiveData.observe(this) {
            if (it) addBookProgress.show() else addBookProgress.hide()
        }
        viewModel.errorLiveData.observe(this) {
            showToast(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar2_.setNavigationOnClickListener {
            findNavController().popBackStack()

        }
        addBook.setOnClickListener {
            if (addBookAuthor.text.trim().toString().isEmpty() || addBookDes.text.toString().trim()
                    .isEmpty() || addBookName.text.trim().toString().isEmpty() ||
                addBookPage.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Maydonlar bo`sh", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.addBook(
                    AddBookRequest(
                        author = addBookAuthor.text.toString(),
                        addBookDes.text.toString(),
                        pageCount = addBookPage.text.toString().toInt(),
                        addBookName.text.toString()
                    )
                )
            }
        }
    }
}