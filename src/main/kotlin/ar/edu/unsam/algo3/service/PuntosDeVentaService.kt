package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.PuntosDeVentatoMarketCardDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(
    val puntosDeVentaRepository: PuntosDeVentaRepository,
    val usuariosRepository: UsuariosRepository
) {
    fun getAll(userName: String): List<PuntosDeVentatoMarketCardDTO> {
        //TODO: Manejar error si no se encuentra usuario
        //TODO: Lanzar 401 al mandar por request otro usuario del logeado (mas alla que exista o no)
        //CONSULTA: Mando 401?? o es mucha info? mejor otro tipo de error? un 400?
        //Opinion personal: Devolveria en caso que no exista usuario o que exista pero no se el logeado
        //el mismo error (400).
        //TODO: Ver de donde obtener el usuario logeado para incluir al path
        //Obtenemos el primer usuario (debería ser el unico de la collection) que cumpla con la condición
        val user = usuariosRepository.elementos.filter { it.value.nombreUsuario == userName  }.values.first()
        return puntosDeVentaRepository.getAll().map { pup -> pup.toMarketCardDTO(user) }
    }
}