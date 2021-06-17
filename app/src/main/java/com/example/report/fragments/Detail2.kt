package com.example.report.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.report.R
import com.example.report.adapters.ComentarioListAdapter
import com.example.report.viewmodels.ComentariosViewModel
import com.example.report.viewmodels.DetailsViewModel

@Suppress("DEPRECATION")
class Detail2 : Fragment() {
    lateinit var v: View
    lateinit var recComentarios : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var viewModelDetails: DetailsViewModel
    lateinit var viewModelComentarios: ComentariosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail2, container, false)
        recComentarios = v.findViewById(R.id.rec_comentarios)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelComentarios = ViewModelProvider(requireActivity()).get(ComentariosViewModel::class.java)
        viewModelDetails = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        recComentarios.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recComentarios.layoutManager = linearLayoutManager

        viewModelComentarios.comentariosLive.observe(viewLifecycleOwner, Observer { result ->
            recComentarios.adapter = ComentarioListAdapter(result, requireContext())
        })
    }
}