package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.domain.Usuario

class MarketDTO(
  val id: Int,
  val nombre: String,
  val tipoPuntoDeVenta: String,
  val direccion: DireccionMarketDTO,
  val stockSobres: Int,
  val pedidosPendientes: Int,
  val distancia: Double,
  val precioSobres: Double,
)

fun PuntoDeVenta.toMarketDTO(user: Usuario?) = MarketDTO(
  id = this.id,
  nombre = this.nombre,
  tipoPuntoDeVenta = this.tipoPuntoDeVenta(),
  direccion = this.direccion.toDireccionDTO(),
  distancia = if (user != null) this.distanciaPuntoVentaUsuario(user) else 0.0,
  stockSobres = this.stockSobres,
  pedidosPendientes = this.cantidadPedidosPendientes(),
  precioSobres = if (user != null) this.importeACobrar(user, 1) else 0.0
)
