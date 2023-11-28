package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.stereotype.Service

@Service
class SeleccionesService(
    val seleccionesRepo: SeleccionesRepository
) {

    fun getAllNames(): List<String>{
        val allNationalTeams = seleccionesRepo.getAll()
        return allNationalTeams.map { nationalTeam -> nationalTeam.pais }
    }
}