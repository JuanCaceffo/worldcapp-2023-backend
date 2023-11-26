package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepository


class Filtro{
  private val condiciones = mutableListOf<CondicionesFiltrado>()

  fun addCondiconFiltrado(condicion:CondicionesFiltrado){
    condiciones.add(condicion)
  }
  fun cumpleCondiciones(figurita: Figurita) = condiciones.all{ it.filtro(figurita)}
}

interface CondicionesFiltrado{
  fun filtro(figurita: Figurita): Boolean
}

class FiltroPalabraClave(private val palabraClave: String, val repository: FiguritasRepository) : CondicionesFiltrado{
  override fun filtro(figurita: Figurita): Boolean {
    return if (palabraClave != "") {
      return figurita in repository.search(palabraClave)
    } else {
      true
    }
  }
}

class FiltroOnfire(private val condicion:Boolean) : CondicionesFiltrado {
  override fun filtro(figurita: Figurita): Boolean {
    return if(condicion){
      figurita.estaOnfire()
    } else {
      true
    }
  }
}

class FiltroEspromesa(private val condicion: Boolean) : CondicionesFiltrado {
  override fun filtro(figurita: Figurita): Boolean {
    return if(condicion){
      figurita.jugador.promesaDelFutbol()
    } else {
      true
    }
  }
}

class FiltroValoracion(private val rango:ClosedRange<Double>) : CondicionesFiltrado {
  override fun filtro(figurita: Figurita): Boolean {
    return if (rango != (0.0..0.0)) {
      return figurita.valoracion() in rango
    } else {
      true
    }
  }
}