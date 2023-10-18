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
    fun getAll(userId: Int): List<PuntoDeVentatoMarketCardDTO> =
        puntosDeVentaRepository.getAll().map { it.toMarketCardDTO(usuariosRepository.getById(userId)) }
}