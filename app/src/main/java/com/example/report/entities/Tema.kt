package com.example.report.entities

class Tema (nombre: String, tipo: String, descrip: String, usuario: String) {
    var name: String = ""
    var type: String = ""
    var description: String = ""
    var userCreator: String = ""

    class Constants {
        companion object {
            val materia = "MATERIA"
            val admin = "ADMINISTRATIVO"
            val ingreso = "INGRESO"
            val experiencia = "EXPERIENCIA"
        }
    }

    init {
        this.name = nombre
        this.type = tipo
        this.description = descrip
        this.userCreator = usuario
    }
}