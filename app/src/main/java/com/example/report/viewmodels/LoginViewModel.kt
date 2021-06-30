package com.example.report.viewmodels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.report.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class LoginViewModel : ViewModel() {

    lateinit var usuarioLogueado : Usuario
    val db = Firebase.firestore

    fun setUser(uid: String) {

        val userRef = db.collection("users").document(uid)

        userRef
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    usuarioLogueado = dataSnapshot.toObject<Usuario>()!!
                    Log.d("USUARIO LOGUEADO = ", usuarioLogueado.username)
                } else {
                    Log.d("Test", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("Test", "get failed with ", exception)
            }
    }

    fun verificarCamposCompletos(userView: TextView, passwordView: TextView): Boolean {
        return (userView.length() == 0 || passwordView.length() == 0)
    }
}