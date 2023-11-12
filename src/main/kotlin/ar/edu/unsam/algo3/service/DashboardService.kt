package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.stereotype.Service
import java.util.Objects

@Service


class DashboardService(val puntosDeVentaRepository: PuntosDeVentaRepository) {
    fun getDashboardStatics(): dashboardStatics {
        val figuritasOfrecidas = dashboardFiguritasOfrecidas(10, "Figuritas Ofrecidas")
        val figuritasFaltantes = dashboardFiguritasFaltantes(5, "Figuritas Faltantes")
        val puntosDeVentas = dashboardPuntosDeVentas(puntosDeVentaRepository.getAll().size, "Puntos de Ventas")
        val usuariosActivos = dashboardUsuariosActivos(15, "Usuarios activos")

        return dashboardStatics(figuritasOfrecidas, figuritasFaltantes, puntosDeVentas, usuariosActivos)
    }
}
