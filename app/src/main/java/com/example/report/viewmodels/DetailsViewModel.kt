package com.example.report.viewmodels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.report.entities.Comentario
import com.example.report.entities.Tema
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DetailsViewModel: ViewModel() {
    val db = Firebase.firestore
    lateinit var temaActual: Tema
    var posicionTema: Int = 0
    var comentarios: MutableList<Comentario> = ArrayList<Comentario>()
    var comentariosLive = MutableLiveData<MutableList<Comentario>>()



    /*fun setTema(position: Int) {
        val path = position.toString()
        val docRef = db.collection("temas").document(path)

        docRef
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    tema = dataSnapshot.toObject<Tema>()
                    Log.d("Test", "DocumentSnapshot data: ${tema.toString()}")
                } else {
                    Log.d("Test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Test", "get failed with ", exception)
            }
    }*/
}