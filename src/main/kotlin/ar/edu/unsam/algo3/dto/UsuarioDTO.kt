package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Usuario
import jakarta.validation.constraints.Email

data class UsuarioLoginDTO (val userName: String, val password: String)
data class UsuarioLogeadoDTO (val userLogedID: Int)
data class UsuarioFiguDTO (val idUsuario: Int, val duenio: String)
data class RequestFiguDTO(val userLogedID: Int, val requestedUserID: Int, val requestedFiguID: Int)
data class UsuarioInfoProfileDTO(val name: String, val lastName: String, val email: String, val birthdate: String, val address: Direccion, val exchangeProximity: Int, val criteria: String)

fun Usuario.loginResponseDTO() = UsuarioLogeadoDTO(this.id)
fun Usuario.dataFiguritaDTO() = UsuarioFiguDTO(this.id, this.nombreUsuario)
fun Usuario.UsuarioInfoProfileDTO() = UsuarioInfoProfileDTO(this.nombre, this.apellido, this.email, this.fechaNacimiento.toString(), this.direccion, this.distanciaMaximaCercania, this.condicionParaDar.criterioParaCambio() )