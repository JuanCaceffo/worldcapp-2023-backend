package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point
import java.time.LocalDate

@Service
class WorldCappBoostrap (
  val figuritasRepository: FiguritasRepository,
  val jugadorRepository: JugadorRepository,
  val seleccionesRepository: SeleccionesRepository,
  val confeRepository: MutableList<Confederacion>,
  val usuariosRepositorio: UsuariosRepository,

  // val puestosDeVentaRepositorio: PuestoDeVentaRepositorio,
): InitializingBean {

  private lateinit var argentina: Seleccion
  private lateinit var espania: Seleccion
  private lateinit var brasil: Seleccion
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

  private lateinit var martinez: Jugador
  private lateinit var otamendi: Jugador
  private lateinit var argento: Jugador
  private lateinit var messi: Jugador
  private lateinit var ramos: Jugador
  private lateinit var neymar: Jugador
  private lateinit var lewandowski: Jugador
  private lateinit var hazard: Jugador
  private lateinit var debruyne: Jugador
  private lateinit var vandijk: Jugador
  private lateinit var mbappe: Jugador

  fun crearFiguritas() {
    figuritasRepository.apply {
      create(Figurita(numero=1, onFire=false, cantidadImpresa=impresionBaja, jugador=martinez))
      create(Figurita(numero=2, onFire=false, cantidadImpresa= impresionAlta, jugador=otamendi))
      create(Figurita(numero=3, onFire=true, cantidadImpresa= impresionMedia, jugador=argento))
      create(Figurita(numero=4, onFire=true, cantidadImpresa= impresionBaja, jugador=messi))
      create(Figurita(numero=5, onFire=false, cantidadImpresa= impresionAlta, jugador=ramos))
      create(Figurita(numero=6, onFire=false, cantidadImpresa= impresionBaja, jugador=neymar))
      create(Figurita(numero=8, onFire=true, cantidadImpresa= impresionAlta, jugador=lewandowski))
      create(Figurita(numero=15, onFire=true, cantidadImpresa= impresionAlta, jugador=hazard))
      create(Figurita(numero=6, onFire=false, cantidadImpresa= impresionAlta, jugador=debruyne))
      create(Figurita(numero=9, onFire=true, cantidadImpresa= impresionAlta, jugador=vandijk))
      create(Figurita(numero=12, onFire=true, cantidadImpresa= impresionAlta, jugador=mbappe))
    }
  }

  private fun crearJugador() {
    martinez = Jugador(nombre = "Gonzalo", apellido = "Martinez", fechaNacimiento = LocalDate.of(1993, 6,13), nroCamiseta = 10, seleccionPerteneciente = argentina, posicion=Mediocampista, anioDeDebut=2008, altura=1.72, peso=70.0, esLider=true, cotizacion=9000000.0 )
    otamendi = Jugador(nombre = "Nicolas", apellido = "Otamendi", fechaNacimiento = LocalDate.of(1998, 12,2), nroCamiseta = 3, seleccionPerteneciente = argentina, posicion=Defensor, anioDeDebut=2003, altura=1.83, peso=75.0, esLider=false, cotizacion=1200000.0 )
    argento = Jugador(nombre = "Pepe", apellido = "Argento", fechaNacimiento = LocalDate.of(1993, 2,12), nroCamiseta = 5, seleccionPerteneciente = argentina, posicion=Arquero, anioDeDebut=2012, altura=1.69, peso=69.0, esLider=true, cotizacion=1700000.0 )
    messi = Jugador(nombre = "Lionel", apellido = "Messi", fechaNacimiento = LocalDate.of(1987, 6,24), nroCamiseta = 10, seleccionPerteneciente = argentina, posicion=Delantero, anioDeDebut=2004, altura=1.7, peso=69.0, esLider=true, cotizacion=19003000.0 )
    ramos = Jugador(nombre = "Sergio", apellido = "Ramos", fechaNacimiento = LocalDate.of(1986, 3,30), nroCamiseta = 4, seleccionPerteneciente = espania, posicion=Defensor, anioDeDebut=2002, altura=1.84, peso=75.0, esLider=false, cotizacion=1035000.0 )
    neymar = Jugador(nombre = "Jr", apellido = "Neymar", fechaNacimiento = LocalDate.of(1992, 2,5), nroCamiseta = 11, seleccionPerteneciente = brasil, posicion=Delantero, anioDeDebut=2010, altura=1.75, peso=71.0, esLider=false, cotizacion=39000000.0 )
    lewandowski = Jugador(nombre = "Robert", apellido = "Lewandowski", fechaNacimiento = LocalDate.of(1988, 8,21), nroCamiseta = 9, seleccionPerteneciente = polonia, posicion=Delantero, anioDeDebut=2006, altura=1.84, peso=80.0, esLider=true, cotizacion=1600000.0 )
    hazard = Jugador(nombre = "Eden", apellido = "Hazard", fechaNacimiento = LocalDate.of(1991, 1,7), nroCamiseta = 7, seleccionPerteneciente = belgica, posicion=Delantero, anioDeDebut=2006, altura=1.73, peso=73.0, esLider=true, cotizacion=1100000.0 )
    debruyne = Jugador(nombre = "Kevin", apellido = "De Bruyne", fechaNacimiento = LocalDate.of(1991, 6,28), nroCamiseta = 17, seleccionPerteneciente = belgica, posicion=Mediocampista, anioDeDebut=2008, altura=1.81, peso=76.0, esLider=false, cotizacion=1200000.0 )
    vandijk = Jugador(nombre = "Virgil", apellido = "Can Dijk", fechaNacimiento = LocalDate.of(1991, 7,8), nroCamiseta = 4, seleccionPerteneciente = holanda, posicion=Defensor, anioDeDebut=2009, altura=1.93, peso=72.0, esLider=true, cotizacion=1400000.0 )
    mbappe = Jugador(nombre = "Kylian", apellido = "Mbappé", fechaNacimiento = LocalDate.of(1998, 12,20), nroCamiseta = 7, seleccionPerteneciente = francia, posicion=Delantero, anioDeDebut=2016, altura=1.78, peso=74.0, esLider=false, cotizacion=1700000.0 )

    jugadorRepository.apply {
      create(martinez)
      create(otamendi)
      create(argento)
      create(messi)
      create(ramos)
      create(neymar)
      create(lewandowski)
      create(hazard)
      create(debruyne)
      create(vandijk)
      create(mbappe)
    }
  }

  private fun crearSelecciones() {
    argentina = Seleccion(pais="Argentina", conmebol, copasConfederacion=22, copasDelMundo=3 )
    brasil = Seleccion(pais="Brasil", conmebol, copasConfederacion=22, copasDelMundo=5 )
    espania = Seleccion(pais="España", uefa, copasConfederacion=8, copasDelMundo=4)
    polonia = Seleccion(pais="Polonia", uefa, copasConfederacion=7, copasDelMundo=3)
    belgica = Seleccion(pais="Belgica", uefa, copasConfederacion=3, copasDelMundo=2)
    holanda = Seleccion(pais="Holanda", uefa, copasConfederacion=4, copasDelMundo=2)
    francia = Seleccion(pais="Francia", uefa, copasConfederacion=2, copasDelMundo=1)

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

  private fun crearUsuarios() {

    usuariosRepositorio.apply {
      create(Usuario(
        apellido = "lopez",
        nombre = "sol",
        nombreUsuario = "sol_lop",
        contrasenia = "12345",
        fechaNacimiento = LocalDate.of(2001, 2, 15),
        email = "lopezSol@gmail.com",
        direccion = Direccion(
          provincia = "Buenos Aires",
          localidad = "Tigre",
          calle = "av.Cazon",
          altura = 130,
          ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
        )
      ))
      create(Usuario(
        apellido = "Barneche",
        nombre = "Facundo",
        nombreUsuario = "facu",
        contrasenia = "12345",
        fechaNacimiento = LocalDate.of(1990, 12, 22),
        email = "fh.barneche@gmail.com",
        direccion = Direccion(
          provincia = "Buenos Aires",
          localidad = "3 de Febrero",
          calle = "Calle Falsa",
          altura = 123,
          ubiGeografica = Point(-33.11359068891678, -58.232631824527)
        )
      ))
    }
  }

  override fun afterPropertiesSet() {
    this.crearConfederaciones()
    this.crearSelecciones()
    this.crearJugador()
    this.crearFiguritas()
    this.crearUsuarios()
  }
}