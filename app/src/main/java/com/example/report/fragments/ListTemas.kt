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
import com.example.report.viewmodels.ListTemasViewModel

class ListTemas : Fragment() {
    lateinit var v: View
    lateinit var recTemas : RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var temasListAdapter: TemaListAdapter
    private lateinit var viewModel: ListTemasViewModel

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
        viewModel = ViewModelProvider(requireActivity()).get(ListTemasViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModel.initTemas()
        recTemas.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        recTemas.layoutManager = linearLayoutManager
        temasListAdapter = TemaListAdapter(viewModel.temas, requireContext()) { x -> onItemClick(x) }
        recTemas.adapter = temasListAdapter
    }

    fun onItemClick (position : Int) : Boolean {
        val action = ListTemasDirections.actionListTemasToDetails()
        v.findNavController().navigate(action)
        return true
    }
}