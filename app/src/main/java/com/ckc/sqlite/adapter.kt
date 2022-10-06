package com.ckc.sqlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ckc.sqlite.databinding.ItemBinding

class adapter(val list :ArrayList<Modell>) : RecyclerView.Adapter<adapter.recylerAdapterHolder>() {
    class recylerAdapterHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recylerAdapterHolder {
        val holder = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return recylerAdapterHolder(holder)
    }

    override fun onBindViewHolder(holder: recylerAdapterHolder, position: Int) {
        holder.binding.textView.text = list[position].name +"  " +list[position].id



    }

    override fun getItemCount(): Int {
        return list.size
    }
}