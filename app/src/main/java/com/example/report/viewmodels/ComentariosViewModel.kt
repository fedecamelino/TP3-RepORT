package com.example.report.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.report.entities.Comentario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ComentariosViewModel: ViewModel() {
    val db = Firebase.firestore
    var comentarios: MutableList<Comentario> = ArrayList<Comentario>()
    var comentariosLive = MutableLiveData<MutableList<Comentario>>() //MutableLiveData<List<Comentario>>()

    fun initComentarios(idTema : Int) {

        var comment : Comentario
        comentarios.clear()

        Log.d("ENTRO AL INITCOMENT, IdTema = ", idTema.toString())
        db.collection("comentarios")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot != null) {
                    Log.d("ENTRO AL IF", "-----------")
                    for (comentario in snapshot) {
                        comment = comentario.toObject()
                        Log.d("Comentario ----------> ", comment.description)
                        if (comment.idTheme == idTema) {
                            comentarios.add(comment)
                        }
                    }
                    comentariosLive.value = comentarios
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}