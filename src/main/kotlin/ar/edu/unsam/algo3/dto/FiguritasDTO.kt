package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Usuario

abstract class FiltroBaseDTO(
  val palabraClave:String = ""
)

open class FiguritaBaseDTO(
  val id: Int,
  val numero: Int,
  val onFire: Boolean,
  val nombre: String,
  val apellido: String,
  val nivelImpresion: String,
  val valoracion: Double
)
open class FiguritaCreateModifyDTO(
  val numero: Int,
  val nombre: String,
  val onFire: Boolean,
  val nivelImpresion: String,
  val urlImage: String? = null
)
class FiguritaFullDTO(
  id:Int,
  numero:Int,
  onFire: Boolean,
  nivelImpresion: String,
  //jugador
  nombre: String,
  apellido: String,
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
  valoracion:Double,
  //duenio
  val duenio: String,
  val idUsuario: Int
): FiguritaBaseDTO(id, numero, onFire, nombre, apellido, nivelImpresion, valoracion)

fun Figurita.toCreateModifyDto() = FiguritaCreateModifyDTO(
  numero = this.numero,
  nombre = jugador.nombre,
  onFire = this.onFire,
  nivelImpresion = this.cantidadImpresa.nombre,
  urlImage = this.urlImage
)

fun Figurita.toBaseDTO() = FiguritaBaseDTO(
  id = this.id,
  numero = this.numero,
  onFire = this.onFire,
  nombre = this.jugador.nombre,
  apellido = this.jugador.apellido,
  nivelImpresion = this.cantidadImpresa.nombre,
  valoracion = this.jugador.valoracionJugador(),
)

fun Figurita.toDTO(user: Usuario?) = FiguritaFullDTO(
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