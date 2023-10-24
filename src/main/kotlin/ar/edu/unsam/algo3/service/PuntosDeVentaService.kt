package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.domain.FiltroPuntoDeVenta
import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.dto.MarketCardDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(
    val puntosDeVentaRepository: PuntosDeVentaRepository,
    val usuariosRepository: UsuariosRepository
) {
    fun getAll(userId: Int): List<MarketCardDTO> =
        puntosDeVentaRepository.getAll().map { it.toMarketCardDTO(usuariosRepository.getById(userId)) }

    fun obtenerPuntosDeVentaFiltrados(userId: Int, filtro: FiltroPuntoDeVenta): List<MarketCardDTO> {
        var listaOrdenada = getAll(userId)

        if (filtro.palabraClave != null) {
            listaOrdenada = filtroPalabraClave(filtro.palabraClave!!, listaOrdenada)
        }

        if (filtro.masBarato!!) {
            listaOrdenada = mapToDTO(userId, puntosDeVentaRepository.ordenarPorMasBarato(usuariosRepository.getById(userId)))
        }

        if (filtro.masSobre!!) {
            listaOrdenada = mapToDTO(userId, puntosDeVentaRepository.ordenarPorMasSobres())
        }

        if (filtro.menorDistancia!!) {
            listaOrdenada = mapToDTO(userId, puntosDeVentaRepository.ordenarPorMenorDistancia(usuariosRepository.getById(userId)))
        }

        if (filtro.soloMasCercanos!!) {
            listaOrdenada = mapToDTO(userId, puntosDeVentaRepository.ordenarPorSoloMasCercanos(usuariosRepository.getById(userId)))
        }

        return listaOrdenada
    }

    fun mapToDTO(userId: Int, lista: List<PuntoDeVenta>) = lista.map { it.toMarketCardDTO(usuariosRepository.getById(userId)) }

    fun filtroPalabraClave(palabra: String, lista: List<MarketCardDTO>): List<MarketCardDTO> {
        val idFiguritas = encontrarFiguritaPorPalabraClave(palabra).map { it.id }
        return lista.filter { it.id in idFiguritas }
    }

    fun encontrarFiguritaPorPalabraClave(palabra: String) = puntosDeVentaRepository.search(palabra)
}