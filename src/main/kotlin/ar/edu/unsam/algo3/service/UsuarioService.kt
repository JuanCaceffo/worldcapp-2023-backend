package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.RequestFiguDTO
import ar.edu.unsam.algo3.dto.UsuarioLogeadoDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.dto.loginResponseDTO
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service


val ERROR_MSG_INVALID_CREDENTIALS = "Las credenciales suministradas son incorrectas"
val ERROR_MSG_INVALID_REQUESTED_FIGU = "El usuario no posee la figurita solicitada"

@Service
class UsuarioService(val usuarioRepo: UsuariosRepository) {
    fun login(dataUser: UsuarioLoginDTO): UsuarioLogeadoDTO {
        //TODO: hacer el filtrado en repository para no guardar todos los usarios en memoria del sv
        val user = usuarioRepo.getAll()
            .find { user -> user.nombreUsuario == dataUser.userName && user.contrasenia == dataUser.password }
            ?: throw NotFoundException(ERROR_MSG_INVALID_CREDENTIALS)
        return user.loginResponseDTO()
    }

    fun searchByID(id: Int): Usuario = usuarioRepo.getById(id)

    fun figuritaRequest(requestData: RequestFiguDTO) {
        val logedUser = searchByID(requestData.userLogedID)
        val userRequested = searchByID(requestData.requestedUserID)
        val figuRequested = userRequested.listaFiguritasARegalar().find { it.id == requestData.requestedFiguID }
            ?: throw NotFoundException(ERROR_MSG_INVALID_REQUESTED_FIGU)

        logedUser.pedirFigurita(figuRequested,userRequested)
    }

}