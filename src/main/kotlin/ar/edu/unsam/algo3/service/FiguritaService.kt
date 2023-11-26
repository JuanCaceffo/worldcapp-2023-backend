package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.MENSAJE_ERROR_ID_INEXISTENTE
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class FiguritaService(
  val figuritaRepository: FiguritasRepository,
  val usuariosRepository: UsuariosRepository
) {
  fun getAll(filtro: FiltroFiguritaDTO): List<FiguritaBaseDTO>{
    val figuritas = figuritaRepository.getAll()
    return filtrarFigus(figuritas, filtro).map { it.toBaseDTO() }
  }

  fun obtenerFiguritasParaIntercambiar(logedUserid:Int, filtro: FiltroFiguritaDTO): List<FiguritaFullDTO> {
    try {
      usuariosRepository.getById(logedUserid)
    } catch (ex: Exception) {
      throw NotFoundException(MENSAJE_ERROR_ID_INEXISTENTE)
    }
    val otros = this.otrosUsuarios(logedUserid)
    return otros.flatMap { filtrarFigus(it.listaFiguritasARegalar(), filtro).map{ figu -> figu.toDTO(it)} }
  }

  fun obtenerFigusFaltantesAgregables(userID: Int,filtro: FiltroFiguritaDTO): List<FiguritaFullDTO> {
    val userFaltentesList = usuariosRepository.getById(userID).figuritasFaltantes.toList()
    val figusFaltantesAUsuario = figuritaRepository.getAll().filter { figu -> !userFaltentesList.contains(figu)  }

    return filtrarFigus(figusFaltantesAUsuario, filtro).map{ it.toDTO(null)}
  }

  fun otrosUsuarios(miID: Int) = usuariosRepository.getAll().filter { it.id != miID }

  fun filtrarFigus(figus: List<Figurita>, filtro: FiltroFiguritaDTO): List<Figurita>{
    val filtros = listOf(FiltroPalabraClave, FiltroEspromesa, FiltroOnfire, FiltroValoracion)
    FiltroPalabraClave.repository = figuritaRepository
    return figus.filter { figu -> filtros.all{ it.filtro(figu, filtro) } }
  }
}