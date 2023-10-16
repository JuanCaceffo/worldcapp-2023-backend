package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepo: UsuariosRepository) {
    fun login(dataUser: UsuarioLoginDTO): Int {
        val user = usuarioRepo.allInstances().find { user -> user.nombreUsuario == dataUser.userName && user.contrasenia == dataUser.password}
            ?: throw NotFoundException("Las credenciales suministradas son incorrectas")
        return user.id
    }
}