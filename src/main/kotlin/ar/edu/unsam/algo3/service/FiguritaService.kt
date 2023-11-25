package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.FiltroFigurita
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
  fun filtrarFigus(figus: List<FiguritaDTO>, filtro: FiltroFigurita): List<FiguritaDTO>{
    var listaFiltrada = figus
    val filtros = mutableListOf<FiguritaFilter>(
      PalabraClaveFilter(filtro.palabraClave, figuritaRepository),
      OnFireFilter(filtro.onFire),
      EsPromesaFilter(filtro.esPromesa),
      RangoValoracionFilter(filtro.rangoValoracion, figuritaRepository),
    )

    filtros.forEach{ listaFiltrada = it.filter(listaFiltrada)}

    return listaFiltrada
  }

  fun obtenerFiguritasParaIntercambiar(logedUserid:Int,filtro: FiltroFigurita): List<FiguritaDTO> {
    try {
      usuariosRepository.getById(logedUserid)
    } catch (ex: Exception) {
      throw NotFoundException(MENSAJE_ERROR_ID_INEXISTENTE)
    }
    val otros = this.otrosUsuarios(logedUserid)
    val listaFigus = otros.flatMap { it.listaFiguritasARegalar().map { figu -> figu.toDTO(it) } }

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

  fun otrosUsuarios(miID: Int) = usuariosRepository.getAll().filter { it.id != miID }

  fun getAllFiguritasIndex():List<FiguritaIndexDTO> = figuritaRepository.getAll().map { figu -> figu.toIndexDTO() }

  fun getAllPlayers():List<JugadorCreateDTO> = jugadorRepository.getAll().map {jugador -> jugador.toJugadorCreateDTO()}
}


interface FiguritaFilter {
  fun filter(figus: List<FiguritaDTO>): List<FiguritaDTO>
}

class PalabraClaveFilter(private val palabraClave: String, private val figuritaRepository: FiguritasRepository) : FiguritaFilter {
  override fun filter(figus: List<FiguritaDTO>): List<FiguritaDTO> {
    return if (palabraClave != "") {
      val idFiguritas = figuritaRepository.search(palabraClave).map { it.id}
      figus.filter { it.id in idFiguritas }
    } else {
      figus
    }
  }
}

class OnFireFilter(private val onFire: Boolean) : FiguritaFilter {
  override fun filter(figus: List<FiguritaDTO>): List<FiguritaDTO> {
    return if (onFire) {
      figus.filter { it.onFire }
    } else {
      figus
    }
  }
}

class EsPromesaFilter(private val promesa: Boolean) : FiguritaFilter {
  override fun filter(figus: List<FiguritaDTO>): List<FiguritaDTO> {
    return if (promesa) {
      figus.filter { it.promesa }
    } else {
      figus
    }
  }
}

class RangoValoracionFilter(private val rangoValoracion: ClosedRange<Double>, private val figuritaRepository: FiguritasRepository) : FiguritaFilter {
  override fun filter(figus: List<FiguritaDTO>): List<FiguritaDTO> {
    return if (rangoValoracion !=(0.0..0.0)) {
      figus.filter { pedirValoracion(it.id) in rangoValoracion }
    } else {
      figus
    }
  }

  private fun pedirValoracion (id: Int) = figuritaRepository.getById(id).valoracion()
}