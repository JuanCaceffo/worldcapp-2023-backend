package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.UsuarioInfoProfileDTO
import ar.edu.unsam.algo3.dto.toInfoProfileDTO
import org.springframework.stereotype.Repository

@Repository
class UsuariosRepository: Repositorio<Usuario>() {
    fun inactivos() = elementos.filter{it.value.figuritasFaltantes.isEmpty() && it.value.figuritasRepetidas().isEmpty()}
    fun editProfileInfo(infoProfile: UsuarioInfoProfileDTO, id: Int): Usuario = this.getById(id)
}
