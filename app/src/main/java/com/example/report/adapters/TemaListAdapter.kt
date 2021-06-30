package com.example.report.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.report.R
import com.example.report.entities.Tema

class TemaListAdapter(
    private var temasList: MutableList<Tema>,
    private var contexto: Context,
    val onItemClick: (Int) -> Boolean
) : RecyclerView.Adapter<TemaListAdapter.TemaHolder>() {

    companion object {
        private val INGRESO = "https://ibb.co/48dDsHX"
        private val MATERIA = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTvADSD9QV9PHEjc8A8IyOrBkiMgZDy4iDgCMmlrUV8JvDg0sVj9aZbKifbojPnwcdn1ow&usqp=CAU"
        private val ADMINISTRATIVO = "https://100funciones.com/wp-content/uploads/2019/01/funciones-de-la-administraci%C3%B3n-2.jpg"
        private val EXPERIENCIA = "https://i.pinimg.com/236x/f6/20/8a/f6208af20256c011ef2f790d085e97d5.jpg"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemaHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.item_tema, parent, false)
        return (TemaHolder(view))
    }

    override fun getItemCount(): Int {
        return temasList.size
    }

    fun setData(newData: ArrayList<Tema>) {
        this.temasList = newData
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TemaHolder, position: Int) {
        holder.setName(temasList[position].name)

        holder.getCardLayout().setOnClickListener() {
            onItemClick(position)
        }

        val url = if (temasList[position].type == Tema.Constants.ingreso) INGRESO
        else if (temasList[position].type == Tema.Constants.materia) MATERIA
        else if (temasList[position].type == Tema.Constants.experiencia) EXPERIENCIA
        else ADMINISTRATIVO

            Glide
              .with(contexto)
              .load(url)
              .into(holder.getImageView());
    }

    class TemaHolder(v: View) : RecyclerView.ViewHolder(v) {

        private val view: View = v

        fun setName(name: String) {
            val txt: TextView = view.findViewById(R.id.txtComentario)
            txt.text = name
        }

        fun getCardLayout (): CardView {
            return view.findViewById(R.id.cardComment)
        }

        fun getImageView () : ImageView {
           return view.findViewById(R.id.imgItem)
        }
    }
}