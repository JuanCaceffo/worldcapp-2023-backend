package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.dto.dataFiguritaDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class FiguritaService(
  val figuritaRepository: FiguritasRepository,
  val usuariosRepository: UsuariosRepository
) {
  fun obtenerFiguritasParaIntercambiar(filtro: FiltroFigurita): List<FiguritaDTO> {
    val otros = this.otrosUsuarios(filtro.idUsuario)
    var listaFiltrada = otros.flatMap { it.listaFiguritasARegalar().map { figu -> figu.toDTO(it.dataFiguritaDTO()) } }

    if (filtro.palabraClave != null) {
      listaFiltrada = filtroPalabraClave(filtro.palabraClave!!, listaFiltrada)
    }

    if (filtro.onFire!!) {
      listaFiltrada = listaFiltrada.filter { it.onFire }
    }

    if (filtro.esPromesa!!) {
      listaFiltrada = listaFiltrada.filter { it.promesa }
    }

    if ((0.0..0.0) != filtro.rangoCotizacion) {
      listaFiltrada = listaFiltrada.filter { it.valoracion in filtro.rangoCotizacion }
    }

    return listaFiltrada
  }

  fun otrosUsuarios(miID: Int) = usuariosRepository.getAll().filter { it.id != miID }

  fun filtroPalabraClave(palabra: String, lista: List<FiguritaDTO>): List<FiguritaDTO> {
    val idFiguritas = encontrarFiguritaPorPalabraClave(palabra).map { it.id }
    return lista.filter { it.id in idFiguritas }
  }

  fun encontrarFiguritaPorPalabraClave(palabra: String) = figuritaRepository.search(palabra)
}