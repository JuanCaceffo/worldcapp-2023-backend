package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*

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

