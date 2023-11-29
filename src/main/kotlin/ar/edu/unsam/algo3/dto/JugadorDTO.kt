package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*

data class InfoCrearJugadorDTO(
    val nombre: String,
    val apellido: String,
    val nacimiento: String,
    val altura: Double,
    val peso: Double,
    val camiseta: Int,
    val seleccion: String,
    val debut: String,
    val posicion: String,
    val posiciones: List<String>?,
    val esLider: Boolean,
    val cotizacion: Double
)
data class JugadorDTO(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val altura: Double,
    val peso: Double,
    val nroCamiseta: Int,
    val seleccion: String,
    val posicion: String,
    val cotizacion: Double
)

fun Jugador.toDTO() = JugadorDTO(
    id = this.id,
    nombre = this.nombre,
    apellido = this.apellido,
    fechaNacimiento = this.fechaNacimiento.toString(),
    altura = this.altura,
    peso = this.peso,
    nroCamiseta = this.nroCamiseta,
    seleccion = this.seleccionPerteneciente.pais,
    posicion = this.posicion.nombre,
    cotizacion = this.cotizacion
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

