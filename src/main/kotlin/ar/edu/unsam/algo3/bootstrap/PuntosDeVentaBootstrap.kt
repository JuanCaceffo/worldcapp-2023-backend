package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Kioscos
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point

@Service
class PuntosDeVentaBootstrap(val puntosDeVentaRepository: PuntosDeVentaRepository): InitializingBean {
    fun createPuntosDeVentas() {
        puntosDeVentaRepository.apply {
           // create(Kioscos("cachito", Direccion("bs as","3 de Febrero", "alpacal", 211, Point(2,2)),2, true))
            addElements()
        }
    }

    override fun afterPropertiesSet() {
        this.createPuntosDeVentas()
    }
}