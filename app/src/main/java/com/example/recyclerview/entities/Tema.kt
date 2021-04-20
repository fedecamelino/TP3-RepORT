package com.example.recyclerview.entities

class Tema (nombre: String, tipo: String, descrip: String) {
    var name: String = ""
    var type: String = ""
    var description: String = ""

    class Constants {
        companion object {
            val materia = "MATERIA"
            val admin = "ADMINISTRATIVO"
            val ingreso = "INGRESO"
        }
    }

    init {
        this.name = nombre
        this.type = tipo
        this.description = descrip
    }
}