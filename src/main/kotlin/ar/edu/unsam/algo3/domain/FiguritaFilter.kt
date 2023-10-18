package ar.edu.unsam.algo3.domain

data class FiltroFigurita(
  var idUsuario: Int,
  var palabraClave: String? = "",
  var onFire: Boolean? = null,
  var esPromesa: Boolean? = null,
  var rangoCotizacion: ClosedRange<Double> = (0.0..0.0),
)