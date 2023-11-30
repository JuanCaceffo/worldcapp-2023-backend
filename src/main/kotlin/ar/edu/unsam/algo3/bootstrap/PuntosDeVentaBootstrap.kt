package ar.edu.unsam.algo3.bootstrap

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import ar.edu.unsam.algo3.repository.UsuariosRepository
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.DependsOn
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.uqbar.geodds.Point
import java.time.LocalDate

@Component
@Order(2)
@DependsOn("usuariosBoostrap")
class PuntosDeVentaBootstrap(
  val puntosDeVentaRepository: PuntosDeVentaRepository,
  val usuariosRepositorio: UsuariosRepository

) : InitializingBean {
  private val puntosDeVenta = mapOf(
    "kiosko0" to Kioscos(
      "El Inútil",
      Direccion("bs as", "Villa Ballester", "Valla a saber donde vive", 666, Point(-31.23123068891678, -57.13359068891678)),
      0,
      false
    ),
    "kiosko1" to Kioscos(
      "La Scaloneta",
      Direccion("bs as", "3 de Febrero", "rodriguez peña", 211, Point(-34.43123068891678, -58.13359068891678)),
      0,
      false
    ),
    "kiosko2" to Kioscos(
      "Señor Kioskero",
      Direccion("bs as", "3 de Febrero", "Urquiza", 31, Point(-34.1111906555678, -58.11111891678)),
      20,
      true
    ),
    "kiosko3" to Kioscos(
      "Un Nombre",
      Direccion("bs as", "Nuñez", "Figueroa Alcorta", 1000, Point(-34.433590688678, -58.2891678)),
      1,
      true
    ),
    "kiosko4" to Kioscos(
      "Rintintin",
      Direccion("bs as", "Nuñez", "Figueroa Alcorta", 1230, Point(-34.433510688678, -58.2891678)),
      10,
      false
    ),
    "kiosko5" to Kioscos(
      "Calambre",
      Direccion("bs as", "Saavedra", "Garcia del Rio", 1000, Point(-34.410590688678, -58.2891678)),
      5,
      true
    ),
    "mercado1" to Supermercados(
      "Chinito",
      Direccion("bs as", "3 de Febrero", "Brasil", 211, Point(-34.433891678, -58.43359068891222)),
      0,
      SinDescuento()
    ),
    "mercado2" to Supermercados(
      "Polque",
      Direccion("bs as", "San Martin", "Bolivia", 211, Point(-34.431, -58.561)),
      10,
      SinDescuento()
    ),
    "mercado3" to Supermercados(
      "No hay polque",
      Direccion("bs as", "Liniers", "J. B. Justo", 211, Point(-34.3359068891678, -58.43359064561678)),
      30,
      SinDescuento()
    ),
    "mercado4" to Supermercados(
      "Potato",
      Direccion("bs as", "La tablada", "J. B. Justo", 2110, Point(-34.7359068891678, -58.41359064561678)),
      3,
      SinDescuento()
    ),
    "mercado5" to Supermercados(
      "Polquelia",
      Direccion("bs as", "La tablada", "J. B. Justo", 11, Point(-34.7359068891678, -58.41359064561678)),
      77,
      SinDescuento()
    ),
    "libreria1" to Librerias(
      "El Principito",
      Direccion("bs as", "3 de Febrero", "Giorello", 211, Point(-34.3459068891678, -58.43385645691678)),
      30
    ),
    "libreria2" to Librerias(
      "Roberto Carlos",
      Direccion("bs as", "Palermo", "Av. Libertador", 3000, Point(-34.1119068891678, -55.433586891678)),
      31
    ),
    "libreria3" to Librerias(
      "Lapicito",
      Direccion("bs as", "Belgrano", "Av. Cabildo", 2121, Point(-35.9993590688916, -59.5906889167888)),
      230
    ),
    "libreria4" to Librerias(
      "El señor de los lapices",
      Direccion("bs as", "Villa Urquiza", "Av. San Martin", 121, Point(-35.0993590688916, -59.5906889167888)),
      100
    ),
    "libreria5" to Librerias(
      "El Pela",
      Direccion("bs as", "Belgrano", "Av. Cabildo", 2221, Point(-36.9993590688916, -59.5906889167888)),
      74
    )
  )

  fun createPuntosDeVentas() {
    puntosDeVenta["kiosko1"]?.addPedidosPendientes(Pedido(2, LocalDate.now()))
    puntosDeVenta["libreria1"]?.addPedidosPendientes(Pedido(1, LocalDate.now()))
    puntosDeVenta.values.forEach { punto -> puntosDeVentaRepository.apply { create(punto) } }
  }

  override fun afterPropertiesSet() {
    println("INICIO EL PROCESO DE CREACIÓN DE PUNTOS DE VENTA.")
    this.createPuntosDeVentas()
    println("FIN DE PUNTOS DE VENTA.")
  }
}