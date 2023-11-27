package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.FiguritaFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.MENSAJE_ERROR_ID_INEXISTENTE
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class FiguritaService(
  val figuritaRepository: FiguritasRepository,
  val usuariosRepository: UsuariosRepository,
  val jugadorRepository: JugadorRepository
) {
  fun getAll(params: FiguritaFilterParams): List<FiguritaBaseDTO>{
    val figuritas = figuritaRepository.getAll()
    return filtrar(figuritas, params).map { it.toBaseDTO() }
  }

  fun paraIntercambiar(logedUserid:Int, params:FiguritaFilterParams): List<FiguritaFullDTO> {
    try {
      usuariosRepository.getById(logedUserid)
    } catch (ex: Exception) {
      throw NotFoundException(MENSAJE_ERROR_ID_INEXISTENTE)
    }
    val otros = this.otrosUsuarios(logedUserid)
    return otros.flatMap { filtrar(it.listaFiguritasARegalar(), params).map{ figu -> figu.toDTO(it)} }
  }

  fun obtenerFigusFaltantesAgregables(userID: Int, params: FiguritaFilterParams): List<FiguritaFullDTO> {
    val userFaltentesList = usuariosRepository.getById(userID).figuritasFaltantes.toList()
    val figusFaltantesAUsuario = figuritaRepository.getAll().filter { figu -> !userFaltentesList.contains(figu)  }
    return filtrar(figusFaltantesAUsuario, params).map{ it.toDTO(null)}
  }

  fun otrosUsuarios(miID: Int) = usuariosRepository.getAll().filter { it.id != miID }

  fun getAllPlayers():List<JugadorCreateDTO> = jugadorRepository.getAll().map {jugador -> jugador.toJugadorCreateDTO()}

  fun crearFiltroFigurita(params: FiguritaFilterParams):Filtro<Figurita>{
    val rango = (params.cotizacionInicial)..(params.cotizacionFinal)
    return Filtro<Figurita>().apply {
      addCondiconFiltrado(FiltroPalabraClaveFigurita(params.palabraClave, figuritaRepository))
      addCondiconFiltrado(FiltroOnfire(params.onFire))
      addCondiconFiltrado(FiltroEspromesa(params.esPromesa))
      addCondiconFiltrado(FiltroValoracion(rango))
    }
  }

  fun filtrar(figus: List<Figurita>, params: FiguritaFilterParams): List<Figurita>{
    val filtro = crearFiltroFigurita(params)
    return figus.filter { figu -> filtro.cumpleCondiciones(figu) }
  }
}