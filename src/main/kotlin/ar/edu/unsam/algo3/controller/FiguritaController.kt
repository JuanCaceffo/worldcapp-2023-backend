package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.FiguritaFilter
import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class FiguritaController (val figuritaService: FiguritaService){
  @GetMapping("/figuritas/search")
  @Operation(summary="Permite buscar figuritas en base al criterio de búsqueda pasado")
  fun search(
    @RequestParam(required = false) palabraClaveFigurita: String?,
    @RequestParam(required = false) onFireFigurita: Boolean?,
    @RequestParam(required = false) esPromesaFigurita: Boolean?,
    @RequestParam(required = false) cotizacionInicialFigurita: Double?,
    @RequestParam(required = false) cotizacionFinalFigurita: Double?
  ):List<Figurita> {
    return if (palabraClaveFigurita == null && onFireFigurita == null && esPromesaFigurita == null && cotizacionInicialFigurita == null && cotizacionFinalFigurita == null) {
      // Devolver toda la lista de figuritas sin filtro
      figuritaService.getAll()
    } else {
      figuritaService.search(FiguritaFilter().apply{
        palabraClave=palabraClaveFigurita
        onFire=onFireFigurita!!
        esPromesa=esPromesaFigurita!!
        rangoCotizacion=(cotizacionInicialFigurita!!..cotizacionFinalFigurita!!)
      })
    }
  }
}

