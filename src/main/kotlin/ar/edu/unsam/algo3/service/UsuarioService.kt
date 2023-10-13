package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class UsuarioService(val usuarioRepo: UsuariosRepository) {
    fun login(dataUser: UsuarioLoginDTO) {

    }

}