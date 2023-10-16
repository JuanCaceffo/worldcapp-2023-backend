package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.dto.PuntosDeVentaDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(val puntosDeVentaRepository: PuntosDeVentaRepository) {
    fun getAll(): List<PuntosDeVentaDTO> = puntosDeVentaRepository.getAll().map { pup -> pup.toDTO() }
}