package com.example.synctrue.ui.entregas

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.synctrue.R
import com.example.synctrue.models.Entrega
import kotlinx.android.synthetic.main.item_lista_entrega.view.*

class EntregaListAdapter(private val entregas: List<Entrega>,
                         private val context: Context) : Adapter<EntregaListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(entrega: Entrega){
            val id_entrega = itemView.entrega_id
            val valor = itemView.valor
            val endereco = itemView.endereco

            id_entrega.text = entrega.t32_id.toString()
            valor.text = entrega.t32_valor.toString()
            endereco.text = entrega.t32_endereco
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =  LayoutInflater.from(context).inflate(R.layout.item_lista_entrega, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entregas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entrega = entregas[position]
        holder.bindView(entrega)
    }
}

