package com.example.report.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.report.R
import com.example.report.entities.Comentario

class ComentarioListAdapter(
    private var comentariosList: MutableList<Comentario>,
    private var contexto: Context
) : RecyclerView.Adapter<ComentarioListAdapter.ComentarioHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComentarioHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comentario, parent, false)
        return (ComentarioHolder(view))
    }

    override fun getItemCount(): Int {
        return comentariosList.size
    }

    override fun onBindViewHolder(holder: ComentarioHolder, position: Int) {
        holder.setNameComentario(comentariosList[position].description)
        holder.setNameUser(comentariosList[position].userCreator)
        holder.getCardLayout()
    }


    class ComentarioHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val view: View = v

        fun setNameComentario(name: String) {
            val txt: TextView = view.findViewById(R.id.txtComentario)
            txt.text = name
        }

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardComment)
        }

        fun setNameUser (name: String) {
            val txt: TextView = view.findViewById(R.id.txtUserComentario)
            txt.text = name
        }
    }
}