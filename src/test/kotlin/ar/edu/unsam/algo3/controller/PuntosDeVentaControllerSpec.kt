package ar.edu.unsam.algo3.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de puntos de venta")
class PuntosDeVentaControllerSpec(@Autowired val mockMvc: MockMvc){
    @Test
    fun `puedo mockear una llamada al endpoint via get y me responde correctamente`() {
        val jsonBody = """    [
                                 {
                                    "id": 2,
                                    "nombre": "Señor Kioskero",
                                    "tipoPuntoDeVenta": "Kiosco",
                                    "direccion": {
                                      "calle": "Urquiza",
                                      "altura": 31,
                                      "ubiGeografica": "x: -34.11119065556780327597152790986001491546630859375, y: -58.1111189167800006316610961221158504486083984375"
                                    },
                                    "direccionPlana": "Urquiza 31",
                                    "geoX": -34.1111906555678,
                                    "geoY": -58.11111891678,
                                    "stockSobres": 20,
                                    "pedidosPendientes": 0,
                                    "distancia": 111.49728538392884,
                                    "precioSobres": 14212.5
                                  }
                              ]
                      """
        mockMvc
            .perform(MockMvcRequestBuilders.get("/puntos-de-venta/usuario/3")
                .param("palabraClave", "Señor Kioskero")
                .param("opcionElegida", "Menor Distancia"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}
