package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.MarketFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.MarketDTO
import ar.edu.unsam.algo3.dto.toDireccionDTO
import ar.edu.unsam.algo3.dto.toMarketDTO
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.ErrorMessages
import ar.edu.unsam.algo3.error.NotFoundException
import ar.edu.unsam.algo3.error.PuntoVentaErrorMessages
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point
import java.time.LocalDate
import kotlin.random.Random

@Service
class PuntosDeVentaService(
  val puntosDeVentaRepository: PuntosDeVentaRepository,
  val usuariosRepository: UsuariosRepository
) {
  fun getById(id:Int):MarketDTO{
     try {
      return puntosDeVentaRepository.getById(id).toMarketDTO(null)
    } catch (ex: Exception) {
      throw NotFoundException(ErrorMessages.ID_INEXISTENTE)
    }
  }

  fun getAll(params: MarketFilterParams): List<MarketDTO> {
    val puntosDeVenta = puntosDeVentaRepository.getAll()
    return filtrar(puntosDeVenta, params).map {
      it.toMarketDTO(null)
    }
  }

  fun puntosDeVentaOrdenados(userId: Int, params: MarketFilterParams): List<MarketDTO> {
    var listaOrdenada: List<PuntoDeVenta> = mutableListOf()

    when (params.opcionElegida) {
      "Menor Distancia" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMenorDistancia(usuariosRepository.getById(userId))
      "Más Barato" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMasBarato(usuariosRepository.getById(userId))
      "Más Sobres" -> listaOrdenada = puntosDeVentaRepository.ordenarPorMasSobres().reversed()
      "Sólo más Cercanos" -> listaOrdenada =
        puntosDeVentaRepository.ordenarPorSoloMasCercanos(usuariosRepository.getById(userId))
    }

    return filtrar(listaOrdenada, params).map {
      it.toMarketDTO(usuariosRepository.getById(userId))
    }
  }

  fun delete(id: Int) {
    val puntoDeVenta = puntosDeVentaRepository.getById(id)
    validarBorrado(puntoDeVenta)
    puntosDeVentaRepository.delete(puntoDeVenta)
  }

  private fun validarBorrado(puntoDeVenta: PuntoDeVenta){
    if(!sePuedeBorrar(puntoDeVenta)) {
      throw BussinesExpetion(PuntoVentaErrorMessages.TIENE_STOCK)
    }
  }

  private fun sePuedeBorrar(puntodeVenta:PuntoDeVenta):Boolean{
    return !puntodeVenta.disponibilidad() && !puntodeVenta.tienePedidoConEntregaProxima()
  }

  private fun crearFiltroMarket(params: MarketFilterParams): Filtro<PuntoDeVenta> {
    return Filtro<PuntoDeVenta>().apply {
      addCondiconFiltrado(FiltroPalabraClavePuntoDeVenta(params.palabraClave, puntosDeVentaRepository))
    }
  }

  private fun filtrar(markets: List<PuntoDeVenta>, params: MarketFilterParams): List<PuntoDeVenta> {
    val filtro = crearFiltroMarket(params)
    return markets.filter { market -> filtro.cumpleCondiciones(market) }
  }

  private fun stringToPoint(point: String = "x: 0, y: 0") : Point {
    val partes = point.split(",")
    val x = partes[0].substringAfter("x: ").toDouble()
    val y = partes[1].substringAfter("y: ").toDouble()
    return Point(x, y)
  }

  private fun crearDireccion(dataMarket: MarketDTO) =
    Direccion(
      "Buenos Aires",
      "San Martin",
      dataMarket.direccion.calle,
      dataMarket.direccion.altura,
      stringToPoint(dataMarket.direccion.ubiGeografica)
    )

  fun crearPuntoDeVentaDesdeDTO(marketDTO: MarketDTO): PuntoDeVenta {
    val puntoDeVenta = when (marketDTO.tipoPuntoDeVenta.lowercase()) {
      "kiosco" -> {
        Kiosco(
          marketDTO.nombre,
          crearDireccion(marketDTO),
          marketDTO.stockSobres
        )
      }
      "libreria" -> {
        Libreria(
          marketDTO.nombre,
          crearDireccion(marketDTO),
          marketDTO.stockSobres
        )
      }
      "supermercado" -> {
        Supermercado(
          marketDTO.nombre,
          crearDireccion(marketDTO),
          marketDTO.stockSobres
        )
      }
      else -> throw IllegalArgumentException("Tipo de punto de venta desconocido: ${marketDTO.tipoPuntoDeVenta}")
    }

    val pedidos = mutableListOf<Pedido>()
    repeat(marketDTO.pedidosPendientes) {
      pedidos.add(Pedido(Random.nextInt(1,50), LocalDate.now().plusDays(Random.nextLong(1,50))))
    }
    puntoDeVenta.id(marketDTO.id)
    puntoDeVenta.pedidosPendientes = pedidos
    return puntoDeVenta
  }

  fun updateMarket(dataMarket: MarketDTO){
    puntosDeVentaRepository.update(crearPuntoDeVentaDesdeDTO(dataMarket))
  }

  fun createMarket(dataMarket: MarketDTO){
    puntosDeVentaRepository.create(crearPuntoDeVentaDesdeDTO(dataMarket))
  }
}