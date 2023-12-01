package ar.edu.unsam.algo3.controller

import org.springframework.web.bind.annotation.RequestParam

open class BaseFilterParams(
  @RequestParam(name = "palabraClave", required = false)  val palabraClave: String = "",
)
//que pasa
class FiguritaFilterParams(
  palabraClave: String,
  @RequestParam(name = "onFire", required = false) val onFire: Boolean = false,
  @RequestParam(name = "esPromesa", required = false) val esPromesa: Boolean = false,
  @RequestParam(name = "cotizacionInicial", required = false) val cotizacionInicial: Double = 0.0,
  @RequestParam(name = "cotizacionFinal", required = false) val cotizacionFinal: Double = 0.0
) : BaseFilterParams(palabraClave)

class MarketFilterParams(
  palabraClave: String,
  @RequestParam(name= "opcionElegida", required = false) val opcionElegida: String = "Menor Distancia"
) : BaseFilterParams(palabraClave)