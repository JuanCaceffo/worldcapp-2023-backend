package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class FiguritaService(
  val figuritaRepository: FiguritasRepository,
  val usuariosRepository: UsuariosRepository
) {
  fun filtrarFigus(figus: List<FiguritaDTO>, filtro: FiltroFigurita): List<FiguritaDTO>{
    var listaFiltrada = figus

    if (filtro.palabraClave != "") {
      listaFiltrada = filtroPalabraClave(filtro.palabraClave, listaFiltrada)
    }

    if (filtro.onFire) {
      listaFiltrada = listaFiltrada.filter { it.onFire }
    }

    if (filtro.esPromesa) {
      listaFiltrada = listaFiltrada.filter { it.promesa }
    }

    if ((0.0..0.0) != filtro.rangoValoracion) {
      listaFiltrada = listaFiltrada.filter { pedirValoracion(it.id) in filtro.rangoValoracion }
    }

    return listaFiltrada
  }
  fun obtenerFiguritasParaIntercambiar(logedUserid:Int,filtro: FiltroFigurita): List<FiguritaDTO> {
    val otros = this.otrosUsuarios(logedUserid)
    var listaFigus = otros.flatMap { it.listaFiguritasARegalar().map { figu -> figu.toDTO(it) } }

    return filtrarFigus(listaFigus,filtro)
  }

  fun obtenerFigusRepesAgregables(filtro: FiltroFigurita): List<FiguritaDTO> {
    val listaFigusAgregablesDTO = figuritaRepository.getAll().map { figu -> figu.toDTO(null) }

    return filtrarFigus(listaFigusAgregablesDTO, filtro)
  }

  fun obtenerFigusFaltantesAgregables(userID: Int,filtro: FiltroFigurita): List<FiguritaDTO> {
    val userFaltentesList = usuariosRepository.getById(userID).figuritasFaltantes.toList()
    val figusFaltantesAUsuario = figuritaRepository.getAll().filter { figu -> !userFaltentesList.contains(figu)  }

    return filtrarFigus(figusFaltantesAUsuario.map { figu -> figu.toDTO(null) }, filtro)
  }

  fun pedirValoracion (id: Int) = figuritaRepository.getById(id).valoracion()

  fun otrosUsuarios(miID: Int) = usuariosRepository.getAll().filter { it.id != miID }

  fun filtroPalabraClave(palabra: String, lista: List<FiguritaDTO>): List<FiguritaDTO> {
    val idFiguritas = figuritaRepository.search(palabra).map { it.id }
    return lista.filter { it.id in idFiguritas }
  }
}