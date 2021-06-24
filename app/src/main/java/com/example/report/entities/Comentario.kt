package com.example.report.entities

class Comentario (descripcion: String, idtema: Int, usuario: String) {

    var idTheme: Int = 0
    var description: String = ""
    var userCreator: String = ""

    constructor() : this("", 0, "")

    init {
        this.idTheme = idtema
        this.description = descripcion
        this.userCreator = usuario
    }

    class Constants {
        companion object {
            val OUTPUT_CREADO = "Nuevo comentario realizado"
            val OUTPUT_ERROR = "Comentario vacio"
        }
    }
}