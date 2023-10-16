package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta

data class PuntosDeVentaDTO(val id: Int, val nombre: String, val direccion: String, val distancia: Double, val stockSobres: Int, val pendientes: Boolean, val precioSobres: Double  )

//TODO: Recibir correctamente la distancia y el precio sobre
//TODO: Implementar DireccionDTO
fun PuntoDeVenta.toDTO() = PuntosDeVentaDTO(this.id, this.nombre, this.direccion.calle, 10.0, this.stockSobres, this.pedidosPendientes.isEmpty(), 3.00)