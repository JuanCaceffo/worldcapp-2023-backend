package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.UsuarioLogeadoDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.dto.loginResponseDTO
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service
import kotlin.reflect.typeOf

@Service
class UsuarioService(val usuarioRepo: UsuariosRepository) {
    fun login(dataUser: UsuarioLoginDTO): UsuarioLogeadoDTO {
        //TODO: hacer el filtrado en repository para no guardar todos los usarios en memoria del sv
        val user = usuarioRepo.getAll().find { user -> user.nombreUsuario == dataUser.userName && user.contrasenia == dataUser.password}
            ?: throw NotFoundException("Las credenciales suministradas son incorrectas")
        return user.loginResponseDTO()
    }
    fun searchByID(id: Int): Usuario = usuarioRepo.getById(id)

}