package com.example.report.viewmodels

import androidx.lifecycle.ViewModel
import com.example.report.entities.Tema

class ListTemasViewModel : ViewModel() {
    var temas : MutableList<Tema> = ArrayList<Tema>()

    fun initTemas() {
        temas.add(Tema("Parciales TP2", Tema.Constants.materia, "¿Fecha de parciales de Taller de Prog. 2?", "roman@gmail.com"))
        temas.add(Tema("Descuento materias", Tema.Constants.admin, "Si curso 2 materias, puedo pedir dto.?", "miguelito5@boca.com"))
        temas.add(Tema("Teoria CS", Tema.Constants.materia, "Necesito algún resumen de Calidad de Software", "raul05@hotmail.com"))
        temas.add(Tema("Cuota 2021", Tema.Constants.admin, "Cuanto vale la cuota actual de 2021?", "patron2@consejo.com"))
        temas.add(Tema("Examen ingreso", Tema.Constants.ingreso, "Hay que dar examen de ingreso?", "patron2@consejo.com"))
        temas.add(Tema("Libreta", Tema.Constants.admin, "Actualmente se usa la libreta de la ORT?", "roman@gmail.com"))
        temas.add(Tema("Nivel Ingles", Tema.Constants.ingreso, "Que tal el inglés que enseñan en ORT?", "patron2@consejo.com"))
    }


}