package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.UsuarioLogeadoDTO
import ar.edu.unsam.algo3.dto.UsuarioLoginDTO
import ar.edu.unsam.algo3.dto.loginResponseDTO
import ar.edu.unsam.algo3.repository.UsuariosRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.jsonMapper
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
import org.uqbar.geodds.Point
import java.time.LocalDate

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de usuario")
class UsuarioControllerSpec(@Autowired val mockMvc: MockMvc) {

    @Autowired
    lateinit var userRepositorty: UsuariosRepository

    lateinit var usuario: Usuario

    @BeforeEach
    fun init() {
        userRepositorty.elementos.clear()
        usuario = Usuario(
            apellido = "pablo",
            nombre = "foglia",
            nombreUsuario = "madescoses",
            contrasenia = "pablitoLoco",
            fechaNacimiento = LocalDate.of(2000, 2, 1),
            email = "madescoses@gmail.com",
            direccion = Direccion(
                provincia = "Buenos Aires",
                localidad = "San Martin",
                calle = "matheu",
                altura = 3568,
                ubiGeografica = Point(-34.57461948921918, -58.5378840940197)
            )
        )
        userRepositorty.create(usuario)
    }

    @Test
    fun `puedo mockear una llamada a el meotodo post que logea el usuario y funciona correctamente`() {
        val userData= UsuarioLoginDTO(userName = usuario.nombreUsuario, password = usuario.contrasenia)
        val userResponse= UsuarioLogeadoDTO(userLogedID = usuario.id)
        val mapper= ObjectMapper()
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userData))
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
            .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(userResponse)))
    }
    @Test
    fun `al utilizar el metodo post para logear un usuario que no coincide con ninguno de la base de datos tenemos un error`(){
        val userData = UsuarioLoginDTO(userName = "pablitoLescano", password = "chiquiritabri")
        val mapper= ObjectMapper()
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userData))
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
}