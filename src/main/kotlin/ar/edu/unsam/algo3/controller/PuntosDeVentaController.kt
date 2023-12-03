package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.error.PuntoVentaErrorMessages
import ar.edu.unsam.algo3.service.PuntosDeVentaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
  ): List<MarketDTO> {
    return this.puntosDeVentaService.puntosDeVentaOrdenados(idUsuario, params)
  }

  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Ok"),
    ApiResponse(responseCode = "400", description = PuntoVentaErrorMessages.TIENE_STOCK),
    ApiResponse(responseCode = "501", description = "Error Inesperado"),
  ])
  @DeleteMapping("/punto-de-venta/{id}/eliminar")
  @Operation(summary="Permite eliminar un punto de venta")
  fun delete(@PathVariable id: Int){
    puntosDeVentaService.delete(id)
  }

  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Ok"),
    ApiResponse(responseCode = "400", description = PuntoVentaErrorMessages.FALLO_CREACION),
    ApiResponse(responseCode = "501", description = "Error Inesperado"),
  ])
  @PostMapping("/punto-de-venta/nuevo")
  @Operation(summary = "Permite crear un nuevo punto de venta")
  fun createMarket(@RequestBody marketDTO: MarketDTO): ResponseEntity<String> {
    puntosDeVentaService.createMarket(marketDTO)
    return ResponseEntity.ok("Market created successfully")
  }

  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Ok"),
    ApiResponse(responseCode = "400", description = PuntoVentaErrorMessages.FALLO_EDICION),
    ApiResponse(responseCode = "501", description = "Error Inesperado"),
  ])
  @PutMapping("/punto-de-venta/editar")
  @Operation(summary = "Permite editar un puntos de venta")
  fun updateMarket(@RequestBody marketDTO: MarketDTO): ResponseEntity<String> {
    puntosDeVentaService.updateMarket(marketDTO)
    return ResponseEntity.ok("Market created successfully")
  }
}