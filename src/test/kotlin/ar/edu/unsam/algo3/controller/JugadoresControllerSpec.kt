package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.bootstrap.FiguritasBoostrap
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.InfoCrearModificarJugadorDTO
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
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
    lateinit var figusBoostrap: FiguritasBoostrap
    @Autowired
    lateinit var jugadoresRepository: JugadorRepository
    @Autowired
    lateinit var seleccionesRepository : SeleccionesRepository
    @Autowired
    lateinit var figuritasRepository: FiguritasRepository

    val jugador = Jugador(
        nombre = "Gonzalo",
        apellido = "Martinez",
        fechaNacimiento = LocalDate.of(1993, 6, 13),
        nroCamiseta = 10,
        seleccionPerteneciente = seleccionArgentina,
        posicion = Mediocampista,
        anioDeDebut = 2008,
        altura = 1.72,
        peso = 70.0,
        esLider = true,
        cotizacion = 9000000.0
    )
    @BeforeEach
    fun init(){
        jugadoresRepository.clear()
        seleccionesRepository.clear()
        figuritasRepository.clear()

        seleccionesRepository.create(seleccionArgentina)
        jugadoresRepository.create(jugador)
    }
    @AfterEach
    fun end(){
        figusBoostrap.afterPropertiesSet()
    }
    val mapper= ObjectMapper()

    @Test
    fun `Al realizar un llamado al metodo post para crear un jugador sale exitosamente`() {
        val infoJugador = InfoCrearModificarJugadorDTO(
                nombre = "Juanchito",
                apellido = "Caceffo",
                fechaNacimiento = "2003-02-01",
                altura = 1.70,
                peso = 67.0,
                nroCamiseta = 10,
                seleccion = "Argentina",
                debut = 2022,
                posicion = "Delantero",
                posiciones = null,
                esLider = true,
                cotizacion = 1000000.0,
            )

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/jugador/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(infoJugador))
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
    @Test
    fun `El llamado al metodo post para crear un jugador falla por que la fecha no tiene el formato correcto`() {
        val infoJugador = InfoCrearModificarJugadorDTO(
            nombre = "Juanchito",
            apellido = "Caceffo",
            fechaNacimiento = "01-02-2003",
            altura = 1.70,
            peso = 67.0,
            nroCamiseta = 10,
            seleccion = "Argentina",
            debut = 2022,
            posicion = "Delantero",
            posiciones = null,
            esLider = true,
            cotizacion = 1000000.0,
        )
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/jugador/crear")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString((infoJugador)))
            )
            .andExpect(MockMvcResultMatchers.status().is5xxServerError)
    }
    @Test
    fun `El llamado al metodo patch para modificar un jugador es exitoso`() {
        val infoJugador = InfoCrearModificarJugadorDTO(
            nombre = "GonzaloElLoquicimo",
            apellido = "Martinez",
            fechaNacimiento = "1993-06-13",
            altura = 1.72,
            peso =  70.0,
            nroCamiseta = 10,
            seleccion = "Argentina",
            debut = 2008,
            posicion = "Mediocampista",
            posiciones = null,
            esLider = true,
            cotizacion = 9000000.0,
        )

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .patch("/jugador/0/modificar")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(infoJugador))
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `El llamado al metodo delete para eliminar un jugador funciona correctamente`() {

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete("/jugador/0/eliminar")
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `El llamado al metodo delete para eliminar un jugador que esta siendo utilizado en una figurita falla`() {
        figuritasRepository.create(Figurita(numero = 1, onFire = false, cantidadImpresa = impresionBaja, jugador = jugador))

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete("/jugador/0/eliminar")
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

    @Test
    fun `El llamado al metodo get para obtener un jugador por id funciona`(){
        val jugadorEncontrado = InfoCrearModificarJugadorDTO(
            nombre = "Gonzalo",
            apellido = "Martinez",
            fechaNacimiento = "1993-06-13",
            altura = 1.72,
            peso =  70.0,
            nroCamiseta = 10,
            seleccion = "Argentina",
            debut = 2008,
            posicion = "Mediocampista",
            posiciones = listOf<String>(),
            esLider = true,
            cotizacion = 9000000.0,
        )

        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get("/jugador/0")
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(jugadorEncontrado)))
    }
}