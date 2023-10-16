package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta

data class PuntosDeVentaDTO(val id: Int, val nombre: String, val tipoPuntoDeVenta: String, val direccion: DireccionDTO, val stockSobres: Int, val pendientes: Boolean, val precioSobres: Double )

fun PuntoDeVenta.toMarketCardDTO() = PuntosDeVentaDTO(this.id, this.nombre,this.tipoPuntoDeVenta(), this.direccion.toMarketCardDTO(), this.stockSobres, this.pedidosPendientes.isNotEmpty(), PuntoDeVenta.costoMinimoSobre)