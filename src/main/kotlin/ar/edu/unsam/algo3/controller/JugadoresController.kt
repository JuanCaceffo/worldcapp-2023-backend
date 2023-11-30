package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.InfoCrearJugadorDTO
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.service.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class JugadoresController(
    val jugadoresService: JugadoresService
) {

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_POSICION_INEXISTENTE + "<br />" + MESNAJE_ERROR_SELECCION_INEXISTENTE + "<br />" + MENSAJE_ERROR_DATA_INCOMPLETA),
        ApiResponse(responseCode = "501", description = "Error al parsear la fecha"),
    ])
    @PostMapping("jugador/crear")
    @Operation(summary = "Permite crear un jugador")
    fun crearJugador(@RequestBody infoJugador: InfoCrearJugadorDTO) {
        jugadoresService.crearJugador(infoJugador)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_POSICION_INEXISTENTE + "<br />" + MESNAJE_ERROR_SELECCION_INEXISTENTE + "<br />" + MENSAJE_ERROR_DATA_INCOMPLETA),
        ApiResponse(responseCode = "501", description = "Error al parsear la fecha"),
    ])
    @PatchMapping("jugador/{id}/modificar")
    @Operation(summary = "Permite modificar un jugador existente")
    fun modificarJugador(@RequestBody infoJugador: InfoCrearJugadorDTO, @PathVariable id: Int ) {
        jugadoresService.modificarJugador(infoJugador, id)
    }

    @GetMapping("/jugadores")
    @Operation(summary = "Devuelve todos los jugadores existentes en el sistema")
    fun getAll(
        params: BaseFilterParams
    ): List<JugadorDTO> {
        return jugadoresService.getAll(BaseFilterParams(params.palabraClave))
    }

    @DeleteMapping("/jugador/{id}/eliminar")
    @Operation(summary = "Permite eliminar un jugador de la base de datos si no esta en ninguna figurtia")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = MENSAJE_ERROR_JUGADOR_UTILIZADO)
    ])
    fun eliminarJugador(@PathVariable id: Int){
        jugadoresService.eliminarJugador(id)
    }
}