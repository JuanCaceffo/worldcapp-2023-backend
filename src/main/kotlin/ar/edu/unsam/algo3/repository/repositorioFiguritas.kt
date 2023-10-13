package ar.edu.unsam.algo3.repository
import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Repositorio
import org.springframework.stereotype.Repository

@Repository
class RepositorioFiguritas: Repositorio<Figurita>() {
    fun setOnFire(nros: MutableList<Int>){
        elementos.forEach{ if (nros.contains(it.value.numero)) {it.value.OnFire(true)}}
    }
}
