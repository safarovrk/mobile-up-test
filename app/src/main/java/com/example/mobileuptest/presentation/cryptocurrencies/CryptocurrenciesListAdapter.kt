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


data class RecyclerViewItem(val cryptocurrency: Cryptocurrency?)

class CryptocurrenciesListAdapter : RecyclerView.Adapter<CryptocurrenciesListAdapter.ViewHolder>() {

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
        holder.bind(RecyclerViewItem(item))
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder private constructor(private val binding: ListItemCryptocurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecyclerViewItem) {
            if (item.cryptocurrency?.name != null) {
                binding.listItemCryptocurrencyCrypto.text =
                    item.cryptocurrency.name
            }
            if (item.cryptocurrency?.symbol != null) {
                binding.listItemCryptocurrencyCryptoShort.text =
                    item.cryptocurrency.symbol.uppercase(Locale.ROOT)
            }
            if (item.cryptocurrency?.current_price != null) {
                //if (eur usd)
                /*binding.listItemCryptocurrencyCryptoSum.text =
                    item.cryptocurrency.current_price.toString()*/
                binding.listItemCryptocurrencyCryptoSum.text = itemView.context.getString(
                    R.string.crypto_usd,
                    item.cryptocurrency.current_price.toString()
                )
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
                }
                else {
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