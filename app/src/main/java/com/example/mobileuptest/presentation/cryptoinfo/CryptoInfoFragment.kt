package com.example.mobileuptest.presentation.cryptoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobileuptest.R
import com.example.mobileuptest.databinding.FragmentCryptoInfoBinding


class CryptoInfoFragment : Fragment() {

    lateinit var binding: FragmentCryptoInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoInfoBinding.inflate(inflater)
        binding.fragmentCryptoInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}