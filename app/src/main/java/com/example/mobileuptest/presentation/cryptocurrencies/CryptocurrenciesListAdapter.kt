package com.example.mobileuptest.presentation.cryptocurrencies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobileuptest.R
import com.example.mobileuptest.databinding.ListItemCryptocurrencyBinding
import com.example.mobileuptest.domain.models.Cryptocurrency
import java.util.*


data class RecyclerViewItem(val cryptocurrency: Cryptocurrency?, val currency: String)

class CryptocurrenciesListAdapter(private val clickListener: CryptocurrencyClickListener) : RecyclerView.Adapter<CryptocurrenciesListAdapter.ViewHolder>() {

    companion object {
        const val USD = "USD"
        const val EUR = "EUR"
    }

    var currency = USD
    var data = listOf<Cryptocurrency?>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(RecyclerViewItem(item, currency), clickListener)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(private val binding: ListItemCryptocurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerViewItem, clickListener: CryptocurrencyClickListener) {
            binding.listItemCryptocurrency.setOnClickListener {
                clickListener.onClick(item)
            }
            if (item.cryptocurrency?.name != null) {
                binding.listItemCryptocurrencyCrypto.text =
                    item.cryptocurrency.name
            }
            if (item.cryptocurrency?.symbol != null) {
                binding.listItemCryptocurrencyCryptoShort.text =
                    item.cryptocurrency.symbol.uppercase(Locale.ROOT)
            }
            if (item.cryptocurrency?.current_price != null) {
                if (item.currency == USD) {
                    binding.listItemCryptocurrencyCryptoSum.text = itemView.context.getString(
                        R.string.crypto_usd,
                        item.cryptocurrency.current_price.toString()
                    )
                }
                if (item.currency == EUR) {
                    binding.listItemCryptocurrencyCryptoSum.text = itemView.context.getString(
                        R.string.crypto_eur,
                        item.cryptocurrency.current_price.toString()
                    )
                }
            }
            if (item.cryptocurrency?.price_change_percentage_24h != null) {
                if (item.cryptocurrency.price_change_percentage_24h < 0) {
                    binding.listItemCryptocurrencyCryptoPercent.text = itemView.context.getString(
                        R.string.crypto_percent_minus,
                        item.cryptocurrency.price_change_percentage_24h.toString()
                    )
                    binding.listItemCryptocurrencyCryptoPercent
                        .setTextColor(itemView.context.getColor(R.color.red_crypto_percent))
                } else if (item.cryptocurrency.price_change_percentage_24h > 0) {
                    binding.listItemCryptocurrencyCryptoPercent.text = itemView.context.getString(
                        R.string.crypto_percent_plus,
                        item.cryptocurrency.price_change_percentage_24h.toString()
                    )
                    binding.listItemCryptocurrencyCryptoPercent
                        .setTextColor(itemView.context.getColor(R.color.green_crypto_percent))
                } else {
                    binding.listItemCryptocurrencyCryptoPercent.text = itemView.context.getString(
                        R.string.crypto_percent_minus,
                        item.cryptocurrency.price_change_percentage_24h.toString()
                    )
                    binding.listItemCryptocurrencyCryptoPercent
                        .setTextColor(itemView.context.getColor(R.color.black_crypto_short))
                }
            }
            if (item.cryptocurrency?.image != null) {
                Glide.with(itemView.context).load(item.cryptocurrency.image)
                    .into(binding.listItemCryptocurrencyImg)
            }

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemCryptocurrencyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CryptocurrencyClickListener(val clickListener: (cryptocurrency: Cryptocurrency?) -> Unit) {
    fun onClick(cryptocurrency: RecyclerViewItem) {
        clickListener(cryptocurrency.cryptocurrency)
    }
}