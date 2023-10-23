package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.error.BussinesExpetion
import jakarta.validation.constraints.Email
import org.uqbar.geodds.Point
import java.time.LocalDate

data class UsuarioLoginDTO (val userName: String, val password: String)
data class UsuarioLogeadoDTO (val userLogedID: Int)
data class UsuarioFiguDTO (val idUsuario: Int, val duenio: String)
data class RequestFiguDTO(val userLogedID: Int, val requestedUserID: Int, val requestedFiguID: Int)
data class UsuarioInfoProfileDTO(var name: String, val lastName: String, val email: String, val birthdate: String, val address: Direccion, val exchangeProximity: Int, val criteria: String)

fun Usuario.loginResponseDTO() = UsuarioLogeadoDTO(this.id)
fun Usuario.dataFiguritaDTO() = UsuarioFiguDTO(this.id, this.nombreUsuario)
fun Usuario.toInfoProfileDTO() = UsuarioInfoProfileDTO(this.nombre, this.apellido, this.email, this.fechaNacimiento.toString(), this.direccion, this.distanciaMaximaCercania, this.condicionParaDar.criterioParaCambio())
fun Usuario.setInfoProfileDTO(infoProfile: UsuarioInfoProfileDTO){
    this.nombre = infoProfile.name
    this.apellido = infoProfile.lastName
    this.email = infoProfile.email
    this.fechaNacimiento = LocalDate.parse(infoProfile.birthdate)
    this.direccion = Direccion(
        provincia = infoProfile.address.provincia,
        localidad = infoProfile.address.localidad,
        calle = infoProfile.address.calle,
        altura = infoProfile.address.altura,
        ubiGeografica = Point(infoProfile.address.ubiGeografica.x, infoProfile.address.ubiGeografica.y))
    this.distanciaMaximaCercania = infoProfile.exchangeProximity

    val condicionesMap: Map<String, CondicionesParaDar> = mapOf(
        "Desprendido"   to Desprendido(),
        "Par"           to Par(),
        "Nacionalista"  to Nacionalista(this),
        "Conservador"   to Conservador(this),
        "Apostador"     to Apostador(),
        "Interesado"    to Interesado(this),
        "Cambiante"     to Cambiante(this),
        "Fanatico"      to Fanatico(this)
    )

    val criterioIntercambio: CondicionesParaDar = condicionesMap[infoProfile.criteria]
        ?: throw BussinesExpetion("Criterio de intercambio inexistente")

    this.modificarComportamientoIntercambio(criterioIntercambio)

}