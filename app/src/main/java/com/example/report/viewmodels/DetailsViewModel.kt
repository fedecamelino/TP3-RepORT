package com.example.report.viewmodels

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.report.entities.Comentario
import com.example.report.entities.Tema
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class DetailsViewModel: ViewModel() {
    lateinit var temaActual: Tema
    var posicionTema: Int = 0
}