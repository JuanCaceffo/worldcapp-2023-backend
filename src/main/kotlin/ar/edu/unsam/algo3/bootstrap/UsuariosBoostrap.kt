package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.Confederacion
import ar.edu.unsam.algo3.domain.Seleccion
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(2)
@Component
class UsuariosBoostrap (
  val usuariosRepositorio: UsuariosRepository
): InitializingBean {

  private val usuarios = mapOf(
//

  )
  fun crearUsuarios() {

//    usuariosRepositorio.apply {
//      create(
//        Usuario(
//          apellido = "lopez",
//          nombre = "sol",
//          nombreUsuario = "sol_lop",
//          contrasenia = "12345",
//          fechaNacimiento = LocalDate.of(2001, 2, 15),
//          email = "lopezSol@gmail.com",
//          direccion = Direccion(
//            provincia = "Buenos Aires",
//            localidad = "Tigre",
//            calle = "av.Cazon",
//            altura = 130,
//            ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
//          )
//        )
//      )
    }

  override fun afterPropertiesSet() {
    TODO("Not yet implemented")
  }
}