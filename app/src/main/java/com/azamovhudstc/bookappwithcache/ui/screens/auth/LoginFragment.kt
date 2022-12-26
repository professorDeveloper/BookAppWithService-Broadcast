package com.azamovhudstc.bookappwithcache.ui.screens.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.AuthRequest
import com.azamovhudstc.bookappwithcache.utils.showToast
import com.azamovhudstc.bookappwithcache.utils.state
import com.azamovhudstc.bookappwithcache.viewmodel.LoginViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.LoginViewModelImp
import com.azamovhudstc.bookappwithcache.viewmodel.imp.RegisterViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
@AndroidEntryPoint

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModelImp>()
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = Bundle()
        viewModel.openVerifyScreenLiveData.observe(this) {
            findNavController().navigate(
                R.id.verifyFragment,
                bundle,
                NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
            )
        }
        viewModel.progressLiveData.observe(this, progressObserver)
        viewModel.errorLiveData.observe(this) {
            showToast(it)
        }
        viewModel.notConnectionLiveData.observe(this) { showToast("No Connection !") }
    }

    private val progressObserver = Observer<Boolean> {
        if (it) progressLogin.show() else progressLogin.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startRegister.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
        viewModel.changeButtonStatusLiveData.value =
            !(phone.unMaskedText.toString().isEmpty() || passwordLogin.text.trim().toString()
                .isEmpty())

        buttonLogin.setOnClickListener {
            if (phone.unMaskedText.toString().isEmpty() || passwordLogin.text.trim().toString()
                    .isEmpty()
            ) {
                viewModel.changeButtonStatusLiveData.value = false
                showToast("Maydon Bo`sh")
            } else {

                viewModel.changeButtonStatusLiveData.value = true
                bundle.putString("login", "login")

                viewModel.login(
                    password = passwordLogin.text.toString(),
                    phone = "+998" + phone.unMaskedText.toString()
                )
                bundle.putString("login", "login")
                bundle.putSerializable(
                    "data",
                    AuthRequest.LoginRequest(
                        passwordLogin.text.toString(), "+998${phone.unMaskedText.toString()}"
                    )
                )
            }
        }
    }
}