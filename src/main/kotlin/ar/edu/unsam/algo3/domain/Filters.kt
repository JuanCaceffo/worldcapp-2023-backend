package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.dto.FiltroFiguritaDTO
import ar.edu.unsam.algo3.repository.FiguritasRepository

interface FiltroFigurita{
  fun filtro(figurita: Figurita, filtro:FiltroFiguritaDTO): Boolean
}


object FiltroPalabraClave : FiltroFigurita{
  lateinit var repository: FiguritasRepository
  override fun filtro(figurita: Figurita, filtro: FiltroFiguritaDTO): Boolean {
    println(repository.getAll())
    return if (filtro.palabraClave != "") {
      return figurita in repository.search(filtro.palabraClave)
    } else {
      true
    }
  }
}

object FiltroOnfire : FiltroFigurita {
  override fun filtro(figurita: Figurita, filtro: FiltroFiguritaDTO): Boolean {
    return if(filtro.onFire){
      figurita.estaOnfire()
    } else {
      true
    }
  }
}

object FiltroEspromesa : FiltroFigurita {
  override fun filtro(figurita: Figurita, filtro: FiltroFiguritaDTO): Boolean {
    return if(filtro.esPromesa){
      figurita.jugador.promesaDelFutbol()
    } else {
      true
    }
  }
}

object FiltroValoracion : FiltroFigurita {
  override fun filtro(figurita: Figurita, filtro:FiltroFiguritaDTO): Boolean {
    return if (filtro.rangoValoracion != (0.0..0.0)) {
      return figurita.valoracion() in filtro.rangoValoracion
    } else {
      true
    }
  }
}