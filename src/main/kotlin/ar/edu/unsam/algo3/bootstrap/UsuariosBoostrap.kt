package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Nacionalista
import ar.edu.unsam.algo3.domain.Par
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.DependsOn
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.uqbar.geodds.Point
import java.time.LocalDate

@Component
@Order(1)
@DependsOn("figuritasBoostrap")
class UsuariosBoostrap(
  val usuariosRepositorio: UsuariosRepository,
  val figuritaRepositorio: FiguritasRepository,
  val seleccionesRepositorio: SeleccionesRepository
) : InitializingBean {
  private val usuarios = mapOf(
    "Sol" to Usuario(
      apellido = "lopez",
      nombre = "sol",
      nombreUsuario = "sol",
      contrasenia = "12345",
      fechaNacimiento = LocalDate.of(2001, 2, 15),
      email = "lopezSol@gmail.com",
      imagenPath = "assets/images/user-profile/sol.jpg",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "Tigre",
        calle = "av.Cazon",
        altura = 130,
        ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
      )
    ), "Pablo" to Usuario(
      apellido = "pablo",
      nombre = "foglia",
      nombreUsuario = "madescoses",
      contrasenia = "12345",
      fechaNacimiento = LocalDate.of(2000, 2, 1),
      email = "madescoses@gmail.com",
      imagenPath = "assets/images/user-profile/pablito.jpg",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "San Martin",
        calle = "matheu",
        altura = 3568,
        ubiGeografica = Point(-34.57461948921918, -58.5378840940197)
      )
    ), "Juan" to Usuario(
      apellido = "juan",
      nombre = "caceffo",
      nombreUsuario = "juanceto01",
      contrasenia = "12345",
      fechaNacimiento = LocalDate.of(2003, 2, 1),
      email = "juanchi@gmail.com",
      imagenPath = "assets/images/user-profile/juanchi.jpg",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "San Martin",
        calle = "Av. Rodríguez Peña",
        altura = 3237,
        ubiGeografica = Point(-34.58424206690573, -58.52112943577023)
      )
    ), "Facundito" to Usuario(
      apellido = "Barneche",
      nombre = "Facundo",
      nombreUsuario = "facu",
      contrasenia = "12345",
      fechaNacimiento = LocalDate.of(1990, 12, 22),
      email = "fh.barneche@gmail.com",
      imagenPath = "assets/images/user-profile/facu.jpg",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "3 de Febrero",
        calle = "Calle Falsa",
        altura = 123,
        ubiGeografica = Point(-33.11359068891678, -58.232631824527)
      )
    ), "Alejo" to Usuario(
      apellido = "alejo",
      nombre = "menini",
      nombreUsuario = "nestorKishner",
      contrasenia = "12345",
      fechaNacimiento = LocalDate.of(2001, 5, 15),
      email = "alete@gmail.com",
      imagenPath = "assets/images/user-profile/ale.jpg",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "General Pacheco",
        calle = "chaco",
        altura = 130,
        ubiGeografica = Point(-34.46067347399887, -58.63219996237826)
      )
    )
  )

  fun crearUsuarios() {
    usuarios.values.forEach { usuario -> usuariosRepositorio.apply { create(usuario) } }
  }

  fun obtenerIdsFiguritasCreadas() = figuritaRepositorio.getAll().map { it.id }

  fun agregarFiguritasFaltantes() {
    usuarios["Pablo"]!!.addFiguritaFaltante(figuritaRepositorio.getById(10))
    usuarios["Pablo"]!!.addFiguritaFaltante(figuritaRepositorio.getById(12))
    usuarios["Sol"]!!.addFiguritaFaltante(figuritaRepositorio.getById(1))
    usuarios["Facundito"]!!.addFiguritaFaltante(figuritaRepositorio.getById(3))
    usuarios["Facundito"]!!.addFiguritaFaltante(figuritaRepositorio.getById(4))
    usuarios["Facundito"]!!.addFiguritaFaltante(figuritaRepositorio.getById(2))
    usuarios["Juan"]!!.addFiguritaFaltante(figuritaRepositorio.getById(5))
    usuarios["Juan"]!!.addFiguritaFaltante(figuritaRepositorio.getById(6))
    usuarios["Juan"]!!.addFiguritaFaltante(figuritaRepositorio.getById(8))
    usuarios["Alejo"]!!.addFiguritaFaltante(figuritaRepositorio.getById(13))
    usuarios["Alejo"]!!.addFiguritaFaltante(figuritaRepositorio.getById(14))
  }

  fun agregarFigusEnUsuariosCreados() {
    seleccionarFigus(8,"Pablo")
    seleccionarFigus(7,"Sol", 3)
    seleccionarFigus(6,"Facundito", 5)
    seleccionarFigus(4,"Juan", 11)
    seleccionarFigus(5,"Alejo", 5)
  }

  fun seleccionarFigus(cantidad:Int, usuario:String, desde:Int = 0 ) {
    repeat(cantidad) { usuarios[usuario]!!.addFiguritaRepetida(figuritaRepositorio.getById(it + obtenerIdsFiguritasCreadas()[desde]))}
  }

  fun cambiarCondicionParaDar() {
    usuarios["Juan"]?.modificarComportamientoIntercambio(Nacionalista(usuarios["Juan"]!!))
    usuarios["Alejo"]?.modificarComportamientoIntercambio(Par(usuarios["Alejo"]!!))
    usuarios["Juan"]?.addSeleccionFavoritas(seleccionesRepositorio.search("Francia").first())
  }

  override fun afterPropertiesSet() {
    println("INICIO EL PROCESO DE CREACIÓN DE USUARIOS")
    this.crearUsuarios()
    this.agregarFigusEnUsuariosCreados()
    this.agregarFiguritasFaltantes()
    this.cambiarCondicionParaDar()
    println("FIN DE USUARIOS.")
  }
}