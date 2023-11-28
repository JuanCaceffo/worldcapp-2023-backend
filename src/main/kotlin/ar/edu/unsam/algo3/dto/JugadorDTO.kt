package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*

data class infoJugadorDTO(
    val nombre: String,
    val apellido: String,
    val nacimiento: String,
    val altura: Double,
    val peso: Double,
    val camiseta: Int,
    val seleccion: String,
    val debut: String,
    val posicion: String,
    val Posiciones: List<String>?,
    val esLider: Boolean,
    val cotizacion: Double
)

data class JugadorCreateDTO(
    val nombre: String,
    val apellido: String,
    val valoracion: Double,
)

data class DataCreateFigurita(
    val jugadores: List<JugadorCreateDTO>,
    val levelPrints: List<NivelImpresion>
)

fun Jugador.toJugadorCreateDTO() = JugadorCreateDTO(
    nombre = this.nombre,
    apellido = this.apellido,
    valoracion = this.valoracionJugador(),
)

