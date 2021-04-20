package com.example.recyclerview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.adapters.TemaListAdapter
import com.example.recyclerview.entities.Tema
import com.google.android.material.snackbar.Snackbar

class ListTemas : Fragment() {
    lateinit var v: View
    lateinit var recTemas : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    var temas : MutableList<Tema> = ArrayList<Tema>()
    private lateinit var temasListAdapter: TemaListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_list_temas, container, false)
        recTemas = v.findViewById(R.id.rec_temas)
        return v
    }

    companion object {
        fun newInstance() = ListTemas()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        temas.add(Tema("Parciales TP2", Tema.Constants.materia, "¿Fecha de parciales de Taller de Prog. 2?"))
        temas.add(Tema("Descuento materias", Tema.Constants.admin, "Si curso 2 materias, puedo pedir dto.?"))
        temas.add(Tema("Teoria CS", Tema.Constants.materia, "Necesito algún resumen de Calidad de Software"))
        temas.add(Tema("Cuota 2021", Tema.Constants.admin, "Cuanto vale la cuota actual de 2021?"))
        temas.add(Tema("Examen ingreso", Tema.Constants.ingreso, "Hay que dar examen de ingreso?"))
        temas.add(Tema("Libreta", Tema.Constants.admin, "Actualmente se usa la libreta de la ORT?"))
        temas.add(Tema("Nivel Ingles", Tema.Constants.ingreso, "Que tal el inglés que enseñan en ORT?"))

        recTemas.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recTemas.layoutManager = linearLayoutManager

        temasListAdapter = TemaListAdapter(temas, requireContext()) { x -> onItemClick(x) }

        recTemas.adapter = temasListAdapter
    }

    fun onItemClick (position : Int) : Boolean {
        val action = ListTemasDirections.actionListTemasToDetails(temas[position].name)
        v.findNavController().navigate(action)
        return true
    }
}