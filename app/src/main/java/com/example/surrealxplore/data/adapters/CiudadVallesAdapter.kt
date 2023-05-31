package com.example.surrealxplore.data.adapters

import android.content.Context
import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.surrealxplore.data.entity.MuseoEntity
import com.example.surrealxplore.databinding.MuseoItemBinding

class CiudadVallesAdapter(val context: Context) : ListAdapter<MuseoEntity, CiudadVallesAdapter.ViewHolder>(
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

            /*binding.tvNombreServicio.text = servicio.nombre_servicio

            val decodeByte = Base64.decode(servicio.icon, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)

            binding.ivServicio.setImageBitmap(bitmap)

            with(binding.root){
                setOnClickListener {
                    Firebase.analytics.logEvent(listenerServicioFirebase, Bundle())
                    listener.showAccesoDirecto(servicio.nombre_servicio,servicio.informacion, servicio.url, servicio.webview) }
            }*/

        }
    }

}