package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.dto.FiguritaIndexDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class FiguritaController (val figuritaService: FiguritaService){

  //TODO: Ver como mitigar la duplicaicon de codgio en los dos endpoints sin perder la descriptibilidad de los mismos
  @GetMapping("/figuritas/intercambiar/{id}")
  @Operation(summary="Devuelve el listado de figuritas no propias disponibles para intercambio")
  fun paraIntercambiar(
    @PathVariable id: Int,
    @RequestParam(name= "palabraClave", required = false, defaultValue = "") palabraClave: String,
    @RequestParam(name= "onFire", required = false, defaultValue = "false") onFire: Boolean,
    @RequestParam(name= "esPromesa", required = false, defaultValue = "false") esPromesa: Boolean,
    @RequestParam(name= "cotizacionInicial", required = false, defaultValue = "0.0") cotizacionInicial: Double,
    @RequestParam(name= "cotizacionFinal", required = false, defaultValue = "0.0") cotizacionFinal: Double
  ):List<FiguritaDTO> {
    val filtro = FiltroFigurita(
      palabraClave = palabraClave,
      onFire = onFire,
      esPromesa = esPromesa,
      rangoValoracion = (cotizacionInicial)..(cotizacionFinal)
    )
    return figuritaService.obtenerFiguritasParaIntercambiar(id,filtro)
  }
  @GetMapping("/figuritas/figus-agregables/user/{userID}")
  @Operation(summary = "Devuelve una lista de las figuritas sin usuario asignado que el usuario puede agregar ")
  fun figuritasFaltantesAgregables(
    @PathVariable userID: Int,
    @RequestParam(name= "palabraClave", required = false, defaultValue = "") palabraClave: String,
    @RequestParam(name= "onFire", required = false, defaultValue = "false") onFire: Boolean,
    @RequestParam(name= "esPromesa", required = false, defaultValue = "false") esPromesa: Boolean,
    @RequestParam(name= "cotizacionInicial", required = false, defaultValue = "0.0") cotizacionInicial: Double,
    @RequestParam(name= "cotizacionFinal", required = false, defaultValue = "0.0") cotizacionFinal: Double
  ):List<FiguritaDTO> {
    val filtro = FiltroFigurita(
      palabraClave = palabraClave,
      onFire = onFire,
      esPromesa = esPromesa,
      rangoValoracion = (cotizacionInicial)..(cotizacionFinal)
    )

    return figuritaService.obtenerFigusFaltantesAgregables(userID,filtro)
  }

  @GetMapping("/figuritas/index")
  @Operation(summary = "Devuelve una lista con todas las figuritas")
  fun getAllFiguritasIndex():List<FiguritaIndexDTO> {
    return figuritaService.getAllFiguritasIndex()
  }
}

