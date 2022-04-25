package com.example.shark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Call

class SharkAdapter(private var items: List<Deal>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SharkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_shark, parent, false)
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SharkViewHolder ->{
                holder.bind(items[position])
            }
        }
    }
    override fun getItemCount(): Int {
        return items.size
    }
    fun setDataSet(Deal: List<Deal>){
        this.items = Deal
    }
    class SharkViewHolder constructor(
        itemView : View
    ): RecyclerView.ViewHolder(itemView){
        private val title = itemView.findViewById<TextView>(R.id.title)
        private val gameID = itemView.findViewById<TextView>(R.id.gameID)
        private val normalPrice = itemView.findViewById<TextView>(R.id.normalPrice)
        private val salePrice = itemView.findViewById<TextView>(R.id.salePrice)
        private val savings = itemView.findViewById<TextView>(R.id.savings)
        private val thumb = itemView.findViewById<ImageView>(R.id.thumb)

        fun bind(deal: Deal){
            title.text = deal.title
            gameID.text = deal.gameID.toString()
            normalPrice.text = deal.normalPrice.toString()
            salePrice.text = deal.salePrice.toString()
            savings.text = deal.savings.toString()

            Glide.with(itemView.context)
                .load(deal.thumb)
                .into(thumb)
        }
    }
}