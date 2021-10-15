package com.clover.cloverricknmorty.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.clover.cloverricknmorty.R
import com.clover.cloverricknmorty.data.model.CharacterList

class MainAdapter(val characters: ArrayList<CharacterList>): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView
        val textView2: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView1 = itemView.findViewById(R.id.text)
            textView2 = itemView.findViewById(R.id.text2)
            imageView = itemView.findViewById(R.id.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView1.text = characters[position].name
        holder.textView2.text = characters[position].gender
        Glide.with(holder.imageView.context)
            .load(characters[position].image)
            .into(holder.imageView)
    }

    fun addCharacters(characters: List<CharacterList>) {
        this.characters.apply {
            clear()
            addAll(characters)
        }
    }
}