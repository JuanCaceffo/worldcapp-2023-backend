package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*

open class JugadorBaseDTO(
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

class InfoCrearJugadorDTO(
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    altura: Double,
    peso: Double,
    nroCamiseta: Int,
    seleccion: String,
    val debut: String,
    posicion: String,
    val posiciones: List<String>?,
    val esLider: Boolean,
    cotizacion: Double
) : JugadorBaseDTO(nombre,apellido, fechaNacimiento, altura, peso, nroCamiseta, seleccion, posicion, cotizacion)
class JugadorDTO(
    val id: Int,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    altura: Double,
    peso: Double,
    nroCamiseta: Int,
    seleccion: String,
    posicion: String,
    cotizacion: Double
) : JugadorBaseDTO(nombre,apellido, fechaNacimiento, altura, peso, nroCamiseta, seleccion, posicion, cotizacion)


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

