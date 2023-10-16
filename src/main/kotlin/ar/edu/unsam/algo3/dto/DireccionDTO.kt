package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion
import org.uqbar.geodds.Point

data class DireccionDTO(val calle: String, val altura: Int, val distancia: Double, val geoLocalizacion: String)

fun Direccion.toMarketCardDTO() = DireccionDTO(this.calle, this.altura, this.distanciaConPoint(Point(3,5)), this.ubiGeografica.toString())