package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Order(0)
class FiguritasBoostrap(
  val figuritasRepository: FiguritasRepository,
  val jugadorRepository: JugadorRepository,
  val seleccionesRepository: SeleccionesRepository,
) : InitializingBean {

  private val selecciones = mapOf(
    "Argentina" to Seleccion(pais = "Argentina", Confederacion.CONMEBOL, copasConfederacion = 22, copasDelMundo = 3),
    "Brasil" to Seleccion(pais = "Brasil", Confederacion.CONMEBOL, copasConfederacion = 22, copasDelMundo = 5),
    "España" to Seleccion(pais = "España", Confederacion.UEFA, copasConfederacion = 8, copasDelMundo = 4),
    "Polonia" to Seleccion(pais = "Polonia", Confederacion.UEFA, copasConfederacion = 7, copasDelMundo = 3),
    "Belgica" to Seleccion(pais = "Belgica", Confederacion.UEFA, copasConfederacion = 3, copasDelMundo = 2),
    "Holanda" to Seleccion(pais = "Holanda", Confederacion.UEFA, copasConfederacion = 4, copasDelMundo = 2),
    "Francia" to Seleccion(pais = "Francia", Confederacion.UEFA, copasConfederacion = 2, copasDelMundo = 1),
    "Japón" to Seleccion(pais = "Japón", Confederacion.AFC, copasConfederacion = 3, copasDelMundo = 0),
    "China" to Seleccion(pais = "China", Confederacion.AFC, copasConfederacion = 1, copasDelMundo = 0),
    "Corea del Sur" to Seleccion(pais = "Corea del Sur", Confederacion.AFC, copasConfederacion = 2, copasDelMundo = 0),
    "México" to Seleccion(pais = "México", Confederacion.CONCACAF, copasConfederacion = 1, copasDelMundo = 0),
    "Corea del Norte" to Seleccion(pais = "Corea del Norte", Confederacion.AFC, copasConfederacion = 0, copasDelMundo = 0 )
  )

  private val jugadores = mapOf(
    "Martinez" to Jugador(
      nombre = "Gonzalo",
      apellido = "Martinez",
      fechaNacimiento = LocalDate.of(1993, 6, 13),
      nroCamiseta = 10,
      seleccionPerteneciente = selecciones["Argentina"]!!,
      posicion = Mediocampista,
      anioDeDebut = 2008,
      altura = 1.72,
      peso = 70.0,
      esLider = true,
      cotizacion = 9000000.0
    ),
    "Otamendi" to Jugador(
      nombre = "Nicolas",
      apellido = "Otamendi",
      fechaNacimiento = LocalDate.of(1998, 12, 2),
      nroCamiseta = 3,
      seleccionPerteneciente = selecciones["Argentina"]!!,
      posicion = Defensor,
      anioDeDebut = 2003,
      altura = 1.83,
      peso = 75.0,
      esLider = false,
      cotizacion = 1200000.0
    ),
    "Argento" to Jugador(
      nombre = "Pepe",
      apellido = "Argento",
      fechaNacimiento = LocalDate.of(1993, 2, 12),
      nroCamiseta = 5,
      seleccionPerteneciente = selecciones["Argentina"]!!,
      posicion = Arquero,
      anioDeDebut = 2012,
      altura = 1.69,
      peso = 69.0,
      esLider = true,
      cotizacion = 1700000.0
    ),
    "Messi" to Jugador(
      nombre = "Lionel",
      apellido = "Messi",
      fechaNacimiento = LocalDate.of(1987, 6, 24),
      nroCamiseta = 10,
      seleccionPerteneciente = selecciones["Argentina"]!!,
      posicion = Delantero,
      anioDeDebut = 2004,
      altura = 1.7,
      peso = 69.0,
      esLider = true,
      cotizacion = 19003000.0
    ),
    "Ramos" to Jugador(
      nombre = "Sergio",
      apellido = "Ramos",
      fechaNacimiento = LocalDate.of(1986, 3, 30),
      nroCamiseta = 4,
      seleccionPerteneciente = selecciones["España"]!!,
      posicion = Defensor,
      anioDeDebut = 2002,
      altura = 1.84,
      peso = 75.0,
      esLider = false,
      cotizacion = 1035000.0
    ),
    "Neymar" to Jugador(
      nombre = "Jr",
      apellido = "Neymar",
      fechaNacimiento = LocalDate.of(1992, 2, 5),
      nroCamiseta = 11,
      seleccionPerteneciente = selecciones["Brasil"]!!,
      posicion = Delantero,
      anioDeDebut = 2010,
      altura = 1.75,
      peso = 71.0,
      esLider = false,
      cotizacion = 39000000.0
    ),
    "Lewandowski" to Jugador(
      nombre = "Robert",
      apellido = "Lewandowski",
      fechaNacimiento = LocalDate.of(1988, 8, 21),
      nroCamiseta = 9,
      seleccionPerteneciente = selecciones["Polonia"]!!,
      posicion = Delantero,
      anioDeDebut = 2006,
      altura = 1.84,
      peso = 80.0,
      esLider = true,
      cotizacion = 1600000.0
    ),
    "Hazard" to Jugador(
      nombre = "Eden",
      apellido = "Hazard",
      fechaNacimiento = LocalDate.of(1991, 1, 7),
      nroCamiseta = 7,
      seleccionPerteneciente = selecciones["Belgica"]!!,
      posicion = Delantero,
      anioDeDebut = 2006,
      altura = 1.73,
      peso = 73.0,
      esLider = true,
      cotizacion = 1100000.0
    ),
    "DeBruyne" to Jugador(
      nombre = "Kevin",
      apellido = "De Bruyne",
      fechaNacimiento = LocalDate.of(1991, 6, 28),
      nroCamiseta = 17,
      seleccionPerteneciente = selecciones["Belgica"]!!,
      posicion = Mediocampista,
      anioDeDebut = 2008,
      altura = 1.81,
      peso = 76.0,
      esLider = false,
      cotizacion = 1200000.0
    ),
    "CanDijk" to Jugador(
      nombre = "Virgil",
      apellido = "Can Dijk",
      fechaNacimiento = LocalDate.of(1991, 7, 8),
      nroCamiseta = 4,
      seleccionPerteneciente = selecciones["Holanda"]!!,
      posicion = Defensor,
      anioDeDebut = 2009,
      altura = 1.93,
      peso = 72.0,
      esLider = true,
      cotizacion = 1400000.0
    ),
    "Mbappe" to Jugador(
      nombre = "Kylian",
      apellido = "Mbappé",
      fechaNacimiento = LocalDate.of(1998, 12, 20),
      nroCamiseta = 7,
      seleccionPerteneciente = selecciones["Francia"]!!,
      posicion = Delantero,
      anioDeDebut = 2016,
      altura = 1.78,
      peso = 74.0,
      esLider = false,
      cotizacion = 1700000.0
    ),
    "Nakamura" to Jugador(
      nombre = "Hiroshi",
      apellido = "Nakamura",
      fechaNacimiento = LocalDate.of(1991, 4, 8),
      nroCamiseta = 8,
      seleccionPerteneciente = selecciones["Japón"]!!,
      posicion = Mediocampista,
      anioDeDebut = 2010,
      altura = 1.76,
      peso = 68.5,
      esLider = false,
      cotizacion = 8000000.0
    ),
    "Zhang" to Jugador(
      nombre = "Yuning",
      apellido = "Zhang",
      fechaNacimiento = LocalDate.of(1994, 9, 15),
      nroCamiseta = 6,
      seleccionPerteneciente = selecciones["China"]!!,
      posicion = Delantero,
      anioDeDebut = 2012,
      altura = 1.84,
      peso = 75.2,
      esLider = false,
      cotizacion = 7000000.0
    ),
    "Silva" to Jugador(
      nombre = "Thiago",
      apellido = "Silva",
      fechaNacimiento = LocalDate.of(1984, 9, 22),
      nroCamiseta = 2,
      seleccionPerteneciente = selecciones["Brasil"]!!,
      posicion = Mediocampista,
      anioDeDebut = 2008,
      altura = 1.83,
      peso = 75.0,
      esLider = false,
      cotizacion = 8500000.0
    ),
    "Lee" to Jugador(
      nombre = "Ji-hoon",
      apellido = "Lee",
      fechaNacimiento = LocalDate.of(1992, 12, 3),
      nroCamiseta = 14,
      seleccionPerteneciente = selecciones["Corea del Sur"]!!,
      posicion = Defensor,
      anioDeDebut = 2012,
      altura = 1.86,
      peso = 76.5,
      esLider = false,
      cotizacion = 7500000.0
    ),
    "Fernandez" to Jugador(
      nombre = "Rafael",
      apellido = "Fernandez",
      fechaNacimiento = LocalDate.of(1994, 7, 17),
      nroCamiseta = 7,
      seleccionPerteneciente = selecciones["México"]!!,
      posicion = Delantero,
      anioDeDebut = 2013,
      altura = 1.81,
      peso = 72.8,
      esLider = true,
      cotizacion = 9000000.0
    ),
    "Modric" to Jugador(
      nombre = "Luka",
      apellido = "Modric",
      fechaNacimiento = LocalDate.of(1985, 9, 9),
      nroCamiseta = 10,
      seleccionPerteneciente = selecciones["México"]!!,
      posicion = Mediocampista,
      anioDeDebut = 2006,
      altura = 1.72,
      peso = 66.0,
      esLider = true,
      cotizacion = 7432000.0
    )
  )

  private val figuritas = listOf(
    Figurita(numero = 1, onFire = false, cantidadImpresa = impresionBaja, jugador = jugadores["Martinez"]!!),
    Figurita(numero = 2, onFire = false, cantidadImpresa = impresionAlta, jugador = jugadores["Otamendi"]!!),
    Figurita(numero = 3, onFire = true, cantidadImpresa = impresionMedia, jugador = jugadores["Argento"]!!),
    Figurita(numero = 4, onFire = true, cantidadImpresa = impresionBaja, jugador = jugadores["Messi"]!!),
    Figurita(numero = 5, onFire = false, cantidadImpresa = impresionAlta, jugador = jugadores["Ramos"]!!),
    Figurita(numero = 6, onFire = false, cantidadImpresa = impresionBaja, jugador = jugadores["Neymar"]!!),
    Figurita(numero = 7, onFire = true, cantidadImpresa = impresionAlta, jugador = jugadores["Lewandowski"]!!),
    Figurita(numero = 8, onFire = true, cantidadImpresa = impresionBaja, jugador = jugadores["Modric"]!!),
    Figurita(numero = 9, onFire = true, cantidadImpresa = impresionAlta, jugador = jugadores["Hazard"]!!),
    Figurita(numero = 10, onFire = false, cantidadImpresa = impresionAlta, jugador = jugadores["DeBruyne"]!!),
    Figurita(numero = 11, onFire = true, cantidadImpresa = impresionAlta, jugador = jugadores["CanDijk"]!!),
    Figurita(numero = 12, onFire = true, cantidadImpresa = impresionAlta, jugador = jugadores["Mbappe"]!!),
    Figurita(numero = 23, onFire = true, cantidadImpresa = impresionAlta, jugador = jugadores["Nakamura"]!!),
    Figurita(numero = 34, onFire = false, cantidadImpresa = impresionMedia, jugador = jugadores["Zhang"]!!),
    Figurita(numero = 20, onFire = false, cantidadImpresa = impresionBaja, jugador = jugadores["Silva"]!!),
    Figurita(numero = 28, onFire = true, cantidadImpresa = impresionBaja, jugador = jugadores["Lee"]!!),
    Figurita(numero = 25, onFire = true, cantidadImpresa = impresionMedia, jugador = jugadores["Fernandez"]!!)
  )

  fun crearFiguritas() {
    println("INICIO EL PROCESO DE CREACIÓN DE FIGURITAS")
    figuritas.forEach { figurita -> figuritasRepository.apply { create(figurita) } }
  }

  fun crearJugador() {
    println("INICIO EL PROCESO DE CREACIÓN DE JUGADORES")
    jugadores.values.forEach { jugador -> jugadorRepository.apply { create(jugador) } }
  }

  fun crearSelecciones() {
    println("INICIO EL PROCESO DE CREACIÓN DE SELECCIONES")
    selecciones.values.forEach { seleccion -> seleccionesRepository.apply { create(seleccion) } }
  }

  override fun afterPropertiesSet() {
    println("INICIO EL PROCESO DE CREACION DE SELECCIONES")
    this.crearSelecciones()
    println("INICIO EL PROCESO DE CREACION DE JUGADORES")
    this.crearJugador()
    println("INICIO EL PROCESO DE CREACION DE FIGURITAS")
    this.crearFiguritas()
    println("FIN DE CREACION DE FIGURITAS")
  }
}