package ar.edu.unsam.algo3.repository
import ar.edu.unsam.algo3.domain.Figurita

class FiguritasRepositorio: Repositorio<Figurita>() {
    fun setOnFire(nros: MutableList<Int>){
        elementos.forEach{ if (nros.contains(it.value.numero)) {it.value.OnFire(true)}}
    }
}
