package ar.edu.unsam.algo2.worldcapp2023

import com.google.gson.Gson

interface  ServiceSelecciones {
    fun getSelecciones(): String
}

//Extra no se usa en el TP
class ServicioSeleccionCreaJson: ServiceSelecciones {
    private val selecciones: MutableList<SeleccionDataExterna> = mutableListOf(argentina, brasil, alemania, uruguay, francia, chile)

    override fun getSelecciones(): String = Gson().toJson(selecciones).trimIndent()
}

var argentina = SeleccionDataExterna (
    id = 0,
    pais = "Argentina",
    confederacion = "CONMEBOL",
    copasDelMundo = 3,
    copasConfederacion = 15
)

var brasil = SeleccionDataExterna (
    id = 1,
    pais = "Brasil",
    confederacion = "CONMEBOL",
    copasDelMundo = 5,
    copasConfederacion = 15
)
var alemania = SeleccionDataExterna(
    id = 2,
    pais = "Alemania",
    confederacion = "UEFA",
    copasDelMundo = 4,
    copasConfederacion = 3
)
var uruguay = SeleccionDataExterna(
    id = 3,
    pais = "Uruguay",
    confederacion = "CONMEBOL",
    copasDelMundo = 2,
    copasConfederacion = 15
)
var francia = SeleccionDataExterna(
    id = 4,
    pais = "Francia",
    confederacion = "UEFA",
    copasDelMundo = 2,
    copasConfederacion = 3
)
var chile = SeleccionDataExterna(
    id = 5,
    pais = "Chile",
    confederacion = "CONMEBOL",
    copasDelMundo = 0,
    copasConfederacion = 15
)
