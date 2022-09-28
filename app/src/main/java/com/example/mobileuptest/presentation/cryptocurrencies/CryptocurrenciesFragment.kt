package com.example.mobileuptest.presentation.cryptocurrencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.navGraphViewModels
import com.example.mobileuptest.R
import com.example.mobileuptest.databinding.FragmentCryptocurrenciesBinding

class CryptocurrenciesFragment : Fragment() {

    lateinit var binding: FragmentCryptocurrenciesBinding
    private val viewModel: CryptocurrenciesViewModel by navGraphViewModels(R.id.nav_graph)
    private val adapter = CryptocurrenciesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptocurrenciesBinding.inflate(inflater)
        binding.fragmentCryptocurrenciesListRv.adapter = adapter
        setListeners()
        setObservers()
        viewModel.loadData()
        binding.fragmentCryptocurrenciesProgress.visibility = View.VISIBLE
        return binding.root
    }

    private fun setListeners() {
        binding.fragmentCryptocurrenciesTryButton.setOnClickListener {
            binding.fragmentCryptocurrenciesErrorPanel.visibility = View.GONE
            binding.fragmentCryptocurrenciesProgress.visibility = View.VISIBLE
            viewModel.loadData()
        }
    }

    private fun setObservers() {
        viewModel.exception.observe(viewLifecycleOwner) {
            binding.fragmentCryptocurrenciesProgress.visibility = View.GONE
            binding.fragmentCryptocurrenciesErrorPanel.visibility = View.VISIBLE
        }
        viewModel.cryptocurrenciesModel.observe(viewLifecycleOwner) {
            adapter.data =
                viewModel.cryptocurrenciesModel.value?.cryptocurrencies ?: mutableListOf()
            if (adapter.data.isEmpty())
                binding.fragmentCryptocurrenciesListRv.visibility = View.GONE
            else {
                binding.fragmentCryptocurrenciesProgress.visibility = View.GONE
                binding.fragmentCryptocurrenciesListRv.visibility = View.VISIBLE
            }

        }
    }

}