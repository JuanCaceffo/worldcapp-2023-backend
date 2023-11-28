package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class FiguritaController(val figuritaService: FiguritaService) {

  @GetMapping("/figuritas")
  @Operation(summary = "Devuelve todas las figuritas existentes en el sistema")
  fun getAll(
    params: BaseFilterParams
  ): List<FiguritaBaseDTO> {
    return figuritaService.getAll(FiguritaFilterParams(params.palabraClave))
  }

  @GetMapping("/figuritas/intercambiar/{id}")
  @Operation(summary = "Devuelve el listado de figuritas no propias disponibles para intercambio")
  fun paraIntercambiar(
    @PathVariable id: Int,
    params: FiguritaFilterParams
  ): List<FiguritaFullDTO> {
    return figuritaService.paraIntercambiar(id, params)
  }

  @GetMapping("/figuritas/figus-agregables/user/{userID}")
  @Operation(summary = "Devuelve una lista de las figuritas sin usuario asignado que el usuario puede agregar ")
  fun figuritasFaltantesAgregables(
    @PathVariable userID: Int, params: FiguritaFilterParams
  ): List<FiguritaFullDTO> {
    return figuritaService.obtenerFigusFaltantesAgregables(userID, params)
  }

  @GetMapping("/figuritas/data-create-figurita")
  @Operation(summary = "Devuelve una lista con todos los jugadores y valoraciones para crear las figuritas, ademas de los niveles de impresiones")
  fun getAllPlayers():DataCreateFigurita {
    val players: List<JugadorCreateDTO> = figuritaService.getAllPlayers()
    val printsLevel: List<NivelImpresion> = listOf(impresionBaja, impresionMedia, impresionAlta)
    return DataCreateFigurita(players, printsLevel)
  }
  @GetMapping("/figurita/{id}")
  @Operation(summary = "Obtiene una figurita")
  fun getById(
    @PathVariable id: Int
  ): FiguritaBaseDTO {
    return this.figuritaService.getById(id)
  }
}