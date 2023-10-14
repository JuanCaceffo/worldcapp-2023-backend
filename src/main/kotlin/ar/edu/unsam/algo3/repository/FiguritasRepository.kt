package ar.edu.unsam.algo3.repository

import ar.edu.unsam.algo3.domain.Figurita
import org.springframework.stereotype.Repository

@Repository
class FiguritasRepository: Repositorio<Figurita>() {
    fun setOnFire(nros: MutableList<Int>){
        elementos.forEach{ if (nros.contains(it.numero)) {it.OnFire(true)}}
    }
}
