package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Jugador

data class JugadorCreateDTO(
    val nombre: String,
    val apellido: String,
    val valoracion: Double,
)

fun Jugador.toJugadorCreateDTO() = JugadorCreateDTO(
    nombre = this.nombre,
    apellido = this.apellido,
    valoracion = this.valoracionJugador(),
)