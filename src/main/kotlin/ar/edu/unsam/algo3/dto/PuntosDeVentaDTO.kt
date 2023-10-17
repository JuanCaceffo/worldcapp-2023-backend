package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.domain.Usuario

data class PuntosDeVentatoMarketCardDTO(val id: Int, val nombre: String, val tipoPuntoDeVenta: String, val direccion: DirecciontoMarketCardDTO, val distancia: Double, val stockSobres: Int, val pendientes: Boolean, val precioSobres: Double )

fun PuntoDeVenta.toMarketCardDTO(user: Usuario) = PuntosDeVentatoMarketCardDTO(this.id, this.nombre,this.tipoPuntoDeVenta(), this.direccion.toMarketCardDTO(), this.distanciaPuntoVentaUsuario(user) ,this.stockSobres, this.pedidosPendientes.isNotEmpty(), PuntoDeVenta.costoMinimoSobre)