package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta

data class PuntosDeVentaDTO(val id: Int, val nombre: String, val calle: String, val altura: Int, val distancia: Double, val stockSobres: Int, val pendientes: Boolean, val precioSobres: Double  )

//TODO: Recibir correctamente la distancia this.distanciaPuntoVentaUsuario()
fun PuntoDeVenta.toDTO() = PuntosDeVentaDTO(this.id, this.nombre, this.direccion.calle, this.direccion.altura, 2.00, this.stockSobres, this.pedidosPendientes.isNotEmpty(), PuntoDeVenta.costoMinimoSobre)