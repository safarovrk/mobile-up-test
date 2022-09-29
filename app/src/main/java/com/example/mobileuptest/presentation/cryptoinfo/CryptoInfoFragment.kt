package com.example.mobileuptest.presentation.cryptoinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileuptest.R


class CryptoInfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println(CryptoInfoFragmentArgs.fromBundle(requireArguments()).cryptocurrencyId)
        return inflater.inflate(R.layout.fragment_crypto_info, container, false)


    }

}