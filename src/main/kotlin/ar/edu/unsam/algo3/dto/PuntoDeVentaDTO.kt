package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.domain.Usuario

data class MarketCardDTO(val id: Int, val nombre: String, val tipoPuntoDeVenta: String, val direccion: DirecciontoMarketCardDTO, val distancia: Double, val stockSobres: Int, val pendientes: Boolean, val precioSobres: Double )
data class SalesPointCardDTO(val id: Int, val nombre: String, val tipoPuntoDeVenta: String, val calle: String, val altura: Int, val stockSobres: Int)

fun PuntoDeVenta.toMarketCardDTO(user: Usuario) = MarketCardDTO(this.id, this.nombre,this.tipoPuntoDeVenta(), this.direccion.toMarketCardDTO(), this.distanciaPuntoVentaUsuario(user) ,this.stockSobres, this.pedidosPendientes.isNotEmpty(), this.importeACobrar(user, 1))
fun PuntoDeVenta.toSalesPointCardDTO() = SalesPointCardDTO(this.id, this.nombre, this.tipoPuntoDeVenta(), this.direccion.calle, this.direccion.altura, this.stockSobres)
