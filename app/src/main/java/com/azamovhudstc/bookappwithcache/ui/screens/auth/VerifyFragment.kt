package com.azamovhudstc.bookappwithcache.ui.screens.auth

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import com.azamovhudstc.bookappwithcache.data.remote.request.auth.AuthRequest
import com.azamovhudstc.bookappwithcache.utils.showToast
import com.azamovhudstc.bookappwithcache.utils.state
import com.azamovhudstc.bookappwithcache.viewmodel.VerifyScreenViewModel
import com.azamovhudstc.bookappwithcache.viewmodel.imp.RegisterViewModelImpl
import com.azamovhudstc.bookappwithcache.viewmodel.imp.VerifyScreenViewModelImp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_verify.*
import java.io.Serializable
import java.text.DecimalFormat

@AndroidEntryPoint

class VerifyFragment : Fragment(R.layout.fragment_verify) {
    lateinit var data: Serializable
    lateinit var timer: CountDownTimer
    private val viewModel: VerifyScreenViewModel by viewModels<VerifyScreenViewModelImp>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openHomeScreenLiveData.observe(this) {
            findNavController().navigate(
                R.id.homeFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.verifyFragment, true).build()
            )
        }
        viewModel.notConnectionLiveData.observe(this) {
            showToast("No connection !")
        }
        viewModel.errorMessageLiveData.observe(this) {
            showToast(it)
        }
        viewModel.progressLiveData.observe(this, progressObserver)
    }

    private val progressObserver = Observer<Boolean> { if(it) verfied_progress.show() else verfied_progress.hide()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val isChecker = arguments?.getString("login")
        button_send.setOnClickListener {
            if (isChecker == "login") {
                if (getOtp.text.toString().isEmpty() || getOtp.text?.length!! > 6) {
                    showToast("Iltimos kod 6 bo`lishi kerak ")
                } else {

                    viewModel.verifySign(
                        getOtp.text.toString(),
                    )
                    resent_code.visibility = View.INVISIBLE

                }
            } else {
                if (getOtp.text.toString().isEmpty() || getOtp.text?.length!! > 6) {
                    showToast("Iltimos kod 6 bo`lishi kerak ")
                } else {
                    viewModel.verifySignUp(getOtp.text.toString())

                }
            }
        }

        timer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                var f = DecimalFormat("00");
                val hour = millisUntilFinished / 3600000 % 24
                val min = millisUntilFinished / 60000 % 60
                val sec = millisUntilFinished / 1000 % 60
                countDown.text = f.format(hour) + ":" + f.format(min) + ":" + f.format(sec)

            }

            override fun onFinish() {

                resent_code.visibility = View.VISIBLE
                countDown.text = "00:00:00"


            }
        }
        timer.start()

        if (isChecker == "login") {
            data = arguments?.getSerializable("data") as AuthRequest.LoginRequest
            var phone = (data as AuthRequest.LoginRequest).phone
            val phoneNumber = phone!!.substring(phone.length - 4, phone.length - 2)
            val phoneNumberTwo = phone.substring(phone.length - 2, phone.length)
            getPhones.text =
                "+998(##)-###-$phoneNumber-$phoneNumberTwo telefon raqamga Tasdiqlash Kodi Yubordik"

        } else {
            data = arguments?.getSerializable("data") as AuthRequest.RegisterRequest

            var phone = (data as AuthRequest.RegisterRequest).phone
            val phoneNumber = phone!!.substring(phone.length - 4, phone.length - 2)
            val phoneNumberTwo = phone.substring(phone.length - 2, phone.length)
            getPhones.text =
                "+998(##)-###-$phoneNumber-$phoneNumberTwo telefon raqamga Tasdiqlash Kodi Yubordik"

        }
        resent_code.setOnClickListener {
            if (isChecker == "login") {
                viewModel.login(
                    (data as AuthRequest.LoginRequest).password,
                    (data as AuthRequest.LoginRequest).phone
                )
                timer.start()
                resent_code.visibility = View.INVISIBLE

            } else {
                viewModel.register(
                    (data as AuthRequest.RegisterRequest).firstName,
                    (data as AuthRequest.RegisterRequest).password,
                    (data as AuthRequest.RegisterRequest).phone,
                    (data as AuthRequest.RegisterRequest).lastName,
                )
                timer.start()
                resent_code.visibility = View.INVISIBLE


            }

        }

        resent_code.visibility = View.GONE
    }

    override fun onDestroyView() {
        timer.cancel()
        super.onDestroyView()

    }
}