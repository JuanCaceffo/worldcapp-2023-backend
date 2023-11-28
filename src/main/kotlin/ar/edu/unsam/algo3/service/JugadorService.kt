package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.BaseFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.JugadorRepository
import org.springframework.stereotype.Service

@Service
class JugadorService(
  val jugadorRepository: JugadorRepository
) {

  fun getAll(params: BaseFilterParams): List<JugadorDTO> {
    val jugadores = jugadorRepository.getAll()
  return filtrar(jugadores, params).map { it.toDTO() }
  }

  fun crearFiltroJugador(params: BaseFilterParams):Filtro<Jugador>{
    return Filtro<Jugador>().apply {
      addCondiconFiltrado(FiltroPalabraClaveJugador(params.palabraClave, jugadorRepository))
    }
  }

  fun filtrar(figus: List<Jugador>, params: BaseFilterParams): List<Jugador>{
    val filtro = crearFiltroJugador(params)
    return figus.filter { figu -> filtro.cumpleCondiciones(figu) }
  }
}