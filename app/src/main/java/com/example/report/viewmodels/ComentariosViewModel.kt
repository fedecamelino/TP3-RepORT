package com.example.report.viewmodels

import android.content.ContentValues
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.report.entities.Comentario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ComentariosViewModel: ViewModel() {
    val db = Firebase.firestore
    var comentarios: MutableList<Comentario> = ArrayList<Comentario>()
    var comentariosLive = MutableLiveData<MutableList<Comentario>>()

    fun initComentarios(idTema : Int) {

        var comment : Comentario
        comentarios.clear()

        db.collection("comentarios")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot != null) {
                    for (comentario in snapshot) {
                        comment = comentario.toObject()
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

    fun validarNuevoComentario(comentario : TextView) : Boolean {
        return comentario.text.toString().length > 0
    }

    fun actualizarComentarios(nuevoComentario: Comentario) {
        comentarios.add(nuevoComentario)
        db.collection("comentarios").add(nuevoComentario)
        comentariosLive.value = comentarios
    }
}