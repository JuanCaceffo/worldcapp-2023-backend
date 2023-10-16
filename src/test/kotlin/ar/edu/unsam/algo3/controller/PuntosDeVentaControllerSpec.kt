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
        val jsonBody = """
        [
            {
                "id": 0,
                "nombre": "La Scaloneta",
                "calle": "rodriguez peña",
                "altura": 211,
                "distancia": 2.0,
                "stockSobres": 2,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 1,
                "nombre": "Señor Kioskero",
                "calle": "Urquiza",
                "altura": 31,
                "distancia": 2.0,
                "stockSobres": 20,
                "pendientes": true,
                "precioSobres": 170.0
            },
            {
                "id": 2,
                "nombre": "Un Nombre",
                "calle": "Figueroa Alcorta",
                "altura": 1000,
                "distancia": 2.0,
                "stockSobres": 1,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 3,
                "nombre": "Chinito",
                "calle": "Brasil",
                "altura": 211,
                "distancia": 2.0,
                "stockSobres": 0,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 4,
                "nombre": "Polque",
                "calle": "Bolivia",
                "altura": 211,
                "distancia": 2.0,
                "stockSobres": 10,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 5,
                "nombre": "No hay polque",
                "calle": "J.B.Justo",
                "altura": 211,
                "distancia": 2.0,
                "stockSobres": 30,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 6,
                "nombre": "El Principito",
                "calle": "Giorello",
                "altura": 211,
                "distancia": 2.0,
                "stockSobres": 30,
                "pendientes": true,
                "precioSobres": 170.0
            },
            {
                "id": 7,
                "nombre": "Roberto Carlos",
                "calle": "Av. Libertador",
                "altura": 3000,
                "distancia": 2.0,
                "stockSobres": 31,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 8,
                "nombre": "Lapicito",
                "calle": "Av. Cabildo",
                "altura": 2121,
                "distancia": 2.0,
                "stockSobres": 230,
                "pendientes": false,
                "precioSobres": 170.0
            }
        ]
    """
        mockMvc
            .perform(MockMvcRequestBuilders.get("/puntosDeVenta/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}