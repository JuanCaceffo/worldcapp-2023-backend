package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.*
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

    fun findGiftableFigurita(figuId: Int, user: Usuario): Figurita {
         return user.listaFiguritasARegalar().find { it.id == figuId }
            ?: throw NotFoundException(ERROR_MSG_INVALID_REQUESTED_FIGU)
    }

    fun getGiftableFigurita (figuId: Int, userID: Int): FiguritaDTO{
        val user = searchByID(userID)
        return findGiftableFigurita(figuId,user).toDTO(user.dataFiguritaDTO())
    }
    fun figuritaRequest(requestData: RequestFiguDTO) {
        val logedUser = searchByID(requestData.userLogedID)
        val userRequested = searchByID(requestData.requestedUserID)
        val figuRequested = findGiftableFigurita(requestData.requestedFiguID,userRequested)

        logedUser.pedirFigurita(figuRequested,userRequested)
    }

    fun getProfileInfo(id: Int): UsuarioInfoProfileDTO = usuarioRepo.getById(id).toInfoProfileDTO()
    fun editProfileInfo(infoProfile: UsuarioInfoProfileDTO, id: Int): UsuarioInfoProfileDTO {
        val user = usuarioRepo.getById(id)
        user.setInfoProfileDTO(infoProfile)
        return user.toInfoProfileDTO()
    }
}