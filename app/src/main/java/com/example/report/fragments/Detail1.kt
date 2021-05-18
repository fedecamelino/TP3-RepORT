package com.example.report.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.report.R

class Detail1 : Fragment() {

    lateinit var temaView : TextView
    lateinit var descripcionView : TextView
    lateinit var v : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail1, container, false)
        temaView = v.findViewById(R.id.txtTema)
        descripcionView = v.findViewById(R.id.txtDescripcion)
        return v
    }

    override fun onStart() {
        super.onStart()

        //temaView.text = DetailsArgs.fromBundle(requireArguments()).temaClick
    }
}