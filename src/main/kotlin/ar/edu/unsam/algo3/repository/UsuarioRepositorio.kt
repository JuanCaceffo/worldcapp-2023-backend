package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Usuario

class RepositorioUsuarios: Repositorio<Usuario>() {
    fun inactivos() = elementos.filter{it.value.figuritasFaltantes.isEmpty() && it.value.figuritasRepetidas().isEmpty()}
}
