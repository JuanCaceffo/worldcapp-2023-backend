package ar.edu.unsam.algo3.dto

import  ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.NivelImpresion
import ar.edu.unsam.algo3.domain.Usuario

data class TemplateFiguritaDTO(
  val id: Int,
  val numero: Int,
  val onFire: Boolean,
  val nivelImpresion: String,
)

fun Figurita.toDTO(user: Usuario?) = FiguritaDTO(
  id = this.id,
  numero = this.numero,
  onFire = this.onFire,
  nivelImpresion = this.cantidadImpresa.nombre,
  nombre = this.jugador.nombre,
  apellido = this.jugador.apellido,
  peso = this.jugador.peso,
  promesa = this.jugador.promesaDelFutbol(),
  altura = this.jugador.altura,
  nroCamiseta = this.jugador.nroCamiseta,
  fechaNac = this.jugador.fechaNacimiento.toString(),
  edad = this.jugador.edad(),
  seleccion = this.jugador.seleccionPerteneciente.pais,
  valorBase = this.valorInicial,
  posicion = this.jugador.posicion.nombre,
  cotizacion = this.jugador.cotizacion,
  anioDebut = this.jugador.anioDeDebut,
  copasDelMundo = this.jugador.seleccionPerteneciente.copasDelMundo,
  confederacion = this.jugador.seleccionPerteneciente.confederacion.nombre,
  confederacionCopas = this.jugador.seleccionPerteneciente.copasConfederacion,
  esLider = this.jugador.esLider,
  valoracion = this.jugador.valoracionJugador(),
  duenio = (user?.nombreUsuario ?: ""),
  idUsuario = user?.id ?: -1
)

data class FiguritaDTO(
  val id: Int,
  val numero: Int,
  val onFire: Boolean,
  val nivelImpresion: String,
  //jugador
  val nombre: String,
  val apellido: String,
  val peso: Double,
  val promesa: Boolean,
  val altura: Double,
  val nroCamiseta: Int,
  val fechaNac: String,
  val edad: Int,
  val seleccion: String,
  val valorBase: Double,
  val posicion: String,
  val cotizacion: Double,
  val anioDebut: Int,
  val copasDelMundo: Int,
  val confederacion: String,
  val confederacionCopas: Int,
  val esLider: Boolean,
  val valoracion: Double,
  //duenio
  val duenio: String,
  val idUsuario: Int
)

data class FiguritaIndexDTO(
  val id: Int,
  val numero: Int,
  val onFire: Boolean,
  val nivelImpresion: NivelImpresion,
  //jugador
  val nombre: String,
  val apellido: String,
  val valoracion: Double,
)

fun Figurita.toIndexDTO() = FiguritaIndexDTO(
  id = this.id,
  numero = this.numero,
  onFire = this.onFire,
  nivelImpresion = this.cantidadImpresa,
  nombre = this.jugador.nombre,
  apellido = this.jugador.apellido,
  valoracion = this.jugador.valoracionJugador(),
)