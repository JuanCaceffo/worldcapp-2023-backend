package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.error.BussinesExpetion
import org.uqbar.geodds.Point
import java.time.LocalDate

enum class TipoFiguList{
    FALTANTES,
    REPETIDAS
}

data class UsuarioLoginDTO (val userName: String, val password: String)
data class UsuarioLogeadoDTO (val userLogedID: Int)
data class UsuarioFiguDTO (val idUsuario: Int, val duenio: String)
data class RequestFiguDTO(val userLogedID: Int, val requestedUserID: Int, val requestedFiguID: Int)
data class AddFiguDTO(val userLogedID: Int, val FiguID: Int)
data class UsuarioInfoProfileDTO(var name: String, val lastName: String, val email: String, val birthdate: String, val address: Direccion, val exchangeProximity: Int, val criteria: String)
//TODO: Ver location en UsuarioInfoDTO ya que si el usuario la cambia de info general debe cambiar aca
data class UsuarioInfoDTO(var username: String, val age: Int, var location: String, val picturePath: String)

fun Usuario.loginResponseDTO() = UsuarioLogeadoDTO(this.id)
fun Usuario.dataFiguritaDTO() = UsuarioFiguDTO(this.id, this.nombreUsuario)
fun Usuario.toInfoProfileDTO() = UsuarioInfoProfileDTO(this.nombre, this.apellido, this.email, this.fechaNacimiento.toString(), this.direccion, this.distanciaMaximaCercania, this.condicionParaDar.criterioParaCambio())
fun Usuario.toUserInfoDTO() = UsuarioInfoDTO(this.nombreUsuario, this.edad(), this.direccion.localidad, this.imagenPath)
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
        "Desprendido"   to Desprendido(this),
        "Par"           to Par(this),
        "Nacionalista"  to Nacionalista(this),
        "Conservador"   to Conservador(this),
        "Apostador"     to Apostador(this),
        "Interesado"    to Interesado(this),
        "Cambiante"     to Cambiante(this),
        "Fanatico"      to Fanatico(this)
    )

    val criterioIntercambio: CondicionesParaDar = condicionesMap[infoProfile.criteria]
        ?: throw BussinesExpetion("Criterio de intercambio inexistente")

    this.modificarComportamientoIntercambio(criterioIntercambio)

}