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
    @RequestParam(required = false) palabraClave: String? = null,
    @RequestParam(required = false) onFire: Boolean = false,
    @RequestParam(required = false) esPromesa: Boolean = false,
    @RequestParam(required = false) cotizacionInicial: Double? = null,
    @RequestParam(required = false) cotizacionFinal: Double? = null
  ):List<FiguritaDTO> {
    val filtro = FiltroFigurita(
      idUsuario = id,
      palabraClave = palabraClave,
      onFire = onFire,
      esPromesa = esPromesa,
      rangoValoracion = (cotizacionInicial ?: 0.0)..(cotizacionFinal ?: 0.0)
    )

    return figuritaService.obtenerFiguritasParaIntercambiar(filtro)
  }
}


