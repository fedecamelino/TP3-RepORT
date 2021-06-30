package com.example.report.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.report.R
import com.example.report.entities.Usuario
import com.example.report.viewmodels.LoginViewModel
import com.example.report.viewmodels.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("DEPRECATION")
class Profile : Fragment() {
    lateinit var v: View
    lateinit var tituloView: TextView
    lateinit var emailView : TextView
    lateinit var passwordView: TextView
    lateinit var passwordInputActualView : TextView
    lateinit var passwordInputView1 : TextView
    lateinit var passwordInputView2 : TextView
    lateinit var btnCambio : Button
    lateinit var btnLogOut : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModelRegistro: RegisterViewModel


    class Constants {
        companion object {
            val OUTPUT_0 = "Las contraseñas no son iguales"
            val OUTPUT_1 = "Contraseña muy corta"
            val OUTPUT_2 = "Su contraseña actual es incorrecta"
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        v = inflater.inflate(R.layout.fragment_profile, container, false)

        tituloView = v.findViewById(R.id.tituloProfile)
        passwordInputActualView = v.findViewById(R.id.passwordInputActual)
        emailView = v.findViewById(R.id.emailProfile)
        passwordView = v.findViewById(R.id.passwordProfile)
        passwordInputView1 = v.findViewById(R.id.passwordInputProfile1)
        passwordInputView2 = v.findViewById(R.id.passwordInputProfile2)
        btnCambio = v.findViewById(R.id.buttonCambio)
        btnLogOut = v.findViewById(R.id.buttonLogOut)

        auth = Firebase.auth

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelRegistro = ViewModelProvider(requireActivity()).get(RegisterViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        val sharedPref: SharedPreferences = requireContext().getSharedPreferences("My Preferences", Context.MODE_PRIVATE)

        emailView.text = sharedPref.getString("USERNAME", "default")!!
        val passwordActual = sharedPref.getString("PASSWORD", "default")!!

        btnCambio.setOnClickListener {

            if (passwordInputActualView.text.toString() == passwordActual) {
                if (viewModelRegistro.verificarPasswords(passwordInputView1, passwordInputView2)) {
                    if (viewModelRegistro.verificarLargoPassword(passwordInputView1)) {

                        val user = FirebaseAuth.getInstance().currentUser

                        user!!.updatePassword(passwordInputView1.text.toString())
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    println("Update Success")
                                } else {
                                    println("Error Update")
                                }
                            }   

                        Firebase.auth.signOut()
                        System.exit(0)
                    } else {
                        Snackbar.make(v, Constants.OUTPUT_1, Snackbar.LENGTH_SHORT).show()
                        passwordInputView1.setText("")
                        passwordInputView2.setText("")
                    }

                } else {
                    Snackbar.make(v, Constants.OUTPUT_0, Snackbar.LENGTH_SHORT).show()
                    passwordInputView1.setText("")
                    passwordInputView2.setText("")
                }
            } else {
                Snackbar.make(v, Constants.OUTPUT_2, Snackbar.LENGTH_SHORT).show()
                passwordInputActualView.setText("")
            }
        }

        btnLogOut.setOnClickListener {
            Firebase.auth.signOut()
            System.exit(0)
        }
    }
}