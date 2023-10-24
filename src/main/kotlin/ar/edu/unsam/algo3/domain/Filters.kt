package ar.edu.unsam.algo3.domain

data class FiltroFigurita(
  var idUsuario: Int,
  var palabraClave: String? = "",
  var onFire: Boolean? = null,
  var esPromesa: Boolean? = null,
  var rangoValoracion: ClosedRange<Double> = (0.0..0.0),
)

data class FiltroPuntoDeVenta(
  var idUsuario: Int,
  var palabraClave: String? = "",
  var menorDistancia: Boolean? = null,
  var masBarato: Boolean? = null,
  var masSobre: Boolean? = null,
  var soloMasCercanos: Boolean? = null,
)