package com.azamovhudstc.bookappwithcache.ui.screens.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.AuthRequest
import com.azamovhudstc.bookappwithcache.utils.showToast
import com.azamovhudstc.bookappwithcache.utils.state
import com.azamovhudstc.bookappwithcache.viewmodel.imp.RegisterViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.buttonRegister
@AndroidEntryPoint

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val viewModel by viewModels<RegisterViewModelImpl>()
    lateinit var bundle: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = Bundle()
        viewModel.messageLIveData.observe(this) {
            showToast(it)
        }
        viewModel.notConnectionLiveData.observe(this) {
            showToast("No internet Connected")

        }
        viewModel.backLoginLiveData.observe(this) {
            findNavController().popBackStack()
        }
        viewModel.changeButtonStatusLiveData.observe(this) {
            buttonRegister.isEnabled = it
        }

        viewModel.progressLiveData.observe(this,progressObserver)
        viewModel.openVerifyScreenLiveData.observe(this) {
            findNavController().navigate(
                R.id.verifyFragment,
                bundle,
                NavOptions.Builder().setPopUpTo(R.id.registerFragment, true).build()
            )

        }
    }
    private val progressObserver = Observer<Boolean> { if(it) progress.show() else progress.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backBtn.setOnClickListener {
            viewModel.backLogin()
        }
        buttonRegister.setOnClickListener {
            if (inputFirstName.text.toString().isEmpty() || lastName.text?.trim().toString()
                    .isEmpty() || lastName.text?.trim().toString().isEmpty()
            ) {
                showToast("Maydonlarni To`ldiring")
            } else {
                bundle.putString("login", "register")
                viewModel.register(
                    inputFirstName.text.toString(),
                    password.text.toString(),
                    "+998" + inputPhone.unMaskedText.toString(),
                    lastName.text.toString()
                )
                bundle.putSerializable(
                    "data",
                    AuthRequest.RegisterRequest(
                        inputFirstName.text.toString(),
                        lastName.text.toString(),
                        password.text.toString(),
                        "+998${inputPhone.unMaskedText.toString()}"
                    )
                )

            }
        }
    }
}