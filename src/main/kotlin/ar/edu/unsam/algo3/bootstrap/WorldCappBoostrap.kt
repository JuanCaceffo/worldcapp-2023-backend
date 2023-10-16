package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.uqbar.geodds.Point
import java.time.LocalDate

class WorldCappBoostrap (
  val figuritasRepository: FiguritasRepository,
  val jugadorRepository: JugadorRepository,
  val seleccionesRepository: SeleccionesRepository,
  val confeRepository: MutableList<Confederacion>,
   val usuariosRepositorio: UsuariosRepository,
  // val puestosDeVentaRepositorio: PuestoDeVentaRepositorio,
): InitializingBean {

  private lateinit var argentina: Seleccion

  private lateinit var afc: Confederacion
  private lateinit var caf: Confederacion
  private lateinit var concacaf: Confederacion
  private lateinit var conmebol: Confederacion
  private lateinit var ofc: Confederacion
  private lateinit var uefa: Confederacion

  fun crearFiguritas() {
    figuritasRepository.apply {
      create(Figurita(numero=1, onFire=true, cantidadImpresa=impresionBaja, jugador=jugadorRepository.getById(1)!!))
    }
  }

  private fun crearJugador() {
    jugadorRepository.apply {
      create(Jugador(nombre = "Gonzalo", apellido = "Martinez", fechaNacimiento = LocalDate.of(1993, 6,13), nroCamiseta = 10, seleccionPerteneciente = argentina, posicion=Mediocampista, anioDeDebut=2008, altura=1.72, peso=70.0, esLider=true, cotizacion=9000000.0 ) )
    }
  }

  private fun crearSelecciones() {
    argentina = Seleccion(pais="Argentina", conmebol, copasConfederacion=22, copasDelMundo=3 )

    seleccionesRepository.apply {
      create(argentina)
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

  private fun crearUsuario() {
    val sol = Usuario(
      apellido = "lopez",
      nombre = "sol",
      nombreUsuario = "sol_lop",
      contrasenia = "1234",
      fechaNacimiento = LocalDate.of(2001, 2, 15),
      email = "lopezSol@gmail.com",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "Tigre",
        calle = "av.Cazon",
        altura = 130,
        ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
      )
    )
    usuariosRepositorio.create(sol)
  }
  override fun afterPropertiesSet() {
    this.crearConfederaciones()
    this.crearSelecciones()
    this.crearJugador()
    this.crearFiguritas()
    this.crearUsuario()
  }
}