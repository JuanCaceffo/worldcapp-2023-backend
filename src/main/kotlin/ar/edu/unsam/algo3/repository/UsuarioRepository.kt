package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Usuario
import org.springframework.stereotype.Repository

@Repository
class UsuariosRepository: Repositorio<Usuario>() {
    fun inactivos() = elementos.filter{it.value.figuritasFaltantes.isEmpty() && it.value.figuritasRepetidas.isEmpty()}
}
