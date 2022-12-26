package com.azamovhudstc.bookappwithcache.repo.impl

import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.azamovhudstc.bookappwithcache.app.App
import com.azamovhudstc.bookappwithcache.data.local.database.appDatabase.AppDatabase
import com.azamovhudstc.bookappwithcache.data.local.database.entites.BookEntities
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.DeleteBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.AddBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.request.book.EditBookRequest
import com.azamovhudstc.bookappwithcache.data.remote.retrofit2.service.book.BookService
import com.azamovhudstc.bookappwithcache.repo.BookRepository
import com.azamovhudstc.bookappwithcache.service.recevier.MyReceiver
import com.azamovhudstc.bookappwithcache.utils.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookApi: BookService,
    private val pref: AppReference,
) : BookRepository {
    var database = AppDatabase.getInstance()
    private val mediatorLiveData = MediatorLiveData<Unit>()
    private val myReceiver = MyReceiver.getInstance()
    private var localBroadcastManager: LocalBroadcastManager =
        LocalBroadcastManager.getInstance(App.instance)

    init {
        localBroadcastManager.registerReceiver(myReceiver, IntentFilter("ACTION_NEW_BOOK_LOAD"))
        mediatorLiveData.observeForever {
        }
    }

    override fun getAllBook(): Flow<LiveData<List<BookEntities>>> = flow {
        if (hasConnection()) {
            reLoadLocalData()
        }
        emit(database.bookDao().getBooks())
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteBook(booksResponseItem: Int, id: Int) {
        if (hasConnection()) {
            bookApi.deleteBook("Bearer " + pref.getToken()!!, DeleteBookRequest(id))
            loadData(pref.getToken()!!)

        } else {
            var book = database.bookDao().getBook(booksResponseItem)
            Log.d("TTT", "deleteBook: $book")
            val data = BookEntities(
                book.id,
                book.author,
                book.description,
                false,
                book.pageCount,
                book.title,
                book.localId, -1
            )

            database.bookDao().update(data)

        }
    }


    override suspend fun addBook(bookRequest: AddBookRequest) {
        if (hasConnection()) {
            bookApi.addBook("Bearer ${pref.getToken()}", bookRequest)
            loadData(pref.getToken()!!)
        } else {

            database.bookDao().insert(bookRequest.toAddRoom())

        }

    }

    override suspend fun editBook(
        id: Int,
        bookRequest: EditBookRequest,
        localId: Int
    ) {
        if (hasConnection()) {
            bookApi.editBook("Bearer ${pref.getToken()}", bookRequest)
            loadData(pref.getToken()!!)
        }
        else {
            var data = database.bookDao().getBook(localId)
            var entity = BookEntities(
                data.id,
                bookRequest.author,
                bookRequest.description,
                false,
                bookRequest.pageCount,
                bookRequest.title,
                data.localId,
                2
            )
            database.bookDao().update(entity)
        }


    }


    override suspend fun reLoadLocalData(): Boolean {
        if (hasConnection()) {
            loadData(token = pref.getToken()!!)
            return true
        }
        return false

    }


    override suspend fun internetState(): Flow<Boolean> = flow {
        emit(hasConnection())
    }.flowOn(Dispatchers.IO)


    private suspend fun loadData(token: String) {
        //Tekshirish
            val list = database.bookDao().getAllBooks() as ArrayList<BookEntities>
            list.filter {
                it.state == 1
            }.forEach { add ->
                val intent = Intent("ACTION_NEW_BOOK_LOAD")
                intent.putExtra("bookRequest", add.toNotification("Kitob  Qo`shildi"))
                localBroadcastManager.sendBroadcast(intent)
                Log.d("!@#", "loadData:ADD  ")
                bookApi.addBook("Bearer $token", add.toAdd())


            }
            list.filter {
                it.state == 2
            }.forEach { update ->
                val intent = Intent("ACTION_NEW_BOOK_LOAD")
                intent.putExtra("bookRequest", update.toNotification("Kitob O`zgartirildi"))
                localBroadcastManager.sendBroadcast(intent)
                Log.d("!@#", "loadData:EDIT  ")
                bookApi.editBook("Bearer $token", update.toUpdate())

            }
            list.filter {
                it.state == -1
            }.forEach { delete ->
                val intent = Intent("ACTION_NEW_BOOK_LOAD")
                intent.putExtra("bookRequest", delete.toNotification("Kitob O`chirildi"))
                localBroadcastManager.sendBroadcast(intent)
                Log.d("!@#", "loadData:DELETE  ")

                bookApi.deleteBook("Bearer $token", delete.toDelete().toDeleteRequest())


            }

            val getAllBook = bookApi.getAllBooks("Bearer $token")
            if (getAllBook.isSuccessful) {
                val body = getAllBook.body()

                database.bookDao().updateWholeDB(body?.map { it.toBookEntity() }!!)

            }

    }

}