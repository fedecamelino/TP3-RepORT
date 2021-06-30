package com.example.report.viewmodels

import android.content.ContentValues
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.report.entities.Tema
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ListTemasViewModel : ViewModel() {
    val db = Firebase.firestore
    var temas: MutableList<Tema> = ArrayList<Tema>()
    var temasLive = MutableLiveData<MutableList<Tema>>() //MutableLiveData<List<Tema>>()
    var flagInit : Boolean = true

    fun initTemas() {

        if (flagInit) {
            db.collection("temas")
                .get()
                .addOnSuccessListener { snapshot ->
                    if (snapshot != null) {
                        for (tema in snapshot) {
                            temas.add(tema.toObject())
                        }
                        temasLive.value = temas
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
        }

        flagInit = false
    }

    fun actualizarTemas(nuevoTema : Tema) {

        val indiceNuevoTema : Int = temas.size
        temas.add(indiceNuevoTema, nuevoTema)
        db.collection("temas").document(indiceNuevoTema.toString()).set(nuevoTema)
        temasLive.value = temas
        Log.d("SPINNER: ", nuevoTema.type)
    }

    fun validarNuevoTema(nombreTema : TextView, descripcionTema : TextView) : Boolean {
        return nombreTema.text.toString().length > 0 && descripcionTema.text.toString().length > 0
    }
}