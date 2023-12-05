package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Confederacion
import ar.edu.unsam.algo3.dto.dashboardStatics
import ar.edu.unsam.algo3.service.ConfederacionService
import ar.edu.unsam.algo3.service.DashboardService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class ConfederacionController(val confederacionService: ConfederacionService) {
    @GetMapping("/confederaciones")
    @Operation(summary = "Devuelve una lista con todas las confederaciones")
    fun getConfederaciones(): List<Confederacion> {
        return confederacionService.getConfederaciones()
    }
}