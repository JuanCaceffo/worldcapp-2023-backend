package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.service.Province
import ar.edu.unsam.algo3.service.ProvinceService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin("*")
class ProvincesController(val provinceService: ProvinceService) {
    @GetMapping("/provinces")
    @Operation(summary = "Obtiene todos las provincias con sus respectivas localidades")
    fun getAll(): List<Province> = this.provinceService.getAll()
}