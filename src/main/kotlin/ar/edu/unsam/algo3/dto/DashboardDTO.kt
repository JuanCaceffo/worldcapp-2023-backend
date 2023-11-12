package ar.edu.unsam.algo3.dto

data class dashboardFiguritasOfrecidas(val quantity: Int, val name: String)
data class dashboardFiguritasFaltantes(val quantity: Int, val name: String)
data class dashboardPuntosDeVentas(val quantity: Int, val name: String)
data class dashboardUsuariosActivos(val quantity: Int, val name: String)
data class dashboardStatics(
    val figuritasOfrecidas: dashboardFiguritasOfrecidas,
    val figuritasFaltantes: dashboardFiguritasFaltantes,
    val puntosDeVentas: dashboardPuntosDeVentas,
    val usuariosActivos: dashboardUsuariosActivos
)