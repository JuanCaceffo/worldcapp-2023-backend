package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.dashboardStatics
import ar.edu.unsam.algo3.service.DashboardService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class DashboardController(val dashboardService: DashboardService) {
    @GetMapping("/dashboard")
    @Operation(summary = "Devuelve una lista con las figus ofrecidas, faltantes, puntos de ventas y usuarios activos")
    fun getDashboardStatics(): dashboardStatics { //:List<DashboardDTO>
        return dashboardService.getDashboardStatics()
    }
}