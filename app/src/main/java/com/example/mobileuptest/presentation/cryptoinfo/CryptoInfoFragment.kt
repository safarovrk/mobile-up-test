package com.example.mobileuptest.presentation.cryptoinfo

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.bumptech.glide.Glide
import com.example.mobileuptest.R
import com.example.mobileuptest.databinding.FragmentCryptoInfoBinding


class CryptoInfoFragment : Fragment() {

    lateinit var binding: FragmentCryptoInfoBinding
    private val viewModel: CryptoInfoViewModel by navGraphViewModels(R.id.nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.nameData.value = CryptoInfoFragmentArgs.fromBundle(requireArguments()).cryptocurrencyName
        viewModel.idData.value = CryptoInfoFragmentArgs.fromBundle(requireArguments()).cryptocurrencyId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoInfoBinding.inflate(inflater)
        setListeners()
        setObservers()
        binding.fragmentCryptoInfoToolbar.title = viewModel.nameData.value
        binding.fragmentCryptoInfoProgress.visibility = View.VISIBLE
        binding.fragmentCryptoInfoErrorPanel.visibility = View.GONE
        binding.fragmentCryptoInfoInfoScrollView.visibility = View.GONE
        loadData()
        return binding.root
    }

    private fun setListeners() {
        binding.fragmentCryptoInfoToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.fragmentCryptoInfoTryButton.setOnClickListener {
            loadData()
        }
    }

    private fun setObservers() {
        viewModel.cryptoInfoModel.observe(viewLifecycleOwner) {
            if (it.cryptoInfo?.description?.en != null) {
                binding.fragmentCryptoInfoDescriptionTxt.movementMethod = LinkMovementMethod.getInstance()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.fragmentCryptoInfoDescriptionTxt.text = Html.fromHtml(it.cryptoInfo.description.en, Html.FROM_HTML_MODE_COMPACT)
                }
            }
            if (it.cryptoInfo?.image?.large != null) {
                Glide.with(requireActivity()).load(it.cryptoInfo.image.large)
                    .into(binding.listItemCryptoInfoImg)
            }
            if (it.cryptoInfo?.categories != null) {
                binding.fragmentCryptoInfoCategoriesTxt.text = TextUtils.join(", ",
                    it.cryptoInfo.categories
                )
            }
            binding.fragmentCryptoInfoProgress.visibility = View.GONE
            binding.fragmentCryptoInfoErrorPanel.visibility = View.GONE
            binding.fragmentCryptoInfoInfoScrollView.visibility = View.VISIBLE
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            binding.fragmentCryptoInfoProgress.visibility = View.GONE
            binding.fragmentCryptoInfoInfoScrollView.visibility = View.GONE
            binding.fragmentCryptoInfoErrorPanel.visibility = View.VISIBLE
        }
    }

    private fun loadData() {
        viewModel.idData.value?.let { viewModel.loadData(it) }
        binding.fragmentCryptoInfoErrorPanel.visibility = View.GONE
        binding.fragmentCryptoInfoInfoScrollView.visibility = View.GONE
        binding.fragmentCryptoInfoProgress.visibility = View.VISIBLE
    }

}