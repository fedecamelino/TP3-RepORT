package com.example.report.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.report.R
import com.example.report.entities.Comentario
import com.example.report.entities.Tema
import com.example.report.viewmodels.ComentariosViewModel
import com.example.report.viewmodels.DetailsViewModel
import com.example.report.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout

@Suppress("DEPRECATION")
class Detail1 : Fragment() {

    lateinit var temaView : TextView
    lateinit var userView : TextView
    lateinit var descripcionView : TextView
    lateinit var comentarioView : TextView
    lateinit var inputComentarioView : TextView
    lateinit var buttonView : Button
    lateinit var v : View
    lateinit var viewModelDetails: DetailsViewModel
    lateinit var viewModelComentarios: ComentariosViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        v = inflater.inflate(R.layout.fragment_detail1, container, false)
        temaView = v.findViewById(R.id.txtTema)
        userView = v.findViewById(R.id.txtUser)
        descripcionView = v.findViewById(R.id.txtDescripcion)
        comentarioView = v.findViewById(R.id.txtNuevoComentario)
        inputComentarioView = v.findViewById(R.id.nuevoComentarioInput)
        buttonView = v.findViewById(R.id.buttonComentar)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelDetails = ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
        viewModelComentarios = ViewModelProvider(requireActivity()).get(ComentariosViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("My Preferences", Context.MODE_PRIVATE)

        temaView.text = viewModelDetails.temaActual.name
        userView.text = viewModelDetails.temaActual.userCreator
        descripcionView.text = viewModelDetails.temaActual.description

        buttonView.setOnClickListener {
            if (viewModelComentarios.validarNuevoComentario(inputComentarioView)) {
                val nuevoComentario = Comentario(
                    inputComentarioView.text.toString(),
                    viewModelDetails.posicionTema,
                    sharedPref.getString("USERNAME", "USERNAME")!!
                )

                Snackbar.make(v, Comentario.Constants.OUTPUT_CREADO, Snackbar.LENGTH_LONG).show()
                viewModelComentarios.actualizarComentarios(nuevoComentario)
                inputComentarioView.setText("")
            }
            else {
                Snackbar.make(v, Comentario.Constants.OUTPUT_ERROR, Snackbar.LENGTH_SHORT).show()
            }
        }

    }
}