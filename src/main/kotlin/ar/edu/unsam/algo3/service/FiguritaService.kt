package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service
class FiguritaService (
  val figuritaRepository: FiguritasRepository,
  val usuarioRepository: UsuariosRepository
){
  fun obtenerFiguritasParaIntercambiar(filtro: FiltroFigurita):List<Figurita> {
    val otrosUsuarios = usuarioRepository.getAll().filter { it.id != filtro.idUsuario }
    var listaFiltrada = otrosUsuarios.flatMap { it.listaFiguritasARegalar() }

    if(filtro.palabraClave != null) {
      listaFiltrada = figuritaRepository.search(filtro.palabraClave!!)
    }

    if(filtro.onFire!!) {
      listaFiltrada = listaFiltrada.filter { it.estaOnfire() }
    }

    if(filtro.esPromesa!!) {
      listaFiltrada = listaFiltrada.filter { it.jugador.promesaDelFutbol() }
    }

    if((0.0..0.0) != filtro.rangoCotizacion) {
      listaFiltrada = listaFiltrada.filter { it.valoracion() in filtro.rangoCotizacion }
    }

    return listaFiltrada
  }
}