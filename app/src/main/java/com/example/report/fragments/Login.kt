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
import com.example.report.viewmodels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class Login : Fragment() {
    lateinit var v : View
    lateinit var btnInicioSesion : Button
    lateinit var userView : TextView
    lateinit var passwordView : TextView
    lateinit var userViewTitle : TextView
    lateinit var passwordViewTitle : TextView
    lateinit var registerView : TextView
    lateinit var btnRegister : Button
    private lateinit var viewModel: LoginViewModel
    var outputString : String = ""

    private lateinit var auth: FirebaseAuth
    /*lateinit var user : FirebaseUser*/

    class Constants {
        companion object {
            val OUTPUT_0 = "Bienvenido"
            val OUTPUT_1 = "Usuario y/o contraseña inválida"
            val OUTPUT_2 = "Complete ambos campos"
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        btnInicioSesion = v.findViewById(R.id.btnLogin)
        userView = v.findViewById(R.id.userText)
        passwordView = v.findViewById(R.id.passwordText)
        userViewTitle = v.findViewById(R.id.userView)
        passwordViewTitle = v.findViewById(R.id.passwordView)
        registerView = v.findViewById(R.id.txtRegistrate)
        btnRegister = v.findViewById(R.id.buttonRegistrar)

        auth = Firebase.auth

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        btnInicioSesion.setOnClickListener {
            /*outputString = when (viewModel.logon(userView, passwordView)) {
                0 -> Constants.OUTPUT_0
                1 -> Constants.OUTPUT_1
                2 -> Constants.OUTPUT_2
                else -> Constants.OUTPUT_3
            }*/

            if (viewModel.verificarCamposCompletos(userView, passwordView)) {
                outputString = Constants.OUTPUT_2
            }
            else {
                val email = userView.text.toString()
                val password = passwordView.text.toString()

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success
                            Log.d("SIGN IN: ", "Success")
                            val uid = auth.currentUser?.uid
                            Log.d("UID = ", uid.toString())
                            if (uid != null) {
                                viewModel.getUser(uid)
                            }
                            outputString = Constants.OUTPUT_0
                        } else {
                            // If sign in fails
                            Log.d("SIGN IN: ", "Failure")
                            outputString = Constants.OUTPUT_1
                        }
                    }

            }

            userView.setText("")
            passwordView.setText("")

            Snackbar.make(v, outputString, Snackbar.LENGTH_SHORT).show()

            /*if (outputString == Constants.OUTPUT_0) {
                val action = LoginDirections.actionLoginToMainActivity(viewModel.usuarioLogueado)
                v.findNavController().navigate(action)
            }*/
        }

        /*btnRegister.setOnClickListener {

        }*/
    }
}