package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion
import org.uqbar.geodds.Point

data class DireccionMarketDTO(
  val calle: String,
  val altura: Int,
  val ubiGeografica: String
)

fun Direccion.toDireccionDTO() = DireccionMarketDTO(
  calle = this.calle,
  altura = this.altura,
  ubiGeografica = this.ubiGeografica.toString()
)
