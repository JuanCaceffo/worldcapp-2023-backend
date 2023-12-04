package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Seleccion
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.MarketDTO
import ar.edu.unsam.algo3.error.JugadorErrorMessages
import ar.edu.unsam.algo3.error.SeleccionErrorMessages
import ar.edu.unsam.algo3.service.PuntosDeVentaService
import ar.edu.unsam.algo3.service.SeleccionesService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

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

    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Permite eliminar una seleccion")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = SeleccionErrorMessages.SELECCION_UTILIZADA),
        ApiResponse(responseCode = "404", description = SeleccionErrorMessages.SELECCION_INEXISTENTE)
    ])
    fun eliminarSeleccion(@PathVariable id: Int){
        seleccionesService.eliminarSeleccion(id)
    }
}