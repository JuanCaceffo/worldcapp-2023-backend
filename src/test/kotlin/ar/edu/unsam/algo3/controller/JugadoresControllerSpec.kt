package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Confederacion
import ar.edu.unsam.algo3.domain.Jugador
import ar.edu.unsam.algo3.domain.Mediocampista
import ar.edu.unsam.algo3.domain.Seleccion
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de jugador")
class JugadoresControllerSpec(@Autowired val mockMvc: MockMvc) {

    @Autowired
    lateinit var jugadoresRepository: JugadorRepository

    @Autowired
    lateinit var seleccionesRepository : SeleccionesRepository


    @BeforeEach
    fun init(){
        jugadoresRepository.clear()
        seleccionesRepository.clear()

        val argentina = Seleccion(pais = "Argentina", Confederacion.CONMEBOL, copasConfederacion = 22, copasDelMundo = 3)

        seleccionesRepository.create(argentina)
        jugadoresRepository.create(
            Jugador(
                nombre = "Gonzalo",
                apellido = "Martinez",
                fechaNacimiento = LocalDate.of(1993, 6, 13),
                nroCamiseta = 10,
                seleccionPerteneciente = argentina,
                posicion = Mediocampista,
                anioDeDebut = 2008,
                altura = 1.72,
                peso = 70.0,
                esLider = true,
                cotizacion = 9000000.0
            )
        )
    }

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
                    .post("/jugador/crear")
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
                    .post("/jugador/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyInfoJugador)
            )
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
    }
    @Test
    fun `El llamado al metodo patch para modificar un jugador es exitoso`() {
        val jsonBodyInfoJugador =
            """
{
  "nombre": "GonzaloElLoquicimo",
  "apellido": "Martinez",
  "nacimiento": "1993-06-13",
  "altura": 1.72,
  "peso": 70.0,
  "camiseta": 10,
  "seleccion": "Argentina",
  "debut": "2008-01-02",
  "posicion": "Mediocampista",
  "esLider": true,
  "cotizacion": 9000000.0,
  "Posiciones": []
}
"""
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .patch("/jugador/0/modificar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonBodyInfoJugador)
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

}