package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.MarketFilterParams
import ar.edu.unsam.algo3.domain.Filtro
import ar.edu.unsam.algo3.domain.FiltroPalabraClavePuntoDeVenta
import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.dto.MarketCardDTO
import ar.edu.unsam.algo3.dto.MarketDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.dto.toMarketDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class PuntosDeVentaService(
  val puntosDeVentaRepository: PuntosDeVentaRepository,
  val usuariosRepository: UsuariosRepository
) {
  fun getAll(params: MarketFilterParams): List<MarketDTO> {
    val puntosDeVenta = puntosDeVentaRepository.getAll()
    return filtrar(puntosDeVenta, params).map {
      it.toMarketDTO()
    }
  }

  fun puntosDeVentaOrdenados(userId: Int, params: MarketFilterParams): List<MarketCardDTO> {
    var listaOrdenada: List<PuntoDeVenta> = mutableListOf()

    when (params.opcionElegida) {
      "Menor Distancia" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMenorDistancia(usuariosRepository.getById(userId))
      "Más Barato" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMasBarato(usuariosRepository.getById(userId))
      "Más Sobres" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMasSobres().reversed()
      "Sólo más Cercanos" -> listaOrdenada =
        puntosDeVentaRepository.ordenarPorSoloMasCercanos(usuariosRepository.getById(userId))
    }

    return filtrar(listaOrdenada, params).map {
      it.toMarketCardDTO(usuariosRepository.getById(userId))
    }
  }

  fun crearFiltroMarket(params: MarketFilterParams): Filtro<PuntoDeVenta> {
    return Filtro<PuntoDeVenta>().apply {
      addCondiconFiltrado(FiltroPalabraClavePuntoDeVenta(params.palabraClave, puntosDeVentaRepository))
    }
  }

  fun filtrar(markets: List<PuntoDeVenta>, params: MarketFilterParams): List<PuntoDeVenta> {
    val filtro = crearFiltroMarket(params)
    return markets.filter { market -> filtro.cumpleCondiciones(market) }
  }
}