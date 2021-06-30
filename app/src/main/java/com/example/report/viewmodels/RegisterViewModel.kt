package com.example.report.viewmodels

import android.content.ContentValues
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.report.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    val db = Firebase.firestore
    var usuarios: MutableList<Usuario> = ArrayList<Usuario>()

    fun getUsuarios() {
        db.collection("users")
            .get()
            .addOnSuccessListener { snapshot ->
                if (snapshot != null) {
                    for (usuario in snapshot) { usuarios.add(usuario.toObject()) }
                }
            }

            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }

    fun verificarUsuarioUnico (emailView: TextView) : Boolean {
        val email = emailView.text.toString()
        var user: Usuario
        var flagUserUnique: Boolean = true

        for (usuario in usuarios) {
            if (email == usuario.username) {
                flagUserUnique = false
            }
        }

        Log.d("BANDERA FINAL = ", flagUserUnique.toString())
        return flagUserUnique
    }

    fun registrarUsuario (user: Usuario, uid: String) {
        db.collection("users").document(uid).set(user)
    }

    fun verificarCamposCompletos(
                                nombreView: TextView,
                                apellidoView: TextView,
                                emailView: TextView,
                                passwordView1: TextView,
                                passwordView2: TextView): Boolean {

        return (nombreView.length() == 0 ||
                apellidoView.length() == 0 ||
                emailView.length() == 0 ||
                passwordView1.length() == 0 ||
                passwordView2.length() == 0
                )
    }

    fun verificarEmail(emailView : TextView) : Boolean {
        return emailView.text.toString().contains("@")
    }

    fun verificarPasswords(passwordView1: TextView, passwordView2: TextView) : Boolean {
        return (passwordView1.text.toString() == passwordView2.text.toString())
    }

    fun verificarLargoPassword(passwordView1: TextView) : Boolean {
        return passwordView1.text.toString().length >= 8
    }
}