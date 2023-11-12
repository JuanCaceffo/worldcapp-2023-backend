package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.stereotype.Service
import java.util.Objects

@Service


class DashboardService(val puntosDeVentaRepository: PuntosDeVentaRepository) {
    //TODO: Devolver la info correcta
    fun getDashboardStatics(): List<dashboardStatics> {
        val figuritasOfrecidas = dashboardStatics(10, "Figuritas Ofrecidas")
        val figuritasFaltantes = dashboardStatics(5, "Figuritas Faltantes")
        val puntosDeVentas = dashboardStatics(puntosDeVentaRepository.getAll().size, "Puntos de Ventas")
        val usuariosActivos = dashboardStatics(15, "Usuarios Activos")
        val seleccionesActivas = dashboardStatics(32, "Selecciones Activas")

        return listOf(figuritasOfrecidas, figuritasFaltantes, puntosDeVentas, usuariosActivos, seleccionesActivas)
    }
}
