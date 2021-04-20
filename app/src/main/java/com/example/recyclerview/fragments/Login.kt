package com.example.recyclerview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.recyclerview.R
import com.example.recyclerview.entities.Usuario
import com.google.android.material.snackbar.Snackbar

class Login : Fragment() {
    lateinit var v : View
    lateinit var btnInicioSesion : Button
    lateinit var userView : TextView
    lateinit var passwordView : TextView

    var usuarios : MutableList<Usuario> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Se ejecuta una sola vez
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        btnInicioSesion = v.findViewById(R.id.btnLogin)
        userView = v.findViewById(R.id.userText)
        passwordView = v.findViewById(R.id.passwordText)
        return v
    }

    override fun onStart() {
        super.onStart()

        usuarios.add(Usuario("federico95", "Fede02"))
        usuarios.add(Usuario("ramoncito", "Chango001"))

        btnInicioSesion.setOnClickListener {

            if (userView.length() > 0 && passwordView.length() > 0) { //Formulario completo
                var user : Usuario? = null
                var flagUserExist : Boolean = false
                var i : Int = 0

                while (i < usuarios.size && !flagUserExist) {
                    if (usuarios[i].usuario == userView.text.toString()) {
                        user = usuarios[i]
                        flagUserExist=true
                    }
                    else i++
                }

                if (!flagUserExist) {
                    Snackbar.make(v, "El usuario no existe", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    if (user != null) {
                        if (userView.text.toString() == user.usuario && passwordView.text.toString() == user.contrasenia) {
                            Snackbar.make(v, "Bienvenido" +user.usuario, Snackbar.LENGTH_SHORT).show()
                            val action = LoginDirections.actionLoginToMainActivity(user.usuario)
                            v.findNavController().navigate(action)
                        } else {
                            Snackbar.make(v, "Usuario y/o contraseña inválida", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }

            }
            else {
                Snackbar.make(v, "Complete ambos campos", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}