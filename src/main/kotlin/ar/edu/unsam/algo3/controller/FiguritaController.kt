package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.FiguritaService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FiguritaController (val figuritaService: FiguritaService){
  @GetMapping("/figuritas/search")
  @Operation(summary="Permite buscar figuritas en base al criterio de búsqueda pasado")
  fun search(@RequestParam(name="buscar") buscarFigurita:String) = figuritaService.search(buscarFigurita)


}