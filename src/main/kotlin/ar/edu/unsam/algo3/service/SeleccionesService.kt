package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.BaseFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.JugadorErrorMessages
import ar.edu.unsam.algo3.error.SeleccionErrorMessages
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.stereotype.Service

@Service
class SeleccionesService(
    val seleccionesRepo: SeleccionesRepository,
    val jugadorRepository: JugadorRepository
) {
    fun crearFiltroSeleccion(params: BaseFilterParams): Filtro<Seleccion> {
        return Filtro<Seleccion>().apply {
            addCondiconFiltrado(FiltroPalabraClaveSeleccion(params.palabraClave, seleccionesRepo))
        }
    }

    fun filtrar(selecciones: List<Seleccion>, params: BaseFilterParams): List<Seleccion>{
        val filtro = crearFiltroSeleccion(params)
        return selecciones.filter { seleccion -> filtro.cumpleCondiciones(seleccion) }
    }

    fun getAllNames(): List<String>{
        val allNationalTeams = seleccionesRepo.getAll()
        return allNationalTeams.map { nationalTeam -> nationalTeam.pais }
    }

    fun getAll(params: BaseFilterParams): List<Seleccion> {
        val selecciones = seleccionesRepo.getAll()
        return filtrar(selecciones, params)
    }

    fun eliminarSeleccion(id: Int) {
        validarSeleccionInutilizada(id)
        val seleccion = seleccionesRepo.getById(id)
        seleccionesRepo.delete(seleccion)
    }

    //VALIDACIONES
    fun validarSeleccionInutilizada(id: Int){
        if (jugadorRepository.getAll().any { jugador -> jugador.seleccionPerteneciente.id == id }){
            throw BussinesExpetion(SeleccionErrorMessages.SELECCION_UTILIZADA)
        }
    }
}
