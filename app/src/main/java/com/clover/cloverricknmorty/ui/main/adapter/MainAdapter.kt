package com.clover.cloverricknmorty.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.clover.cloverricknmorty.R
import com.clover.cloverricknmorty.data.model.CharacterList

class MainAdapter(
    val characters: ArrayList<CharacterList>,
    val itemClickListener: OnItemClickListener
    ): RecyclerView.Adapter<MainAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(character: CharacterList, clickListener: OnItemClickListener)
        {

            val textView1: TextView = itemView.findViewById(R.id.text_name)
            val textView2: TextView = itemView.findViewById(R.id.text_status)
            val textView3: TextView = itemView.findViewById(R.id.text_species)

            textView1.text = character.name
            textView2.text = character.status
            textView3.text = character.species

            itemView.setOnClickListener {
                clickListener.onItemClicked(character.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character, itemClickListener)
    }

    fun addCharacters(characters: List<CharacterList>) {
        this.characters.apply {
            clear()
            addAll(characters)
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(characterId: Int)
}
