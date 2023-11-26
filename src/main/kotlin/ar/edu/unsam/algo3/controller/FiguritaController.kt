package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*

open class FiguritaBaseParams(
  @Parameter(description = "Palabra clave a buscar", required = false) @RequestParam val palabraClave: String,
)

class FiguritaParams(
  palabraClave: String,
  @RequestParam(name = "onFire", required = false) val onFire: Boolean = false,
  @RequestParam(name = "esPromesa", required = false) val esPromesa: Boolean = false,
  @RequestParam(name = "cotizacionInicial", required = false) val cotizacionInicial: Double = 0.0,
  @RequestParam(name = "cotizacionFinal", required = false) val cotizacionFinal: Double = 0.0
) : FiguritaBaseParams(palabraClave)

@RestController
@CrossOrigin("*")
class FiguritaController(val figuritaService: FiguritaService) {

  @GetMapping("/figuritas")
  @Operation(summary = "Devuelve todas las figuritas existentes en el sistema")
  fun getAll(
    params: FiguritaBaseParams
  ): List<FiguritaBaseDTO> {
    return figuritaService.getAll(FiguritaParams(params.palabraClave))
  }

  @GetMapping("/figuritas/intercambiar/{id}")
  @Operation(summary = "Devuelve el listado de figuritas no propias disponibles para intercambio")
  fun paraIntercambiar(
    @PathVariable id: Int,
    params: FiguritaParams
  ): List<FiguritaFullDTO> {

    return figuritaService.paraIntercambiar(id, params)
  }

  @GetMapping("/figuritas/figus-agregables/user/{userID}")
  @Operation(summary = "Devuelve una lista de las figuritas sin usuario asignado que el usuario puede agregar ")
  fun figuritasFaltantesAgregables(
    @PathVariable userID: Int, params: FiguritaParams
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
}