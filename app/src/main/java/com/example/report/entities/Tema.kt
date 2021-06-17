package com.example.report.entities

import android.os.Parcel
import android.os.Parcelable

class Tema (nombre: String, tipo: String, descrip: String, usuario: String) : Parcelable {
    var name: String = ""
    var type: String = ""
    var description: String = ""
    var userCreator: String = ""

    constructor() : this("", "", "", "")

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

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeString(type)
        writeString(description)
        writeString(userCreator)
    }

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    companion object CREATOR : Parcelable.Creator<Tema> {
        override fun createFromParcel(parcel: Parcel): Tema {
            return Tema(parcel)
        }

        override fun newArray(size: Int): Array<Tema?> {
            return arrayOfNulls(size)
        }
    }
}