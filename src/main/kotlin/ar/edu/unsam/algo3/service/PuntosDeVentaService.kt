package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.PuntoDeVentatoMarketCardDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(
    val puntosDeVentaRepository: PuntosDeVentaRepository,
    val usuariosRepository: UsuariosRepository
) {
    fun getAll(userId: Int): List<PuntoDeVentatoMarketCardDTO> {
        //TODO: Ver de donde obtener el usuario logeado para incluir al path en el front
        //Obtenemos el primer usuario (debería ser el unico de la collection) que cumpla con la condición
        val user = usuariosRepository.elementos.filter { it.value.id == userId  }.values
        if(user.isEmpty()){
            throw NotFoundException("No se encontró el usuario")
        }
        return puntosDeVentaRepository.getAll().map { pup -> pup.toMarketCardDTO(user.first()) }
    }
}