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
    var listaFiltrada = figuritaRepository.search(figuritaFilter.palabraClave)

    if(figuritaFilter.onFire!!) {
      listaFiltrada.filter { it.estaOnfire() }
    }

    if(figuritaFilter.esPromesa!!) {
      listaFiltrada.filter { it.jugador.promesaDelFutbol() }
    }

    if(figuritaFilter.cotizacionFinal!! > 0.0) {
      listaFiltrada.filter { it.valoracion() in figuritaFilter.cotizacionInicial!!..figuritaFilter.cotizacionFinal!!}
    }

    return listaFiltrada
  }
}