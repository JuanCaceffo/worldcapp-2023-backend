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

    private fun getTotalEntities(entity: Repositorio<*>) = entity.getAll().size

    fun getDashboardStatics(): List<dashboardStatics> {
        val activeUsers = usuariosRepository.getAll()
        val figuritasOfrecidas = dashboardStatics(activeUsers.sumOf { it.figuritasRepetidas.size }, "Figuritas Ofrecidas")
        val figuritasFaltantes = dashboardStatics(activeUsers.sumOf { it.figuritasFaltantes.size }, "Figuritas Faltantes")
        val puntosDeVentas = dashboardStatics(getTotalEntities(puntosDeVentaRepository), "Puntos de Ventas")
        val usuariosActivos = dashboardStatics(getTotalEntities(usuariosRepository), "Usuarios Activos")
        val seleccionesActivas = dashboardStatics(getTotalEntities(seleccionesRepository), "Selecciones Activas")

        return listOf(figuritasOfrecidas, figuritasFaltantes, puntosDeVentas, usuariosActivos, seleccionesActivas)
    }
}
