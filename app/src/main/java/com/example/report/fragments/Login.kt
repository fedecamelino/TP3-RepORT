package com.example.report.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var auth: FirebaseAuth

    class Constants {
        companion object {
            val OUTPUT_1 = "Usuario y/o contraseña inválida"
            val OUTPUT_2 = "Complete ambos campos"
        }
    }

    @SuppressLint("CutPasteId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_login, container, false)

        btnInicioSesion = v.findViewById(R.id.btnLogin)
        userView = v.findViewById(R.id.userText)
        passwordView = v.findViewById(R.id.passwordText)
        userViewTitle = v.findViewById(R.id.userText)
        passwordViewTitle = v.findViewById(R.id.passwordText)
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

        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("My Preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnInicioSesion.setOnClickListener {

            if (viewModel.verificarCamposCompletos(userView, passwordView)) {
                Snackbar.make(v, Constants.OUTPUT_2, Snackbar.LENGTH_SHORT).show()
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

                            editor.putString("USERNAME", email)
                            editor.putString("PASSWORD", password)
                            editor.apply()

                            val action = LoginDirections.actionLoginToMainActivity()
                            v.findNavController().navigate(action)

                        } else {
                            // If sign in fails
                            Log.d("SIGN IN: ", "Failure")
                            Snackbar.make(v, Constants.OUTPUT_1, Snackbar.LENGTH_SHORT).show()
                        }
                    }

                userView.setText("")
                passwordView.setText("")
            }
        }

        btnRegister.setOnClickListener {
            val action = LoginDirections.actionLoginToRegister()
            v.findNavController().navigate(action)
        }
    }
}