package com.example.report.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.report.R
import com.example.report.adapters.TemaListAdapter
import com.example.report.viewmodels.DetailsViewModel
import com.example.report.viewmodels.ListTemasViewModel
import androidx.lifecycle.Observer
import com.example.report.entities.Tema
import com.example.report.viewmodels.LoginViewModel

@Suppress("DEPRECATION")
class ListTemas : Fragment() {

    lateinit var v: View
    lateinit var recTemas : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var btnFloating : View
    lateinit var viewModelTemas: ListTemasViewModel
    lateinit var viewModelDetails: DetailsViewModel
    lateinit var viewModelLogin: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_list_temas, container, false)
        btnFloating = v.findViewById(R.id.floatingButton)
        recTemas = v.findViewById(R.id.rec_temas)
        return v
    }

    companion object {
        fun newInstance() = ListTemas()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelTemas = ViewModelProvider(requireActivity()).get(ListTemasViewModel::class.java)
        viewModelDetails = ViewModelProvider(requireActivity()).get(DetailsViewModel:: class.java)
        viewModelLogin = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        viewModelTemas.initTemas()
    }

    override fun onStart() {
        super.onStart()

        //Log.d("-----------EN LIST TEMAS------------", viewModelLogin.usuarioLogueado.usuario)
        recTemas.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recTemas.layoutManager = linearLayoutManager

        viewModelTemas.temasLive.observe(viewLifecycleOwner, Observer { result ->
            recTemas.adapter = TemaListAdapter(result, requireContext()) { x -> onItemClick(x) }
        })

        btnFloating.setOnClickListener {
            val action = ListTemasDirections.actionListTemasToNewTema()
            v.findNavController().navigate(action)
        }
    }

    fun onItemClick (posicion : Int) : Boolean {
        viewModelDetails.posicionTema = posicion
        val action = ListTemasDirections.actionListTemasToDetails(viewModelTemas.temas[posicion])
        v.findNavController().navigate(action)
        return true
    }
}