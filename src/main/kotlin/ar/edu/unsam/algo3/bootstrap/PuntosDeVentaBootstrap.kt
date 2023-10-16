package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point
import java.time.LocalDate

@Service
class PuntosDeVentaBootstrap(val puntosDeVentaRepository: PuntosDeVentaRepository): InitializingBean {
    val kiosko1 = Kioscos("La Scaloneta", Direccion("bs as","3 de Febrero", "rodriguez peña", 211, Point(2,2)),2, false)
    val kiosko2 = Kioscos("Señor Kioskero", Direccion("bs as","3 de Febrero", "Urquiza", 31, Point(20,20)),20, true)
    val kiosko3 = Kioscos("Un Nombre", Direccion("bs as","Nuñez", "Figueroa Alcorta", 1000, Point(200,12)),1, true)
    val mercado1 = Supermercados("Chinito", Direccion("bs as","3 de Febrero", "Brasil", 211, Point(112,22)),0, SinDescuento())
    val mercado2 = Supermercados("Polque", Direccion("bs as","San Martin", "Bolivia", 211, Point(1,2123)),10, SinDescuento())
    val mercado3 = Supermercados("No hay polque", Direccion("bs as","Liniers", "J.B.Justo", 211, Point(22,233)),30, SinDescuento())
    val libreria1 = Librerias("El Principito", Direccion("bs as","3 de Febrero", "Giorello", 211, Point(2333,22)),30)
    val libreria2 = Librerias("Roberto Carlos", Direccion("bs as","Palermo", "Av. Libertador", 3000, Point(123,2)),31)
    val libreria3 = Librerias("Lapicito", Direccion("bs as","Belgrano", "Av. Cabildo", 2121, Point(2,123)),230)

    fun createPuntosDeVentas() {
        puntosDeVentaRepository.apply {
            kiosko2.addPedidosPendientes(Pedido(2, LocalDate.now()))
            libreria1.addPedidosPendientes(Pedido(1, LocalDate.now()))
            bootstrapAddElements(arrayOf(kiosko1, kiosko2, kiosko3, mercado1, mercado2, mercado3, libreria1, libreria2, libreria3))
        }
    }

    override fun afterPropertiesSet() {
        this.createPuntosDeVentas()
    }
}
