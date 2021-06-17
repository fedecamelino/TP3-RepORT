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
        private val INGRESO = "https://artes.unc.edu.ar/wp-content/blogs.dir/2/files/sites/2/Ingres2020_SliderWEB.png"
        private val MATERIA = "https://destinonegocio.com/wp-content/uploads/2018/11/14-sistema-informatico.jpg"
        private val ADMINISTRATIVO = "https://definicion.xyz/wp-content/uploads/2020/11/principios-y-elementos-de-la-administracion-700x438.jpg"
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