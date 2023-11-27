package ar.edu.unsam.algo3.dto

import ar.edu.unsam.algo3.domain.PuntoDeVenta
import ar.edu.unsam.algo3.domain.Usuario

open class MarketDTO(
  val id: Int,
  val nombre: String,
  val tipoPuntoDeVenta: String,
  val direccion: DireccionMarketDTO,
  val stockSobres: Int,
  val pedidosPendientes: Int,
)

class MarketCardDTO(
  id: Int,
  nombre: String,
  tipoPuntoDeVenta: String,
  direccion: DireccionMarketDTO,
  stockSobres: Int,
  pedidosPendientes: Int,
  val distancia: Double,
  val precioSobres: Double,
) : MarketDTO( id, nombre, tipoPuntoDeVenta, direccion, stockSobres, pedidosPendientes)

fun PuntoDeVenta.toMarketCardDTO(user: Usuario) = MarketCardDTO(
  id = this.id,
  nombre = this.nombre,
  tipoPuntoDeVenta = this.tipoPuntoDeVenta(),
  direccion = this.direccion.toMarketCardDTO(),
  distancia = this.distanciaPuntoVentaUsuario(user),
  stockSobres = this.stockSobres,
  pedidosPendientes = this.cantidadPedidosPendientes(),
  precioSobres = this.importeACobrar(user, 1)
)

fun PuntoDeVenta.toMarketDTO() = MarketDTO(
  id = this.id,
  nombre = this.nombre,
  tipoPuntoDeVenta = this.tipoPuntoDeVenta(),
  direccion = this.direccion.toMarketCardDTO(),
  stockSobres = this.stockSobres,
  pedidosPendientes = this.cantidadPedidosPendientes(),
)
