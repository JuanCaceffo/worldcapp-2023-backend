package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.bootstrap.PuntosDeVentaBootstrap
import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Kiosco
import ar.edu.unsam.algo3.dto.DireccionMarketDTO
import ar.edu.unsam.algo3.dto.MarketDTO
import ar.edu.unsam.algo3.repository.PuntosDeVentaRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.uqbar.geodds.Point

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de puntos de venta")
class PuntosDeVentaControllerSpec(@Autowired val mockMvc: MockMvc) {
  @Autowired
  lateinit var puntosDeVentaBoostrap: PuntosDeVentaBootstrap

  @Autowired
  lateinit var puntosDeVentaRepository: PuntosDeVentaRepository

  private final val punto = Point(
    -34.1111906555678,
    -58.11111891678
  )

  private final val puntoBorrable = Point(
    -31.23123068891678,
    -57.13359068891678
  )

  private final val direccion = Direccion(
    calle = "Urquiza",
    altura = 31,
    ubiGeografica = punto,
    provincia = "bs as",
    localidad = "3 de Febrero"
  )

  private final val direccionBorrable = Direccion(
    calle = "Valla a saber donde vive",
    altura = 666,
    ubiGeografica = puntoBorrable,
    provincia = "bs as",
    localidad = "3 de Febrero"
  )

  val puestoDeVenta = Kiosco(direccion = direccion, stockSobres = 20, nombre = "Señor Kioskero", hayEmpleados = true)
  val puestoDeVentaBorrable =
    Kiosco(direccion = direccionBorrable, stockSobres = 0, nombre = "El Inútil", hayEmpleados = false)

  @BeforeEach
  fun init() {
    puntosDeVentaRepository.clear()
    puntosDeVentaRepository.create(puestoDeVenta)
    puntosDeVentaRepository.create(puestoDeVentaBorrable)
  }

  @AfterEach
  fun end() {
    puntosDeVentaBoostrap.afterPropertiesSet()
  }

  val mapper = ObjectMapper()

  @Test
  fun `Al realizar un llamado al metodo post para crear un puesto de venta sale exitosamente`() {
    val infoPuesto = MarketDTO(
      id = 2,
      nombre = "Señor Kioskero",
      tipoPuntoDeVenta = "Kiosco",
      direccion = DireccionMarketDTO(calle = "Urquiza", altura = 31, ubiGeografica = punto.toString()),
      stockSobres = 20,
      pedidosPendientes = 0,
      distancia = 111.49728538392884,
      precioSobres = 14212.5
    )

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .post("/punto-de-venta/nuevo")
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(infoPuesto))
      )
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `El llamado al metodo put para modificar un puesto de venta es exitoso`() {
    val infoPuesto = MarketDTO(
      id = 2,
      nombre = "Señor Kioskero Nuevo",
      tipoPuntoDeVenta = "Kiosco",
      direccion = DireccionMarketDTO(calle = "Rivadavia", altura = 99, ubiGeografica = punto.toString()),
      stockSobres = 20,
      pedidosPendientes = 0,
      distancia = 111.49728538392884,
      precioSobres = 14212.5
    )

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .put("/punto-de-venta/editar")
          .contentType(MediaType.APPLICATION_JSON)
          .content(mapper.writeValueAsString(infoPuesto))
      )
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `El llamado al metodo delete para eliminar un puesto de venta con condiciones que impiden su eliminacion genera un 404`() {
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete("/punto-de-venta/0/eliminar")
      )
      .andExpect(MockMvcResultMatchers.status().is4xxClientError)
  }

  @Test
  fun `El llamado al metodo delete para eliminar un puesto de venta se procesa correctamente`() {
    mockMvc
      .perform(
        MockMvcRequestBuilders
          .delete("/punto-de-venta/1/eliminar")
      )
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `El llamado al metodo get para obtener un puesto de venta por id funciona`() {
    val infoPuesto = MarketDTO(
      id = 0,
      nombre = "Señor Kioskero",
      tipoPuntoDeVenta = "Kiosco",
      direccion = DireccionMarketDTO(calle = "Urquiza", altura = 31, ubiGeografica = punto.toString()),
      stockSobres = 20,
      pedidosPendientes = 0,
      distancia = 0.0,
      precioSobres = 0.0
    )

    mockMvc
      .perform(
        MockMvcRequestBuilders
          .get("/punto-de-venta/0")
      )
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(infoPuesto)))
  }
}