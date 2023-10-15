package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(val puntosDeVentaRepository: PuntosDeVentaRepository) {
    fun getAllPuntosDeVenta() = puntosDeVentaRepository.findAll()
}