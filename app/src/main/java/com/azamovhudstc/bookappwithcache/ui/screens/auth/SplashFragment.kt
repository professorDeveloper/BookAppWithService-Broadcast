package com.azamovhudstc.bookappwithcache.ui.screens.auth

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.azamovhudstc.bookappwithcache.R
import com.azamovhudstc.bookappwithcache.data.local.sharedpref.AppReference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var sharedPreferences=AppReference(requireContext())

        lifecycleScope.launch {
            delay(1500)
            if (sharedPreferences.getToken().toString().isEmpty()) {
                findNavController().navigate(
                    R.id.loginFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                )

            }
            else {
                findNavController().navigate(
                    R.id.homeFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.splashFragment, true).build()
                )
            }
        }



    }
}