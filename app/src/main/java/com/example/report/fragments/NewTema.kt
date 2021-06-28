package com.example.report.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.report.R
import com.example.report.entities.Tema
import com.example.report.viewmodels.ListTemasViewModel
import com.example.report.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
class NewTema : Fragment() {

    lateinit var nuevoTemaView : TextView
    lateinit var tituloView : TextView
    lateinit var ingreseTituloView : TextView
    lateinit var descripcionView : TextView
    lateinit var ingreseDescripcionView : TextView
    lateinit var tipoView: TextView
    lateinit var spinnerView : Spinner
    lateinit var btnCancel : View
    lateinit var btnCreate : Button
    lateinit var v: View
    var spinnerText : String = "MATERIA"
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var temasViewModel : ListTemasViewModel
    var listaTiposTemas = listOf(
                            Tema.Constants.materia,
                            Tema.Constants.ingreso,
                            Tema.Constants.experiencia,
                            Tema.Constants.admin)

    class Constants {
        companion object {
            val OUTPUT_CREADO = "Tema creado con éxito"
            val OUTPUT_ERROR = "Complete todos los campos"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_new_tema, container, false)
        nuevoTemaView = v.findViewById(R.id.nuevoTemaView)
        tituloView = v.findViewById(R.id.tituloTemaView)
        ingreseTituloView = v.findViewById(R.id.tituloInputView)
        descripcionView = v.findViewById(R.id.descripcionTemaView)
        ingreseDescripcionView = v.findViewById(R.id.descripcionInputView)
        tipoView = v.findViewById(R.id.tipoTemaView)
        spinnerView = v.findViewById(R.id.tipoTemaSpinner)
        btnCancel = v.findViewById(R.id.cancelButton)
        btnCreate = v.findViewById(R.id.createButton)

        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        //setHasOptionsMenu(true)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        temasViewModel = ViewModelProvider(requireActivity()).get(ListTemasViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("My Preferences", Context.MODE_PRIVATE)

        definirSpinner(spinnerView, listaTiposTemas, requireContext())
        spinnerView.setSelection(0, false)
        spinnerView.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Snackbar.make(v, listaTiposTemas[position], Snackbar.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                spinnerText = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        btnCancel.setOnClickListener {
            val action = NewTemaDirections.actionNewTemaToListTemas2()
            v.findNavController().navigate(action)
        }

        btnCreate.setOnClickListener {

            if (temasViewModel.validarNuevoTema(ingreseTituloView, ingreseDescripcionView)) {
                val nuevoTema = Tema(
                    ingreseTituloView.text.toString(),
                    spinnerText,
                    ingreseDescripcionView.text.toString(),
                    sharedPref.getString("USERNAME", "default")!!
                )

                Snackbar.make(v, Constants.OUTPUT_CREADO, Snackbar.LENGTH_LONG).show()
                temasViewModel.actualizarTemas(nuevoTema)

                val action = NewTemaDirections.actionNewTemaToListTemas2()
                v.findNavController().navigate(action)
            }
            else {
                Snackbar.make(v, Constants.OUTPUT_ERROR, Snackbar.LENGTH_SHORT).show()
            }
        }

    }

}

fun definirSpinner (spinner: Spinner, list : List<String>, context : Context)
{
    val adapter = ArrayAdapter(context,android.R.layout.simple_spinner_item, list)
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinner.adapter = adapter
}