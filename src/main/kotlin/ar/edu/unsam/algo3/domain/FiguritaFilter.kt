package ar.edu.unsam.algo3.domain

data class FiguritaFilter (
  var palabraClave: String? = "",
  var onFire: Boolean? = null,
  var esPromesa: Boolean? = null,
  var rangoCotizacion: ClosedRange<Double> = (0.0..0.0)
)