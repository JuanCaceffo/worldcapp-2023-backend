package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.Direccion

data class DirecciontoMarketCardDTO(val calle: String, val altura: Int, val ubiGeografica: String)

fun Direccion.toMarketCardDTO() = DirecciontoMarketCardDTO(this.calle, this.altura, this.ubiGeografica.toString())
//distanciaPuntoVentaUsuario(usuario: Usuario) = direccion.distanciaConPoint(usuario.direccion.ubiGeografica)