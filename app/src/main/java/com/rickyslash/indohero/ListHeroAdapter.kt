package com.rickyslash.indohero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rickyslash.indohero.databinding.LayoutItemHeroBinding
import com.bumptech.glide.Glide

class ListHeroAdapter(private val listHero: ArrayList<Hero>): RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: LayoutItemHeroBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHeroAdapter.ListViewHolder {
        val binding = LayoutItemHeroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListHeroAdapter.ListViewHolder, position: Int) {
        val (name, desc, photo) = listHero[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemName.text = desc

        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

}