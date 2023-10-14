package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.FiguritaFilter
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.repository.Repositorio
import org.springframework.stereotype.Service

@Service
class FiguritaService (
  val figuritaRepository: Repositorio<Figurita>,
  val usuarioRepository: Repositorio<Usuario>
){
  fun search(figuritaFilter: FiguritaFilter):List<Figurita> {
    var listaFiltrada = figuritaRepository.search(figuritaFilter.palabraClave!!)

    if(figuritaFilter.onFire!!) {
      listaFiltrada = listaFiltrada.filter { it.estaOnfire() }
    }

    if(figuritaFilter.esPromesa!!) {
      listaFiltrada = listaFiltrada.filter { it.jugador.promesaDelFutbol() }
    }

    if((0.0..0.0) != figuritaFilter.rangoCotizacion) {
      listaFiltrada = listaFiltrada.filter { it.valoracion() in figuritaFilter.rangoCotizacion}
    }

    return listaFiltrada
  }

  fun getAll():List<Figurita>{
    return figuritaRepository.getAll()
  }
}