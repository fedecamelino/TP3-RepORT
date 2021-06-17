package com.example.report.entities

import android.os.Parcel
import android.os.Parcelable

class Usuario (username: String, password: String, name: String, lastName: String) : Parcelable {
    var usuario : String
    var contrasenia : String
    var nombre: String
    var apellido: String

    constructor() : this("", "", "", "")

    init {
        this.usuario = username
        this.contrasenia = password
        this.nombre = name
        this.apellido = lastName
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(usuario)
        writeString(contrasenia)
        writeString(nombre)
        writeString(apellido)
    }

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}
