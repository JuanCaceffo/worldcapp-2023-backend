package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.infoJugadorDTO
import ar.edu.unsam.algo3.service.JugadoresService
import ar.edu.unsam.algo3.service.MENSAJE_ERROR_POSICION_INEXISTENTE
import ar.edu.unsam.algo3.service.MESNAJE_ERROR_SELECCION_INEXISTENTE
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
@RequestMapping("/jugadores")
class JugadoresController(
    val jugadoresService: JugadoresService
) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_POSICION_INEXISTENTE + "<br />" + MESNAJE_ERROR_SELECCION_INEXISTENTE)
    ])
    @PostMapping("/crear")
    @Operation(summary = "Permite crear un jugador")
    fun crearJugador(@RequestBody infoJugador: infoJugadorDTO) {
        jugadoresService.crearJugador(infoJugador)
    }
}