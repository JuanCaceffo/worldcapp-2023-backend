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
		"id": 12,
		"nombre": "Lapicito",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. Cabildo",
			"altura": 2121,
			"ubiGeografica": "x: -35.99935906889160008859107620082795619964599609375, y: -59.59068891678879964501902577467262744903564453125"
		},
		"distancia": 344.12869201834286,
		"stockSobres": 230,
		"pendientes": false,
		"precioSobres": 36403.5
	},
	{
		"id": 13,
		"nombre": "El señor de los lapices",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. San Martin",
			"altura": 121,
			"ubiGeografica": "x: -35.09935906889160150967654772102832794189453125, y: -59.59068891678879964501902577467262744903564453125"
		},
		"distancia": 253.74339004151435,
		"stockSobres": 100,
		"pendientes": false,
		"precioSobres": 26848.5
	},
	{
		"id": 9,
		"nombre": "Polquelia",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "J. B. Justo",
			"altura": 11,
			"ubiGeografica": "x: -34.73590688916780067074796534143388271331787109375, y: -58.413590645616778829207760281860828399658203125"
		},
		"distancia": 181.16423488677756,
		"stockSobres": 77,
		"pendientes": false,
		"precioSobres": 18370.0
	},
	{
		"id": 14,
		"nombre": "El Pela",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. Cabildo",
			"altura": 2221,
			"ubiGeografica": "x: -36.99935906889160008859107620082795619964599609375, y: -59.59068891678879964501902577467262744903564453125"
		},
		"distancia": 449.396066456104,
		"stockSobres": 74,
		"pendientes": false,
		"precioSobres": 47428.5
	},
	{
		"id": 11,
		"nombre": "Roberto Carlos",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Av. Libertador",
			"altura": 3000,
			"ubiGeografica": "x: -34.1119068891677983401677920483052730560302734375, y: -55.43358689167799724373253411613404750823974609375"
		},
		"distancia": 281.95596391607603,
		"stockSobres": 31,
		"pendientes": false,
		"precioSobres": 29788.5
	},
	{
		"id": 10,
		"nombre": "El Principito",
		"tipoPuntoDeVenta": "Librerias",
		"direccion": {
			"calle": "Giorello",
			"altura": 211,
			"ubiGeografica": "x: -34.34590688916780010231377673335373401641845703125, y: -58.43385645691677865443125483579933643341064453125"
		},
		"distancia": 138.28497510243807,
		"stockSobres": 30,
		"pendientes": true,
		"precioSobres": 14773.5
	},
	{
		"id": 7,
		"nombre": "No hay polque",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "J. B. Justo",
			"altura": 211,
			"ubiGeografica": "x: -34.33590688916780209183343686163425445556640625, y: -58.43359064561678195559579762630164623260498046875"
		},
		"distancia": 137.18003688618126,
		"stockSobres": 30,
		"pendientes": false,
		"precioSobres": 13970.0
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
		"distancia": 111.49728538392884,
		"stockSobres": 20,
		"pendientes": true,
		"precioSobres": 14212.5
	},
	{
		"id": 6,
		"nombre": "Polque",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "Bolivia",
			"altura": 211,
			"ubiGeografica": "x: -34.4309999999999973852027324028313159942626953125, y: -58.56099999999999994315658113919198513031005859375"
		},
		"distancia": 149.60016617219566,
		"stockSobres": 10,
		"pendientes": false,
		"precioSobres": 15170.0
	},
	{
		"id": 3,
		"nombre": "Rintintin",
		"tipoPuntoDeVenta": "Kioscos",
		"direccion": {
			"calle": "Figueroa Alcorta",
			"altura": 1230,
			"ubiGeografica": "x: -34.4335106886779982460211613215506076812744140625, y: -58.289167800000001307125785388052463531494140625"
		},
		"distancia": 146.86139593426225,
		"stockSobres": 10,
		"pendientes": false,
		"precioSobres": 16357.000000000002
	},
	{
		"id": 4,
		"nombre": "Calambre",
		"tipoPuntoDeVenta": "Kioscos",
		"direccion": {
			"calle": "Garcia del Rio",
			"altura": 1000,
			"ubiGeografica": "x: -34.4105906886779990827562869526445865631103515625, y: -58.289167800000001307125785388052463531494140625"
		},
		"distancia": 144.31447604441308,
		"stockSobres": 5,
		"pendientes": false,
		"precioSobres": 18337.5
	},
	{
		"id": 8,
		"nombre": "Potato",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "J. B. Justo",
			"altura": 2110,
			"ubiGeografica": "x: -34.73590688916780067074796534143388271331787109375, y: -58.413590645616778829207760281860828399658203125"
		},
		"distancia": 181.16423488677756,
		"stockSobres": 3,
		"pendientes": false,
		"precioSobres": 18370.0
	},
	{
		"id": 0,
		"nombre": "La Scaloneta",
		"tipoPuntoDeVenta": "Kioscos",
		"direccion": {
			"calle": "rodriguez peña",
			"altura": 211,
			"ubiGeografica": "x: -34.43123068891677718283972353674471378326416015625, y: -58.1335906889167830513542867265641689300537109375"
		},
		"distancia": 146.8005678753469,
		"stockSobres": 2,
		"pendientes": false,
		"precioSobres": 16357.000000000002
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
		"distancia": 146.87028580832043,
		"stockSobres": 1,
		"pendientes": false,
		"precioSobres": 18587.5
	},
	{
		"id": 5,
		"nombre": "Chinito",
		"tipoPuntoDeVenta": "Supermercados",
		"direccion": {
			"calle": "Brasil",
			"altura": 211,
			"ubiGeografica": "x: -34.43389167800000194574749912135303020477294921875, y: -58.4335906889122185248197638429701328277587890625"
		},
		"distancia": 147.98101429792536,
		"stockSobres": 0,
		"pendientes": false,
		"precioSobres": 14970.0
	}
]
    """
        mockMvc
            .perform(MockMvcRequestBuilders.get("/puntosDeVenta/3"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}