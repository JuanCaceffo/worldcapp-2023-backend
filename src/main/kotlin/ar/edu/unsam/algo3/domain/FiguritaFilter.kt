package ar.edu.unsam.algo3.domain

data class FiguritaFilter (
  var palabraClave: String = "",
  var onFire: Boolean? = false,
  var esPromesa: Boolean? = false,
  var cotizacionInicial: Double? = 0.0,
  var cotizacionFinal: Double? = 0.0
)