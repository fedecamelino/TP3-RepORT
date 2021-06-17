package com.example.report.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.report.R
import com.example.report.viewmodels.DetailsViewModel
import com.example.report.viewmodels.LoginViewModel

@Suppress("DEPRECATION")
class Detail1 : Fragment() {

    lateinit var temaView : TextView
    lateinit var userView : TextView
    lateinit var descripcionView : TextView
    lateinit var v : View
    lateinit var viewModelDetails: DetailsViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail1, container, false)
        temaView = v.findViewById(R.id.txtTema)
        userView = v.findViewById(R.id.txtUser)
        descripcionView = v.findViewById(R.id.txtDescripcion)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelDetails = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        temaView.text = viewModelDetails.temaActual.name
        userView.text = viewModelDetails.temaActual.userCreator
        descripcionView.text = viewModelDetails.temaActual.description
    }
}