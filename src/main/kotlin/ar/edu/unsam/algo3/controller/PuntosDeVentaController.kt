package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.PuntosDeVentaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PuntosDeVentaController(val puntosDeVentaService: PuntosDeVentaService) {
    @GetMapping("/puntosDeVenta/")
    @Operation(summary = "Obtiene todos los puntos de venta")
    fun getAllPuntosDeVenta() = this.puntosDeVentaService.getAllPuntosDeVenta()
}