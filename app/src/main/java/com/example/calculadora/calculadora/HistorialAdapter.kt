package com.example.calculadora.calculadora

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculadora.calculadora.Database.Operacion
import com.example.calculadora.calculadora.databinding.ItemOperacionBinding

class HistorialAdapter(
    private val lista: List<Operacion>,
    private val onItemClick: (Operacion) -> Unit,
    private val onItemLongClick: (Operacion) -> Unit
) : RecyclerView.Adapter<HistorialAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemOperacionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOperacionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.binding.txtDescripcion.text = item.descripcion
        holder.binding.txtFecha.text = item.fecha
        
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
        
        holder.itemView.setOnLongClickListener {
            onItemLongClick(item)
            true
        }
    }

    override fun getItemCount(): Int = lista.size
}