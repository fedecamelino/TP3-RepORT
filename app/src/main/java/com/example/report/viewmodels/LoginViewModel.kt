package com.example.report.viewmodels

import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.report.entities.Usuario

class LoginViewModel : ViewModel() {
    private var usuarios : MutableList<Usuario> = mutableListOf()

    fun initUsers () {
        usuarios.add(Usuario("roman@gmail.com", "gallardoHijo", "Juan Roman", "Riquelme"))
        usuarios.add(Usuario("patron2@consejo.com", "ChauPol2020", "Jorge", "Bermudez"))
        usuarios.add(Usuario("miguelito5@boca.com", "unpoquitoma", "Miguel", "Russo"))
        usuarios.add(Usuario("raul5@boca.com", "mosquito", "Raul", "Cascini"))
    }

    fun logon (userView: TextView, passwordView: TextView) : Int {
        var user : Usuario? = null
        var flagUserExist : Boolean = false
        var i : Int = 0

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
                if (user != null) {
                    if (userView.text.toString() == user.usuario && passwordView.text.toString() == user.contrasenia) {
                        output = 0
                    } else output = 1
                }
            }
        }

        return output
    }


}