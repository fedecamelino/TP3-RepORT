package com.example.report.entities

import android.os.Parcel
import android.os.Parcelable

class Usuario (username: String, name: String, lastName: String) : Parcelable {
    var username : String
    var nombre: String
    var apellido: String

    constructor() : this("", "","")

    init {
        this.username = username
        this.nombre = name
        this.apellido = lastName
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(username)
        writeString(nombre)
        writeString(apellido)
    }

    constructor(source: Parcel) : this(
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
