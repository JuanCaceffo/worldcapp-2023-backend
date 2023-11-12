package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.uqbar.geodds.Point
import java.time.LocalDate

@Service
class PuntosDeVentaBootstrap(
    val puntosDeVentaRepository: PuntosDeVentaRepository,
    val usuariosRepositorio: UsuariosRepository

): InitializingBean {
    val kiosko1 = Kioscos("La Scaloneta", Direccion("bs as","3 de Febrero", "rodriguez peña", 211, Point(-34.43123068891678, -58.13359068891678)),2, false)
    val kiosko2 = Kioscos("Señor Kioskero", Direccion("bs as","3 de Febrero", "Urquiza", 31, Point(-34.1111906555678, -58.11111891678)),20, true)
    val kiosko3 = Kioscos("Un Nombre", Direccion("bs as","Nuñez", "Figueroa Alcorta", 1000, Point(-34.433590688678, -58.2891678)),1, true)
    val kiosko4 = Kioscos("Rintintin", Direccion("bs as","Nuñez", "Figueroa Alcorta", 1230, Point(-34.433510688678, -58.2891678)),10, false)
    val kiosko5 = Kioscos("Calambre", Direccion("bs as","Saavedra", "Garcia del Rio", 1000, Point(-34.410590688678, -58.2891678)),5, true)
    val mercado1 = Supermercados("Chinito", Direccion("bs as","3 de Febrero", "Brasil", 211, Point(-34.433891678, -58.43359068891222)),0, SinDescuento())
    val mercado2 = Supermercados("Polque", Direccion("bs as","San Martin", "Bolivia", 211, Point(-34.431, -58.561)),10, SinDescuento())
    val mercado3 = Supermercados("No hay polque", Direccion("bs as","Liniers", "J. B. Justo", 211, Point(-34.3359068891678, -58.43359064561678)),30, SinDescuento())
    val mercado4 = Supermercados("Potato", Direccion("bs as","La tablada", "J. B. Justo", 2110, Point(-34.7359068891678, -58.41359064561678)),3, SinDescuento())
    val mercado5 = Supermercados("Polquelia", Direccion("bs as","La tablada", "J. B. Justo", 11, Point(-34.7359068891678, -58.41359064561678)),77, SinDescuento())
    val libreria1 = Librerias("El Principito", Direccion("bs as","3 de Febrero", "Giorello", 211, Point(-34.3459068891678, -58.43385645691678)),30)
    val libreria2 = Librerias("Roberto Carlos", Direccion("bs as","Palermo", "Av. Libertador", 3000, Point(-34.1119068891678, -55.433586891678)),31)
    val libreria3 = Librerias("Lapicito", Direccion("bs as","Belgrano", "Av. Cabildo", 2121, Point(-35.9993590688916, -59.5906889167888)),230)
    val libreria4 = Librerias("El señor de los lapices", Direccion("bs as","Villa Urquiza", "Av. San Martin", 121, Point(-35.0993590688916, -59.5906889167888)),100)
    val libreria5 = Librerias("El Pela", Direccion("bs as","Belgrano", "Av. Cabildo", 2221, Point(-36.9993590688916, -59.5906889167888)),74)

    fun createPuntosDeVentas() {
        puntosDeVentaRepository.apply {
            kiosko2.addPedidosPendientes(Pedido(2, LocalDate.now()))
            libreria1.addPedidosPendientes(Pedido(1, LocalDate.now()))
            val puntosDeVentas = arrayOf(kiosko1, kiosko2, kiosko3, kiosko4, kiosko5, mercado1, mercado2, mercado3, mercado4, mercado5, libreria1, libreria2, libreria3, libreria4, libreria5)
            puntosDeVentas.forEach { this.create(it) }
        }
    }

    override fun afterPropertiesSet() {
        this.createPuntosDeVentas()
    }
}