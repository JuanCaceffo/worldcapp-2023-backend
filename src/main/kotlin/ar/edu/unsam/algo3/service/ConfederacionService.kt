package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.Confederacion
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.Repositorio
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.stereotype.Service

@Service


class ConfederacionService() {
    fun getConfederaciones(): List<Confederacion> = listOf(Confederacion.AFC, Confederacion.CAF, Confederacion.CONCACAF, Confederacion.CONMEBOL, Confederacion.OFC,Confederacion.UEFA)
}
