package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Usuario
import org.uqbar.geodds.Point
import java.time.LocalDate

data class UsuarioLoginDTO (val userName: String, val password: String)

fun Usuario.loginDTO() = UsuarioLoginDTO(this.nombreUsuario,this.contrasenia)
