package com.example.surrealxplore.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.surrealxplore.data.entity.MuseoEntity
import com.example.surrealxplore.databinding.MuseoItemBinding
import com.example.surrealxplore.interfaces.onClick

class MuseosAdapter(val context: Context, private val onClick: onClick) : ListAdapter<MuseoEntity, MuseosAdapter.ViewHolder>(
    DiffCallback
) {

    companion object DiffCallback : DiffUtil.ItemCallback<MuseoEntity>() {
        override fun areItemsTheSame(oldItem: MuseoEntity, newItem: MuseoEntity): Boolean {
            return oldItem.nombre == newItem.nombre
        }

        override fun areContentsTheSame(oldItem: MuseoEntity, newItem: MuseoEntity): Boolean {
            return oldItem.nombre == newItem.nombre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = MuseoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val museo = getItem(position)
        holder.bind(museo)
    }


    inner class ViewHolder(private val binding: MuseoItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(museo: MuseoEntity) {

            binding.tvNombreMuseo.text = museo.nombre
            binding.tvUbicacionMuseo.text = museo.calle + museo.colonia + museo.cp
            binding.ivMuseo.setImageResource(museo.imagen[0])

            binding.lyMuseo.setOnClickListener {
                onClick.descripcionMuseo(museo.nombre)
            }

        }
    }

}