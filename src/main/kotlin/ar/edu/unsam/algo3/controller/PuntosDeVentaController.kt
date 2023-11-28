package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.PuntosDeVentaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class PuntosDeVentaController(val puntosDeVentaService: PuntosDeVentaService) {
  @GetMapping("/puntos-de-venta")
  @Operation(summary = "Obtiene todos los puntos de venta")
  fun getAllSalesPoint(
    params:BaseFilterParams
  ): List<MarketDTO> {
    return this.puntosDeVentaService.getAll(MarketFilterParams(params.palabraClave))
  }

  @GetMapping("/punto-de-venta/{id}")
  @Operation(summary = "Obtiene un punto de venta")
  fun getById(
    @PathVariable id: Int
  ): MarketDTO {
    return this.puntosDeVentaService.getById(id)
  }

  @GetMapping("/puntos-de-venta/usuario/{idUsuario}")
  @Operation(summary = "Obtiene todos los puntos de venta con referencia al usuario logueado")
  fun getAll(
    @PathVariable idUsuario: Int,
    params: MarketFilterParams
  ): List<MarketCardDTO> {
    return this.puntosDeVentaService.puntosDeVentaOrdenados(idUsuario, params)
  }

  @DeleteMapping("/punto-de-venta/eliminar/{id}")
  @Operation(summary="Permite eliminar un punto de venta")
  fun delete(@PathVariable id: Int){
    puntosDeVentaService.delete(id)
  }

  @PostMapping("/punto-de-venta/nuevo")
  @Operation(summary = "Permite crear un nuevo punto de venta")
  fun create(@RequestBody dataMarket: MarketCardDTO) = puntosDeVentaService.createMarket(dataMarket)
}