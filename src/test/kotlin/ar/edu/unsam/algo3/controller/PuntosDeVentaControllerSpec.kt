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
			"ubiGeografica": "x: -34.5, y: -58.5"
		},
		"distancia": 9.652853219763065,
		"stockSobres": 2,
		"pendientes": false,
		"precioSobres": 1287.0
	},
	{
		"id": 1,
		"nombre": "Señor Kioskero",
		"tipoPuntoDeVenta": "Kioscos",
		"direccion": {
			"calle": "Urquiza",
			"altura": 31,
			"ubiGeografica": "x: -34.60000000000000142108547152020037174224853515625, y: -58.5"
		},
		"distancia": 19.519103555348373,
		"stockSobres": 20,
		"pendientes": true,
		"precioSobres": 2712.5
	},
	{
		"id": 2,
		"nombre": "Un Nombre",
		"tipoPuntoDeVenta": "Kioscos",
		"direccion": {
			"calle": "Figueroa Alcorta",
			"altura": 1000,
			"ubiGeografica": "x: -34.7000000000000028421709430404007434844970703125, y: -58.5"
		},
		"distancia": 30.26713378578851,
		"stockSobres": 1,
		"pendientes": false,
		"precioSobres": 4087.5
	},
	{
		"id": 3,
		"nombre": "Chinito",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "Brasil",
			"altura": 211,
			"ubiGeografica": "x: -34, y: -58.5"
		},
		"distancia": 48.614614766312364,
		"stockSobres": 0,
		"pendientes": false,
		"precioSobres": 5070.0
	},
	{
		"id": 4,
		"nombre": "Polque",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "Bolivia",
			"altura": 211,
			"ubiGeografica": "x: -34.5, y: -58"
		},
		"distancia": 52.57544959986732,
		"stockSobres": 10,
		"pendientes": false,
		"precioSobres": 5470.0
	},
	{
		"id": 5,
		"nombre": "No hay polque",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "J. B. Justo",
			"altura": 211,
			"ubiGeografica": "x: -34.5, y: -58.89999999999999857891452847979962825775146484375"
		},
		"distancia": 31.335745536780912,
		"stockSobres": 30,
		"pendientes": false,
		"precioSobres": 3370.0
	},
	{
		"id": 6,
		"nombre": "El Principito",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Giorello",
			"altura": 211,
			"ubiGeografica": "x: -34.5, y: -58.60000000000000142108547152020037174224853515625"
		},
		"distancia": 7.952092941551721,
		"stockSobres": 30,
		"pendientes": true,
		"precioSobres": 1228.5
	},
	{
		"id": 7,
		"nombre": "Roberto Carlos",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. Libertador",
			"altura": 3000,
			"ubiGeografica": "x: -34.89999999999999857891452847979962825775146484375, y: -55.5"
		},
		"distancia": 285.30717007421595,
		"stockSobres": 31,
		"pendientes": false,
		"precioSobres": 30208.5
	},
	{
		"id": 8,
		"nombre": "Lapicito",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. Cabildo",
			"altura": 2121,
			"ubiGeografica": "x: -35.5, y: -59.5"
		},
		"distancia": 145.8623633973204,
		"stockSobres": 230,
		"pendientes": false,
		"precioSobres": 15508.5
	}
]
    """
        mockMvc
            .perform(MockMvcRequestBuilders.get("/puntosDeVenta/?userId=0"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}