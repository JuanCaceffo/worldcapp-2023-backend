package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.infoJugadorDTO
import ar.edu.unsam.algo3.service.JugadoresService
import io.swagger.v3.oas.annotations.Operation
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
    @PostMapping("/crear")
    @Operation(summary = "Permite crear un jugador")
     fun crearJugador(@RequestBody infoJugador: infoJugadorDTO ){
        jugadoresService.crearJugador(infoJugador)
     }
}