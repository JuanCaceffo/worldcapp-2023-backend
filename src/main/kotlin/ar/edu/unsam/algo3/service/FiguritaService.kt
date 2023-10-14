package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.repository.Repositorio
import org.springframework.stereotype.Service

@Service
class FiguritaService (
  val figuritaRepository: Repositorio<Figurita>,
  val usuarioRepository: Repositorio<Usuario>
){
  fun search(textoBusqueda: String) = figuritaRepository.search(textoBusqueda)
}