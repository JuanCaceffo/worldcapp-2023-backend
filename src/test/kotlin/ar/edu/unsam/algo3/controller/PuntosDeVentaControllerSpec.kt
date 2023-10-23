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
                    "ubiGeografica": "x: -34.43123068891677718283972353674471378326416015625, y: -58.1335906889167830513542867265641689300537109375"
                },
                "distancia": 39.824643680015846,
                "stockSobres": 2,
                "pendientes": false,
                "precioSobres": 4587.0
            },
            {
                "id": 1,
                "nombre": "Señor Kioskero",
                "tipoPuntoDeVenta": "Kioscos",
                "direccion": {
                    "calle": "Urquiza",
                    "altura": 31,
                    "ubiGeografica": "x: -34.11119065556780327597152790986001491546630859375, y: -58.1111189167800006316610961221158504486083984375"
                },
                "distancia": 55.192374805366086,
                "stockSobres": 20,
                "pendientes": true,
                "precioSobres": 7212.5
            },
            {
                "id": 2,
                "nombre": "Un Nombre",
                "tipoPuntoDeVenta": "Kioscos",
                "direccion": {
                    "calle": "Figueroa Alcorta",
                    "altura": 1000,
                    "ubiGeografica": "x: -34.43359068867800232283116201870143413543701171875, y: -58.289167800000001307125785388052463531494140625"
                },
                "distancia": 25.55501517359048,
                "stockSobres": 1,
                "pendientes": false,
                "precioSobres": 3462.5
            },
            {
                "id": 3,
                "nombre": "Chinito",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "Brasil",
                    "altura": 211,
                    "ubiGeografica": "x: -34.43389167800000194574749912135303020477294921875, y: -58.4335906889122185248197638429701328277587890625"
                },
                "distancia": 12.309793037155538,
                "stockSobres": 0,
                "pendientes": false,
                "precioSobres": 1470.0
            },
            {
                "id": 4,
                "nombre": "Polque",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "Bolivia",
                    "altura": 211,
                    "ubiGeografica": "x: -34.43359458891678315239914809353649616241455078125, y: -58.4335906889100016314841923303902149200439453125"
                },
                "distancia": 12.309769426770224,
                "stockSobres": 10,
                "pendientes": false,
                "precioSobres": 1470.0
            },
            {
                "id": 5,
                "nombre": "No hay polque",
                "tipoPuntoDeVenta": "Supermercados",
                "direccion": {
                    "calle": "J. B. Justo",
                    "altura": 211,
                    "ubiGeografica": "x: -34.33590688916780209183343686163425445556640625, y: -58.43359064561678195559579762630164623260498046875"
                },
                "distancia": 16.42221973037073,
                "stockSobres": 30,
                "pendientes": false,
                "precioSobres": 1870.0
            },
            {
                "id": 6,
                "nombre": "El Principito",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Giorello",
                    "altura": 211,
                    "ubiGeografica": "x: -34.34590688916780010231377673335373401641845703125, y: -58.43385645691677865443125483579933643341064453125"
                },
                "distancia": 15.68921799099085,
                "stockSobres": 30,
                "pendientes": true,
                "precioSobres": 1858.5
            },
            {
                "id": 7,
                "nombre": "Roberto Carlos",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Av. Libertador",
                    "altura": 3000,
                    "ubiGeografica": "x: -34.1119068891677983401677920483052730560302734375, y: -55.43358689167799724373253411613404750823974609375"
                },
                "distancia": 290.1975724693765,
                "stockSobres": 31,
                "pendientes": false,
                "precioSobres": 30733.5
            },
            {
                "id": 8,
                "nombre": "Lapicito",
                "tipoPuntoDeVenta": "Librerias",
                "direccion": {
                    "calle": "Av. Cabildo",
                    "altura": 2121,
                    "ubiGeografica": "x: -35.99935906889160008859107620082795619964599609375, y: -59.59068891678879964501902577467262744903564453125"
                },
                "distancia": 197.34693880780935,
                "stockSobres": 230,
                "pendientes": false,
                "precioSobres": 20968.5
            }
        ]
    """
        mockMvc
            .perform(MockMvcRequestBuilders.get("/puntosDeVenta/?userId=0"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}