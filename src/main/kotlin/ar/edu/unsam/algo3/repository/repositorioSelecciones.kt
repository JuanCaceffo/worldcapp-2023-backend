package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Repositorio
import ar.edu.unsam.algo3.domain.Seleccion
import org.springframework.stereotype.Repository

@Repository
class RepositorioSelecciones: Repositorio<Seleccion>()