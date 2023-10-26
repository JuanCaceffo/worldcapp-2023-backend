package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service


val ERROR_MSG_INVALID_CREDENTIALS = "Las credenciales suministradas son incorrectas"
val ERROR_MSG_INVALID_REQUESTED_FIGU = "El usuario no posee la figurita solicitada"
val ERROR_MSG_DONT_REMOVE_FIGURITA = "La figurita no fue eliminada con exito ya que no se encontró en la lista"

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

    fun getGiftableFigurita(figuId: Int, userID: Int): FiguritaDTO {
        val user = searchByID(userID)
        return findGiftableFigurita(figuId, user).toDTO(user.dataFiguritaDTO())
    }

    fun figuritaRequest(requestData: RequestFiguDTO) {
        val logedUser = searchByID(requestData.userLogedID)
        val userRequested = searchByID(requestData.requestedUserID)
        val figuRequested = findGiftableFigurita(requestData.requestedFiguID, userRequested)

        logedUser.pedirFigurita(figuRequested, userRequested)
    }

    fun getProfileInfo(id: Int): UsuarioInfoProfileDTO = searchByID(id).toInfoProfileDTO()
    fun getUserInfo(id: Int): UsuarioInfoDTO = searchByID(id).toUserInfoDTO()
    fun editProfileInfo(infoProfile: UsuarioInfoProfileDTO, id: Int): UsuarioInfoProfileDTO {
        val user = searchByID(id)
        user.setInfoProfileDTO(infoProfile)
        return user.toInfoProfileDTO()
    }

    fun getFigusList(id: Int, figusList: TipoFiguList): List<FiguritaDTO> {
        val user = searchByID(id)
        val list: List<Figurita>
        when (figusList) {
            TipoFiguList.FALTANTES -> {
                list = user.figuritasFaltantes.toList()
            }

            TipoFiguList.REPETIDAS -> {
                list = user.figuritasRepetidas
            }
        }
        return list.map { figu -> figu.toDTO(user.dataFiguritaDTO()) }
    }

    fun deleteFigurita(userID: Int, figuID: Int, figusList: TipoFiguList) {
        fun validiationRemove(isRemoved:Boolean){
            if (!isRemoved) throw BussinesExpetion(ERROR_MSG_DONT_REMOVE_FIGURITA)
        }
        val user = searchByID(userID)
        when (figusList) {
            TipoFiguList.FALTANTES -> {
                val isRemoved = user.figuritasFaltantes.removeIf { figu -> figu.id == figuID }
                validiationRemove(isRemoved)
            }

            TipoFiguList.REPETIDAS -> {
                val isRemoved = user.figuritasRepetidas.removeIf { figu -> figu.id == figuID }
                validiationRemove(isRemoved)
            }
        }
    }
}