package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.FiltroFigurita
import ar.edu.unsam.algo3.dto.FiguritaDTO
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
class FiguritaController (val figuritaService: FiguritaService){
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
      idUsuario = id,
      palabraClave = palabraClave,
      onFire = onFire,
      esPromesa = esPromesa,
      rangoValoracion = (cotizacionInicial)..(cotizacionFinal)
    )
    return figuritaService.obtenerFiguritasParaIntercambiar(filtro)
  }
}


