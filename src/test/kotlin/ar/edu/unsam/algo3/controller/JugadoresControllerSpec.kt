package ar.edu.unsam.algo3.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de usuario")
class JugadoresControllerSpec(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `Al realizar un llamado al metodo post para crear un jugador sale exitosamente`() {
        val jsonBodyInfoJugador =
"""
{
  "nombre": "Juanchito",
  "apellido": "Caceffo",
  "nacimiento": "2003-02-01",
  "altura": 1.70,
  "peso": 67,
  "camiseta": 10,
  "seleccion": "Argentina",
  "debut": "2022-01-02",
  "posicion": "Delantero",
  "esLider": true,
  "cotizacion": 1000000,
  "posiciones": []
}                
"""
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/jugadores/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyInfoJugador)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
    @Test
    fun `El llamado al metodo post para crear un jugador falla por que la fecha no tiene el formato correcto`() {
        val jsonBodyInfoJugador =
            """
{
  "nombre": "Juanchito",
  "apellido": "Caceffo",
  "nacimiento": "01-02-2003",
  "altura": 1.70,
  "peso": 67,
  "camiseta": 10,
  "seleccion": "Argentina",
  "debut": "2022-01-02",
  "posicion": "Delantero",
  "esLider": true,
  "cotizacion": 1000000,
  "Posiciones": []
}                
"""
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/jugadores/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyInfoJugador)
            )
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
    }
}