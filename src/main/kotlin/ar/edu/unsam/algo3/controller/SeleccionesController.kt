package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Seleccion
import ar.edu.unsam.algo3.error.ErrorMessages
import ar.edu.unsam.algo3.error.SeleccionErrorMessages
import ar.edu.unsam.algo3.service.SeleccionesService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/selecciones")
class SeleccionesController(
    val seleccionesService: SeleccionesService
) {
    @GetMapping("/pais")
    @Operation(summary = "Devuelve una lista con todos los nombres de las seleciones")
    fun getAllNames(): List<String>{
        return seleccionesService.getAllNames()
    }

    @GetMapping("")
    @Operation(summary = "Obtiene todas las selecciones")
    fun getAllTeams(params: BaseFilterParams): List<Seleccion> = seleccionesService.getAll(BaseFilterParams(params.palabraClave))

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una seleccion especifica")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = SeleccionErrorMessages.DATA_INCOMPLETA),
        ApiResponse(responseCode = "404", description = ErrorMessages.ID_INEXISTENTE)
    ])
    fun obtenerSeleccion(@PathVariable id: Int): Seleccion {
        return seleccionesService.obtenerSeleccion(id)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
    ])
    @PostMapping("/crear")
    @Operation(summary = "Permite crear una seleccion")
    fun crearSeleccion(@RequestBody infoSeleccion: Seleccion) {
        seleccionesService.crearSeleccion(infoSeleccion)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = SeleccionErrorMessages.DATA_INCOMPLETA),
        ApiResponse(responseCode = "404", description = SeleccionErrorMessages.SELECCION_INEXISTENTE)
    ])
    @PutMapping("/{id}/modificar")
    @Operation(summary = "Permite modificar una seleccion existente")
    fun modificarSeleccion(@RequestBody infoSeleccion: Seleccion, @PathVariable id: Int ) {
        seleccionesService.modificarSeleccion(infoSeleccion, id)
    }

    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Ok"),
        ApiResponse(responseCode = "400", description = SeleccionErrorMessages.SELECCION_UTILIZADA),
        ApiResponse(responseCode = "404", description = SeleccionErrorMessages.SELECCION_INEXISTENTE)
    ])
    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Permite eliminar una seleccion")
    fun eliminarSeleccion(@PathVariable id: Int){
        seleccionesService.eliminarSeleccion(id)
    }
}