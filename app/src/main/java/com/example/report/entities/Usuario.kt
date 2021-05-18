package com.example.report.entities

class Usuario (username: String, password: String, name: String, lastName: String) {
    var usuario : String
    var contrasenia : String
    var nombre: String
    var apellido: String

    init {
        this.usuario = username
        this.contrasenia = password
        this.nombre = name
        this.apellido = lastName
    }
}