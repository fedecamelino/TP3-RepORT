package com.example.recyclerview.entities

class Usuario (username: String, password: String) {
    var usuario : String
    var contrasenia : String

    init {
        this.usuario = username
        this.contrasenia = password
    }
}