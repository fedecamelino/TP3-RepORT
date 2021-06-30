package com.example.report.viewmodels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.example.report.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class LoginViewModel : ViewModel() {

    fun verificarCamposCompletos(userView: TextView, passwordView: TextView): Boolean {
        return (userView.length() == 0 || passwordView.length() == 0)
    }
}