package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.PuntosDeVentaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class PuntosDeVentaController(val puntosDeVentaService: PuntosDeVentaService) {
  @GetMapping("/puntosDeVenta/{id}")
  @Operation(summary = "Obtiene todos los puntos de venta")
  fun getAll(
    @PathVariable id: Int,
    @RequestParam(name= "palabraClave", required = false, defaultValue = "") palabraClave: String,
    @RequestParam(name= "opcionElegida", required = false, defaultValue = "") opcionElegida: String
  ): List<MarketCardDTO> {
    val filtro = FiltroPuntoDeVentaDTO(
      palabraClave = palabraClave,
      opcionElegida = opcionElegida,
    )
    return this.puntosDeVentaService.obtenerPuntosDeVentaFiltrados(id,filtro)
  }
}