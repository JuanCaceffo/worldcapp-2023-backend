package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.Repositorio
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service


class DashboardService(
    val puntosDeVentaRepository: PuntosDeVentaRepository,
    val usuariosRepository: UsuariosRepository,
    val seleccionesRepository: SeleccionesRepository
) {

    fun getDashboardStatics(): List<dashboardStatics> {
        val figuritasOfrecidas = dashboardStatics(usuariosRepository.getAllFiguritasRepetidas(), "Figuritas Ofrecidas")
        val figuritasFaltantes = dashboardStatics(usuariosRepository.getAllFiguritasFaltantes(), "Figuritas Faltantes")
        val puntosDeVentas = dashboardStatics(puntosDeVentaRepository.getTotalEntities(), "Puntos de Ventas")
        val usuariosActivos = dashboardStatics(usuariosRepository.getTotalEntities(), "Usuarios Activos")
        val seleccionesActivas = dashboardStatics(seleccionesRepository.getTotalEntities(), "Selecciones Activas")

        return listOf(figuritasOfrecidas, figuritasFaltantes, puntosDeVentas, usuariosActivos, seleccionesActivas)
    }
}
