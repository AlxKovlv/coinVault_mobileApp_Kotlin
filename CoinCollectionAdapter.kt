package com.example.coinvault_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CoinCollectionAdapter(private val coinCollectionList: List<Coin>) :
    RecyclerView.Adapter<CoinCollectionAdapter.CoinCollectionViewHolder>() {

    inner class CoinCollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewCoinName: TextView = itemView.findViewById(R.id.textViewCoinName)
        private val textViewCoinIssuer: TextView = itemView.findViewById(R.id.textViewCoinIssuer)
        private val textViewCoinYear: TextView = itemView.findViewById(R.id.textViewCoinYear)
        private val buttonDeleteCoin: ImageButton = itemView.findViewById(R.id.buttonDeleteCoin)

        fun bind(coin: Coin) {
            textViewCoinName.text = coin.coinName
            textViewCoinIssuer.text = coin.issuer
            textViewCoinYear.text = coin.year.toString()

            buttonDeleteCoin.setOnClickListener {
                // Handle delete coin button click
                deleteCoin(coin)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinCollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin_collection, parent, false)
        return CoinCollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoinCollectionViewHolder, position: Int) {
        val coin = coinCollectionList[position]
        holder.bind(coin)
    }

    override fun getItemCount(): Int {
        return coinCollectionList.size
    }

    private fun deleteCoin(coin: Coin) {
        // TODO: Implement logic to delete the coin from the collection
    }
}
