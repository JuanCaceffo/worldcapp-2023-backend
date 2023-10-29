package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroPuntoDeVenta
import ar.edu.unsam.algo3.domain.PuntoDeVenta
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

  fun obtenerPuntosDeVentaFiltrados(filtro: FiltroPuntoDeVenta): List<MarketCardDTO> {
    var listaOrdenada = getAll(filtro.idUsuario)
    println(filtro)
    if (filtro.palabraClave != "") {
      listaOrdenada = filtroPalabraClave(filtro.palabraClave, listaOrdenada)
    }

    when (filtro.opcionElegida) {
      "Menor Distancia" -> listaOrdenada = mapToDTO(
        filtro.idUsuario,
        puntosDeVentaRepository.ordenarPorMenorDistancia(usuariosRepository.getById(filtro.idUsuario))
      )

      "Más Barato" -> listaOrdenada = mapToDTO(
        filtro.idUsuario,
        puntosDeVentaRepository.ordenarPorMasBarato(usuariosRepository.getById(filtro.idUsuario))
      )

      "Más Sobres" -> listaOrdenada = mapToDTO(filtro.idUsuario, puntosDeVentaRepository.ordenarPorMasSobres())

      "Sólo más Cercanos" -> listaOrdenada = mapToDTO(
        filtro.idUsuario,
        puntosDeVentaRepository.ordenarPorSoloMasCercanos(usuariosRepository.getById(filtro.idUsuario))
      )
    }

    return listaOrdenada
  }

  fun mapToDTO(userId: Int, lista: List<PuntoDeVenta>) =
    lista.map { it.toMarketCardDTO(usuariosRepository.getById(userId)) }

  fun filtroPalabraClave(palabra: String, lista: List<MarketCardDTO>): List<MarketCardDTO> {
    val idFiguritas = encontrarFiguritaPorPalabraClave(palabra).map { it.id }
    return lista.filter { it.id in idFiguritas }
  }

  fun encontrarFiguritaPorPalabraClave(palabra: String) = puntosDeVentaRepository.search(palabra)
}