package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Seleccion
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.MarketDTO
import ar.edu.unsam.algo3.service.PuntosDeVentaService
import ar.edu.unsam.algo3.service.SeleccionesService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/selecciones")
class SeleccionesControler(
    val seleccionesService: SeleccionesService
) {
    @GetMapping("/pais")
    @Operation(summary = "Devuelve una lista con todos los nombres de las seleciones")
    fun getAllNames(): List<String>{
        return seleccionesService.getAllNames()
    }

    @GetMapping("")
    @Operation(summary = "Obtiene todas las selecciones")
    fun getAllTeams(params: BaseFilterParams): List<Seleccion> = seleccionesService.getAll(BaseFilterParams(params.palabraClave))
}