package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.JugadorService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class JugadorController(val jugadorService: JugadorService) {

  @GetMapping("/jugadores")
  @Operation(summary = "Devuelve todos los jugadores existentes en el sistema")
  fun getAll(
    params: BaseFilterParams
  ): List<JugadorDTO> {
    return jugadorService.getAll(BaseFilterParams(params.palabraClave))
  }

//  @GetMapping("/figuritas/data-create-figurita")
//  @Operation(summary = "Devuelve una lista con todos los jugadores y valoraciones para crear las figuritas, ademas de los niveles de impresiones")
//  fun getAllPlayers():DataCreateFigurita {
//    val players: List<JugadorCreateDTO> = figuritaService.getAllPlayers()
//    val printsLevel: List<NivelImpresion> = listOf(impresionBaja, impresionMedia, impresionAlta)
//    return DataCreateFigurita(players, printsLevel)
//  }
}