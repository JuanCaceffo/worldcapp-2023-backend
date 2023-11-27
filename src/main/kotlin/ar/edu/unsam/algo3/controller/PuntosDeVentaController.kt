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

  @GetMapping("/puntos-de-venta/{id}")
  @Operation(summary = "Obtiene todos los puntos de venta con referencia al usuario logueado")
  fun getAll(
    @PathVariable id: Int,
    params: MarketFilterParams
  ): List<MarketCardDTO> {
    return this.puntosDeVentaService.puntosDeVentaOrdenados(id, params)
  }
}