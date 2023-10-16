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
                "tipoPuntoDeVenta": "Kioscos",
                "direccion": {
                    "calle": "rodriguez peña",
                    "altura": 211,
                    "distancia": 5392.429996594742,
                    "geoLocalizacion": "x: 2123123, y: 28712387"
                },
                "stockSobres": 2,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 1,
                "nombre": "Señor Kioskero",
                "tipoPuntoDeVenta": "Kioscos",
                "direccion": {
                    "calle": "Urquiza",
                    "altura": 31,
                    "distancia": 2494.3859008159975,
                    "geoLocalizacion": "x: 20, y: 20"
                },
                "stockSobres": 20,
                "pendientes": true,
                "precioSobres": 170.0
            },
            {
                "id": 2,
                "nombre": "Un Nombre",
                "tipoPuntoDeVenta": "Kioscos",
                "direccion": {
                    "calle": "Figueroa Alcorta",
                    "altura": 1000,
                    "distancia": 17977.878448859254,
                    "geoLocalizacion": "x: 200, y: 12"
                },
                "stockSobres": 1,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 3,
                "nombre": "Chinito",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "Brasil",
                    "altura": 211,
                    "distancia": 12010.425835780801,
                    "geoLocalizacion": "x: 112, y: 22"
                },
                "stockSobres": 0,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 4,
                "nombre": "Polque",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "Bolivia",
                    "altura": 211,
                    "distancia": 4672.263095589012,
                    "geoLocalizacion": "x: 1, y: 2123"
                },
                "stockSobres": 10,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 5,
                "nombre": "No hay polque",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "J.B.Justo",
                    "altura": 211,
                    "distancia": 14106.902364072614,
                    "geoLocalizacion": "x: 22, y: 233"
                },
                "stockSobres": 30,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 6,
                "nombre": "El Principito",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Giorello",
                    "altura": 211,
                    "distancia": 17825.058949226455,
                    "geoLocalizacion": "x: 2333, y: 22"
                },
                "stockSobres": 30,
                "pendientes": true,
                "precioSobres": 170.0
            },
            {
                "id": 7,
                "nombre": "Roberto Carlos",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Av. Libertador",
                    "altura": 3000,
                    "distancia": 13337.90905435936,
                    "geoLocalizacion": "x: 123, y: 2"
                },
                "stockSobres": 31,
                "pendientes": false,
                "precioSobres": 170.0
            },
            {
                "id": 8,
                "nombre": "Lapicito",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Av. Cabildo",
                    "altura": 2121,
                    "distancia": 13101.135253325981,
                    "geoLocalizacion": "x: 2, y: 123"
                },
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