package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class WorldCappBoostrap (
  val figuritasRepository: FiguritasRepository,
  val jugadorRepository: JugadorRepository,
  val seleccionesRepository: SeleccionesRepository,
  // val usuariosRepositorio: UsuarioRepositorio,
  // val puestosDeVentaRepositorio: PuestoDeVentaRepositorio,
): InitializingBean {

  private lateinit var argentina: Seleccion
  private lateinit var polonia: Seleccion
  private lateinit var belgica: Seleccion
  private lateinit var holanda: Seleccion
  private lateinit var francia: Seleccion

  private lateinit var afc: Confederacion
  private lateinit var caf: Confederacion
  private lateinit var concacaf: Confederacion
  private lateinit var conmebol: Confederacion
  private lateinit var ofc: Confederacion
  private lateinit var uefa: Confederacion

  private lateinit var lewandowski: Jugador
  private lateinit var hazard: Jugador
  private lateinit var debruyne: Jugador
  private lateinit var vandijk: Jugador
  private lateinit var mbappe: Jugador

  fun crearFiguritas() {
    figuritasRepository.apply {
      create(Figurita(numero=1, onFire=true, cantidadImpresa=impresionBaja, jugador=jugadorRepository.getById(0)!!))
      create(Figurita(numero=8, onFire=true, cantidadImpresa= impresionAlta, jugador=lewandowski))
      create(Figurita(numero=15, onFire=true, cantidadImpresa= impresionAlta, jugador=hazard))
      create(Figurita(numero=6, onFire=false, cantidadImpresa= impresionAlta, jugador=debruyne))
      create(Figurita(numero=9, onFire=true, cantidadImpresa= impresionAlta, jugador=vandijk))
      create(Figurita(numero=12, onFire=true, cantidadImpresa= impresionAlta, jugador=mbappe))
    }
  }

  private fun crearJugador() {
    lewandowski = Jugador(nombre = "Robert", apellido = "Lewandowski", fechaNacimiento = LocalDate.of(1988, 8,21), nroCamiseta = 9, seleccionPerteneciente = polonia, posicion=Delantero, anioDeDebut=2006, altura=1.84, peso=80.0, esLider=true, cotizacion=1600000.0 )
    hazard = Jugador(nombre = "Eden", apellido = "Hazard", fechaNacimiento = LocalDate.of(1991, 1,7), nroCamiseta = 7, seleccionPerteneciente = belgica, posicion=Delantero, anioDeDebut=2006, altura=1.73, peso=73.0, esLider=true, cotizacion=1100000.0 )
    debruyne = Jugador(nombre = "Kevin", apellido = "De Bruyne", fechaNacimiento = LocalDate.of(1991, 6,28), nroCamiseta = 17, seleccionPerteneciente = belgica, posicion=Mediocampista, anioDeDebut=2008, altura=1.81, peso=76.0, esLider=false, cotizacion=1200000.0 )
    vandijk = Jugador(nombre = "Virgil", apellido = "Can Dijk", fechaNacimiento = LocalDate.of(1991, 7,8), nroCamiseta = 4, seleccionPerteneciente = holanda, posicion=Defensor, anioDeDebut=2009, altura=1.93, peso=72.0, esLider=true, cotizacion=1400000.0 )
    mbappe = Jugador(nombre = "Kylian", apellido = "Mbappé", fechaNacimiento = LocalDate.of(1998, 12,20), nroCamiseta = 7, seleccionPerteneciente = francia, posicion=Delantero, anioDeDebut=2016, altura=1.78, peso=74.0, esLider=false, cotizacion=1700000.0 )

    jugadorRepository.apply {
      create(Jugador(nombre = "Gonzalo", apellido = "Martinez", fechaNacimiento = LocalDate.of(1993, 6,13), nroCamiseta = 10, seleccionPerteneciente = argentina, posicion=Mediocampista, anioDeDebut=2008, altura=1.72, peso=70.0, esLider=true, cotizacion=9000000.0 ) )
      create(lewandowski)
      create(hazard)
      create(debruyne)
      create(vandijk)
      create(mbappe)
    }
  }

  private fun crearSelecciones() {
    argentina = Seleccion(pais="Argentina", conmebol, copasConfederacion=22, copasDelMundo=3 )
    polonia = Seleccion(pais="Polonia", uefa, copasConfederacion = 7, copasDelMundo = 3)
    belgica = Seleccion(pais="Belgica", uefa, copasConfederacion = 3, copasDelMundo = 2)
    holanda = Seleccion(pais = "Holanda", uefa, copasConfederacion = 4, copasDelMundo = 2)
    francia = Seleccion(pais = "Francia", uefa, copasConfederacion = 2, copasDelMundo = 1)

    seleccionesRepository.apply {
      create(argentina)
      create(polonia)
      create(belgica)
      create(holanda)
      create(francia)
    }
  }

  private fun crearConfederaciones() {
    afc = Confederacion("AFC")
    caf = Confederacion("CAF")
    concacaf = Confederacion("CONCACAF")
    conmebol = Confederacion("CONMEBOL")
    ofc = Confederacion("OFC")
    uefa = Confederacion("UEFA")
  }

  override fun afterPropertiesSet() {
    this.crearConfederaciones()
    this.crearSelecciones()
    this.crearJugador()
    this.crearFiguritas()
  }
}