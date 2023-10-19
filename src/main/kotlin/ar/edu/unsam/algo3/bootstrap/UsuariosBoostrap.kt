package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point
import java.time.LocalDate

@Order(2)
@Service
class UsuariosBoostrap(
  val usuariosRepositorio: UsuariosRepository,
  val figuritaRepositorio: FiguritasRepository
) : InitializingBean {

  private val usuarios = mapOf(
    "Sol" to Usuario(
      apellido = "lopez",
      nombre = "sol",
      nombreUsuario = "sol_lop",
      fechaNacimiento = LocalDate.of(2001, 2, 15),
      email = "lopezSol@gmail.com",
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
      fechaNacimiento = LocalDate.of(2000, 2, 1),
      email = "madescoses@gmail.com",
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
      fechaNacimiento = LocalDate.of(2003, 2, 1),
      email = "juanchi@gmail.com",
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
      nombreUsuario = "roberto_gomez",
      fechaNacimiento = LocalDate.of(1990, 12, 22),
      email = "robertoGomez@gmail.com",
      direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "Tigre",
        calle = "av.Cazon",
        altura = 130,
        ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
      )
    ), "Alejo" to Usuario(
      apellido = "alejo",
      nombre = "menini",
      nombreUsuario = "nestorKishner",
      fechaNacimiento = LocalDate.of(2001, 5, 15),
      email = "alete@gmail.com",
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

  fun agregarFigusEnUsuariosCreados() {
    repeat(4) { usuarios["Pablo"]!!.addFiguritaRepetida(figuritaRepositorio.getById(it)) }
    repeat(2) { usuarios["Sol"]!!.addFiguritaRepetida(figuritaRepositorio.getById(it + 3)) }
    repeat(6) { usuarios["Facundito"]!!.addFiguritaRepetida(figuritaRepositorio.getById(it + 7)) }
    repeat(3) { usuarios["Juan"]!!.addFiguritaRepetida(figuritaRepositorio.getById(it + 10)) }
    repeat(5) { usuarios["Alejo"]!!.addFiguritaRepetida(figuritaRepositorio.getById(it + 5)) }
  }

  override fun afterPropertiesSet() {
    this.crearUsuarios()
    this.agregarFigusEnUsuariosCreados()
  }
}