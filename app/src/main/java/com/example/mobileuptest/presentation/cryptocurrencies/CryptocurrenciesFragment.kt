package com.example.mobileuptest.presentation.cryptocurrencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.size
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.example.mobileuptest.R
import com.example.mobileuptest.databinding.FragmentCryptocurrenciesBinding
import com.google.android.material.chip.Chip

class CryptocurrenciesFragment : Fragment() {

    private lateinit var binding: FragmentCryptocurrenciesBinding
    private val viewModel: CryptocurrenciesViewModel by navGraphViewModels(R.id.nav_graph)
    private val adapter =
        CryptocurrenciesListAdapter(CryptocurrencyClickListener { cryptocurrency ->
            if (cryptocurrency != null) {
                findNavController().navigate(
                    R.id.action_cryptocurrenciesFragment_to_cryptoInfoFragment,
                    bundleOf(
                        "cryptocurrencyId" to cryptocurrency.id,
                        "cryptocurrencyName" to cryptocurrency.name
                    )
                )
            }
        })
    private var lastCheckedId: Int = View.NO_ID

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptocurrenciesBinding.inflate(inflater)
        binding.fragmentCryptocurrenciesListRv.adapter = adapter
        setListeners()
        setObservers()
        chipStateInit()
        loadData()
        return binding.root
    }

    private fun setListeners() {
        binding.fragmentCryptocurrenciesTryButton.setOnClickListener {
            loadData()
        }
        binding.fragmentCryptocurrenciesChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            if (checkedIds[0] == View.NO_ID) {
                group.check(lastCheckedId)
                return@setOnCheckedStateChangeListener
            }
            lastCheckedId = checkedIds[0]
            viewModel.checkedChipValue.value =
                group.findViewById<Chip>(checkedIds[0]).text.toString()
        }
    }

    private fun setObservers() {
        viewModel.exception.observe(viewLifecycleOwner) {
            binding.fragmentCryptocurrenciesProgress.visibility = View.GONE
            binding.fragmentCryptocurrenciesErrorPanel.visibility = View.VISIBLE
        }
        viewModel.cryptocurrenciesModel.observe(viewLifecycleOwner) {
            if (viewModel.checkedChipValue.value != null) {
                adapter.currency = viewModel.checkedChipValue.value.toString()
            }
            adapter.data =
                viewModel.cryptocurrenciesModel.value?.cryptocurrencies ?: mutableListOf()
            if (adapter.data.isEmpty())
                binding.fragmentCryptocurrenciesListRv.visibility = View.GONE
            else {
                binding.fragmentCryptocurrenciesErrorPanel.visibility = View.GONE
                binding.fragmentCryptocurrenciesProgress.visibility = View.GONE
                binding.fragmentCryptocurrenciesListRv.visibility = View.VISIBLE
            }

        }
        viewModel.checkedChipValue.observe(viewLifecycleOwner) {
            loadData()
        }
    }

    private fun loadData() {
        viewModel.checkedChipValue.value?.let {
            binding.fragmentCryptocurrenciesErrorPanel.visibility = View.GONE
            binding.fragmentCryptocurrenciesListRv.visibility = View.GONE
            binding.fragmentCryptocurrenciesProgress.visibility = View.VISIBLE
            viewModel.loadData(it)
        }

    }

    private fun chipStateInit() {
        val chipGroup = binding.fragmentCryptocurrenciesChipGroup
        if (viewModel.checkedChipValue.value == null) {
            chipGroup.check(chipGroup.getChildAt(0).id)
            viewModel.checkedChipValue.value = chipGroup
                .findViewById<Chip>(chipGroup.getChildAt(0).id)
                .text.toString()
        } else {
            for (i in 0 until chipGroup.size) {
                if (chipGroup.findViewById<Chip>(chipGroup.getChildAt(i).id).text.toString()
                    == viewModel.checkedChipValue.value
                ) chipGroup.check(chipGroup.getChildAt(i).id)
            }
        }
    }
}