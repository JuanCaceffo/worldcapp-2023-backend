package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Usuario
import org.uqbar.geodds.Point
import java.time.LocalDate

data class UsuarioLoginDTO (val userName: String, val password: String)
data class UsuarioLogeadoDTO (val userLogedID: Int)
data class UsuariotoMarketCardDTO(val ubiGeografica: Point)

fun Usuario.loginResponseDTO() = UsuarioLogeadoDTO(this.id)
fun Usuario.toMarketCardDTO() = UsuariotoMarketCardDTO(this.direccion.ubiGeografica)
