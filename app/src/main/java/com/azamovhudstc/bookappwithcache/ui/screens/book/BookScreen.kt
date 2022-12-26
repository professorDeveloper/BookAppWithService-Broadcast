package com.azamovhudstc.bookappwithcache.ui.screens.book

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.service.BookService
import com.azamovhudstc.bookappwithcache.ui.adapter.BooksAdapter
import com.azamovhudstc.bookappwithcache.utils.showSnack
import com.azamovhudstc.bookappwithcache.viewmodel.HomeScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.HomeScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BookScreen : Fragment(R.layout.fragment_home),
    BooksAdapter.ContactItemCallBack.SetLongClickListener {
    private val adapter by lazy { BooksAdapter(this) }
    private var size = 0
    private val viewModel: HomeScreenViewModel by viewModels<HomeScreenViewModelImp>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.statusLiveData.observe(this) {
            toolbar.title = it
        }
        viewModel.messageLiveData.observe(this) {
            showSnack(it)
        }
        viewModel.editBookLiveData.observe(this) {

        }
        viewModel.getAllBooksLiveData.observe(this, getAllBookObserver)
        viewModel.addBookLiveData.observe(this) {
            findNavController().navigate(R.id.addBookScreen)
        }
        viewModel.progressLiveData.observe(this, progressObserver)
    }

    private val progressObserver = Observer<Boolean> {
        if (it) {
            progressHome.show()
            rv_book.visibility=View.GONE
        } else {
            progressHome.hide()
            rv_book.visibility=View.VISIBLE
        }
    }
    private val getAllBookObserver = Observer<List<BookEntities>> {
        adapter.submitList(it)
        size = it.size
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_book.adapter = adapter
        requireContext().startService(Intent(requireContext(), BookService::class.java))
        statuser()
        btnRefresh.setOnClickListener {
            viewModel.reloadData()
        }
        viewModel.status()
        viewModel.getAllBooks()
        buttonAdd.setOnClickListener {
            viewModel.addBook()
        }
    }

    private fun statuser() {
        var netwo = NetworkReceiver.getInstance()
        requireContext().registerReceiver(
            netwo,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        netwo!!.setOnConnectListener {
            viewModel.status()
        }
    }

    override fun deleteClick(contact: BookEntities) {
        viewModel.delete(contact)
    }

    override fun showClick(contact: BookEntities) {
        var bundle = Bundle()
        bundle.putSerializable("data", contact)
        findNavController().navigate(R.id.showBookScreen, bundle)

    }

    override fun editItemClick(contact: BookEntities) {
        var bundle = Bundle()
        bundle.putSerializable("data", contact)
        findNavController().navigate(R.id.editBookScreen, bundle)
    }

    override fun likedClick(contact: BookEntities) {

    }


}