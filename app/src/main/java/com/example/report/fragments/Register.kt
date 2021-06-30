package com.example.report.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.report.R
import com.example.report.entities.Usuario
import com.example.report.viewmodels.LoginViewModel
import com.example.report.viewmodels.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class Register : Fragment() {

    lateinit var v : View
    lateinit var tituloView: TextView
    lateinit var nombreView : TextView
    lateinit var nombreInputView : TextView
    lateinit var apellidoView : TextView
    lateinit var apellidoInputView : TextView
    lateinit var emailView : TextView
    lateinit var emailInputView : TextView
    lateinit var passwordView : TextView
    lateinit var passwordInputView1 : TextView
    lateinit var passwordInputView2 : TextView
    lateinit var btnRegistrar : Button
    private lateinit var viewModelRegistro: RegisterViewModel
    private lateinit var auth: FirebaseAuth
    var outputString : String = ""

    class Constants {
        companion object {
            val OUTPUT_0 = "Usuario registrado con éxito"
            val OUTPUT_1 = "El correo ya existe"
            val OUTPUT_2 = "Complete todos los campos"
            val OUTPUT_3 = "Las contraseñas no son iguales"
            val OUTPUT_4 = "Contraseña muy corta"
            val OUTPUT_5 = "El correo es inválido"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_register, container, false)

        tituloView = v.findViewById(R.id.tituloRegistroTxt)
        nombreView = v.findViewById(R.id.nombreTxt)
        nombreInputView = v.findViewById(R.id.nombreInputTxt)
        apellidoView = v.findViewById(R.id.apellidoTxt)
        apellidoInputView = v.findViewById(R.id.apellidoInputTxt)
        emailView = v.findViewById(R.id.emailTxt)
        emailInputView = v.findViewById(R.id.emailInputTxt)
        passwordView = v.findViewById(R.id.passwordTxt)
        passwordInputView1 = v.findViewById(R.id.passwordInputTxt1)
        passwordInputView2 = v.findViewById(R.id.passwordInputTxt2)
        btnRegistrar = v.findViewById(R.id.buttonRegistro)

        auth = Firebase.auth
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelRegistro = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        viewModelRegistro.getUsuarios()

        btnRegistrar.setOnClickListener {

            if (viewModelRegistro.verificarCamposCompletos(
                                    emailInputView,
                                    apellidoInputView,
                                    emailInputView,
                                    passwordInputView1,
                                    passwordInputView2)
            ) { outputString = Constants.OUTPUT_2 }
            else {
                if (viewModelRegistro.verificarEmail(emailInputView)) {
                    if (viewModelRegistro.verificarPasswords(passwordInputView1, passwordInputView2)) {
                        if (viewModelRegistro.verificarLargoPassword(passwordInputView1)) {
                            if (viewModelRegistro.verificarUsuarioUnico(emailInputView)) {
                                val email = emailInputView.text.toString()
                                val password1 = passwordInputView1.text.toString()
                                val nombre = nombreInputView.text.toString()
                                val apellido = apellidoInputView.text.toString()
                                val usuario = Usuario(email, password1, nombre, apellido)

                                auth.createUserWithEmailAndPassword(email, password1)
                                    .addOnCompleteListener(requireActivity()) { task ->
                                        if (task.isSuccessful) {
                                            // Sign in success
                                            val uid = auth.currentUser?.uid
                                            if (uid != null) {
                                                Log.d("UID = ", uid)
                                                viewModelRegistro.registrarUsuario(usuario, uid)
                                            }
                                        }
                                    }
                                outputString = Constants.OUTPUT_0


                            } else {
                                emailInputView.setText("")
                                outputString = Constants.OUTPUT_1
                            }
                        } else {
                            passwordInputView1.setText("")
                            passwordInputView2.setText("")
                            outputString = Constants.OUTPUT_4
                        }
                    } else {
                        passwordInputView1.setText("")
                        passwordInputView2.setText("")
                        outputString = Constants.OUTPUT_3
                    }
                } else {
                    emailInputView.setText("")
                    outputString = Constants.OUTPUT_5
                }
            }

            Snackbar.make(v, outputString, Snackbar.LENGTH_SHORT).show()

            if (outputString == Constants.OUTPUT_0) {
                val action = RegisterDirections.actionRegisterToLogin()
                v.findNavController().navigate(action)
            }

        }

    }


}