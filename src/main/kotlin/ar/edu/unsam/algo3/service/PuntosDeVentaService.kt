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
      throw BussinesExpetion(PuntoVentaErrorMessages.PUNTO_TIENE_STOCK)
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

  //TODO: Fix hardcodeo de valores
  fun createMarket(dataMarket: MarketDTO){
//    val direccion = Direccion(
//      "Buenos Aires",
//      "San Martin",
//      dataMarket.direccion.calle,
//      dataMarket.direccion.altura,
//      Point(dataMarket.geoX,dataMarket.geoY))
//    puntosDeVentaRepository.create(puntoDeVenta)

  }

  fun updateMarket(dataMarket: MarketDTO){

//    puntosDeVentaRepository.update(puntoDeVenta)
  }
}