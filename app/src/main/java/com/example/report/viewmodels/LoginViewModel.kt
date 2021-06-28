package com.example.report.viewmodels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.report.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class LoginViewModel : ViewModel() {

    /*private var usuarios : MutableList<Usuario> = mutableListOf()
    var usuarioLogueado : Usuario? = null*/
    val db = Firebase.firestore

    /*fun initUsers () {
        usuarios.add(Usuario("roman@gmail.com", "gallardoHijo", "Juan Roman", "Riquelme"))
        usuarios.add(Usuario("patron2@consejo.com", "ChauPol2020", "Jorge", "Bermudez"))
        usuarios.add(Usuario("miguelito5@boca.com", "unpoquitoma", "Miguel", "Russo"))
        usuarios.add(Usuario("raul5@boca.com", "mosquito", "Raul", "Cascini"))
    }

    fun logon (userView: TextView, passwordView: TextView) : Int {
        var flagUserExist : Boolean = false
        var i : Int = 0
        lateinit var user : Usuario

        var output : Int = 3
        //Por defecto devuelve 3 -> Campo/s vacio/s
        //Usuario inexistente en la BD -> 2
        //Credenciales incorrectas -> 1
        //Credenciales correctas -> 0

        if (userView.length() > 0 && passwordView.length() > 0) { //Campos completos
            while (i < usuarios.size && !flagUserExist) {
                if (usuarios[i].usuario == userView.text.toString()) {
                    user = usuarios[i]
                    flagUserExist=true
                }
                else i++
            }

            if (!flagUserExist) output = 2
            else {
                if (userView.text.toString() == user.usuario && passwordView.text.toString() == user.contrasenia) {
                    setUserLogin(user)
                    output = 0
                }
                else output = 1
            }
        }

        return output
    }*/

    fun getUser(uid: String) {

        val userRef = db.collection("users").document(uid)
        var user: Usuario

        userRef
            .get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    user = dataSnapshot.toObject<Usuario>()!!
                    Log.d("USUARIO LOGUEADO = ", user.username)
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