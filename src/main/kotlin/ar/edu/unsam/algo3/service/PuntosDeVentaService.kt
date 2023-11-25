package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroPuntoDeVenta
import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.dto.MarketCardDTO
import ar.edu.unsam.algo3.dto.SalesPointCardDTO
import ar.edu.unsam.algo3.dto.toMarketCardDTO
import ar.edu.unsam.algo3.dto.toSalesPointCardDTO
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

  fun getAllSalesPoint(): List<SalesPointCardDTO> = puntosDeVentaRepository.getAll().map { it.toSalesPointCardDTO() }

  fun obtenerPuntosDeVentaFiltrados(userLogedID: Int,filtro: FiltroPuntoDeVenta): List<MarketCardDTO> {
    var listaOrdenada = getAll(userLogedID)

    when (filtro.opcionElegida) {
      "Menor Distancia" -> listaOrdenada = mapToDTO(
        userLogedID,
        puntosDeVentaRepository.ordenarPorMenorDistancia(usuariosRepository.getById(userLogedID))
      )

      "Más Barato" -> listaOrdenada = mapToDTO(
        userLogedID,
        puntosDeVentaRepository.ordenarPorMasBarato(usuariosRepository.getById(userLogedID))
      )

      "Más Sobres" -> listaOrdenada = mapToDTO(userLogedID, puntosDeVentaRepository.ordenarPorMasSobres()).reversed()

      "Sólo más Cercanos" -> listaOrdenada = mapToDTO(
        userLogedID,
        puntosDeVentaRepository.ordenarPorSoloMasCercanos(usuariosRepository.getById(userLogedID))
      )
    }

    if (filtro.palabraClave != "") {
      listaOrdenada = filtroPalabraClave(filtro.palabraClave, listaOrdenada)
      println(listaOrdenada)
    }

    return listaOrdenada
  }

  fun mapToDTO(userId: Int, lista: List<PuntoDeVenta>) =
    lista.map { it.toMarketCardDTO(usuariosRepository.getById(userId)) }

  fun filtroPalabraClave(palabra: String, lista: List<MarketCardDTO>): List<MarketCardDTO> {
    val idStore = puntosDeVentaRepository.search(palabra).map { it.id }
    return lista.filter { it.id in idStore }
  }

}