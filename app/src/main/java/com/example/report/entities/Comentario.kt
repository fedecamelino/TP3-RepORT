package com.example.report.entities

class Comentario (usuario: String, descripcion: String) {

    var description: String = ""
    var userCreator: String = ""

    init {
        this.description = descripcion
        this.userCreator = usuario
    }
}