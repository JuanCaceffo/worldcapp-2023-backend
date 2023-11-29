package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.infoJugadorDTO
import ar.edu.unsam.algo3.service.JugadoresService
import ar.edu.unsam.algo3.service.MENSAJE_ERROR_DATA_INCOMPLETA
import ar.edu.unsam.algo3.service.MENSAJE_ERROR_POSICION_INEXISTENTE
import ar.edu.unsam.algo3.service.MESNAJE_ERROR_SELECCION_INEXISTENTE
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/jugador")
class JugadoresController(
    val jugadoresService: JugadoresService
) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_POSICION_INEXISTENTE + "<br />" + MESNAJE_ERROR_SELECCION_INEXISTENTE + "<br />" + MENSAJE_ERROR_DATA_INCOMPLETA),
        ApiResponse(responseCode = "501", description = "Error al parsear la fecha"),
    ])
    @PostMapping("/crear")
    @Operation(summary = "Permite crear un jugador")
    fun crearJugador(@RequestBody infoJugador: infoJugadorDTO) {
        jugadoresService.crearJugador(infoJugador)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_POSICION_INEXISTENTE + "<br />" + MESNAJE_ERROR_SELECCION_INEXISTENTE + "<br />" + MENSAJE_ERROR_DATA_INCOMPLETA),
        ApiResponse(responseCode = "501", description = "Error al parsear la fecha"),
    ])
    @PatchMapping("/{id}/modficar")
    @Operation(summary = "Permite modificar un jugador existente")
    fun modificarJugador(@RequestBody infoJugador: infoJugadorDTO, @PathVariable id: Int ) {
        jugadoresService.modificarJugador(infoJugador, id)
    }
}