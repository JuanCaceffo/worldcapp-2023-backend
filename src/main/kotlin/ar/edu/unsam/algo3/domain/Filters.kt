package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository


class Filtro<T>{
  private val condiciones = mutableListOf<CondicionesFiltrado<T>>()

  fun addCondiconFiltrado(condicion:CondicionesFiltrado<T>){
    condiciones.add(condicion)
  }
  fun cumpleCondiciones(elemento: T) = condiciones.all{ it.filtro(elemento)}
}

interface CondicionesFiltrado<T>{
  fun filtro(elemento: T): Boolean
}

class FiltroPalabraClaveFigurita(private val palabraClave: String, val repository: FiguritasRepository) : CondicionesFiltrado<Figurita>{
  override fun filtro(elemento: Figurita): Boolean {
    return if (palabraClave != "") {
      return elemento in repository.search(palabraClave)
    } else {
      true
    }
  }
}

class FiltroPalabraClavePuntoDeVenta(private val palabraClave: String, val repository: PuntosDeVentaRepository) : CondicionesFiltrado<PuntoDeVenta>{
  override fun filtro(elemento: PuntoDeVenta): Boolean {
    return if (palabraClave != "") {
      return elemento in repository.search(palabraClave)
    } else {
      true
    }
  }
}


class FiltroOnfire(private val condicion:Boolean) : CondicionesFiltrado<Figurita> {
  override fun filtro(elemento: Figurita): Boolean {
    return if(condicion){
      elemento.estaOnfire()
    } else {
      true
    }
  }
}

class FiltroEspromesa(private val condicion: Boolean) : CondicionesFiltrado<Figurita> {
  override fun filtro(elemento: Figurita): Boolean {
    return if(condicion){
      elemento.jugador.promesaDelFutbol()
    } else {
      true
    }
  }
}

class FiltroValoracion(private val rango:ClosedRange<Double>) : CondicionesFiltrado<Figurita> {
  override fun filtro(elemento: Figurita): Boolean {
    return if (rango != (0.0..0.0)) {
      return elemento.valoracion() in rango
    } else {
      true
    }
  }
}