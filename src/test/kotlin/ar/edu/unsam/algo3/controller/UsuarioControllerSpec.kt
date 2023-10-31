package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Direccion
import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.domain.Usuario
import ar.edu.unsam.algo3.dto.*
import ar.edu.unsam.algo3.repository.FiguritasRepository
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
    @Autowired
    lateinit var figusRepositorty: FiguritasRepository

    lateinit var usuario: Usuario
    lateinit var usuarioLogeado: Usuario

    @BeforeEach
    fun init() {
        userRepositorty.clear()
        usuario = Usuario(
            apellido = "foglia",
            nombre = "pablo",
            nombreUsuario = "madescoses",
            contrasenia = "pablitoLoco",
            fechaNacimiento = LocalDate.of(2000, 2, 1),
            email = "madescoses@gmail.com",
            imagenPath = "/src/assets/images/card-img-10.jpg",
            direccion = Direccion(
                provincia = "Buenos Aires",
                localidad = "San Martin",
                calle = "matheu",
                altura = 3568,
                ubiGeografica = Point(-34.57461948921918, -58.5378840940197)
            )
        )
        usuarioLogeado = Usuario(
            apellido = "juan",
            nombre = "caceffo",
            nombreUsuario = "juanceto01",
            contrasenia = "sacaleno",
            fechaNacimiento = LocalDate.of(2003, 2, 1),
            email = "juanchi@gmail.com",
            imagenPath = "/src/assets/images/card-img-10.jpg",
            direccion = Direccion(
                provincia = "Buenos Aires",
                localidad = "San Martin",
                calle = "Av. Rodríguez Peña",
                altura = 3237,
                ubiGeografica = Point(-34.58424206690573, -58.52112943577023)
            ),

        )
        userRepositorty.create(usuario)
        userRepositorty.create(usuarioLogeado)
    }
    val mapper= ObjectMapper()

    @Test
    fun `puedo mockear una llamada a el meotodo post que logea el usuario y funciona correctamente`() {
        val userData= UsuarioLoginDTO(userName = usuario.nombreUsuario, password = usuario.contrasenia)
        val userResponse= UsuarioLogeadoDTO(userLogedID = usuario.id)
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
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/user/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userData))
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }
    @Test
    fun `Al utilizar el endpoint de patch para que el usuario logeado le pida una figu a otro sale bien`(){
        repeat(2){usuario.addFiguritaRepetida(figusRepositorty.getById(0))}
        val ReqeustData = RequestFiguDTO(userLogedID = 1, requestedFiguID = 0, requestedUserID = 0)
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .patch("/user/request-figurita")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(ReqeustData))
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
    }
    @Test
    fun `Al utilizar el endpoint de patch para que el usuario logeado le pida una figu a otro usuario lejano no sale bien`(){
        repeat(2){usuario.recibirFigurita(figusRepositorty.getById(0))}
        usuario.direccion.ubiGeografica.x= 0.0
        usuario.direccion.ubiGeografica.x= 1.0
        val ReqeustData = RequestFiguDTO(userLogedID = 1, requestedFiguID = 0, requestedUserID = 0)
        mockMvc
            .perform(
                MockMvcRequestBuilders
                    .patch("/user/request-figurita")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(ReqeustData))
            )
            .andExpect(MockMvcResultMatchers.status().is4xxClientError)
    }

        @Test
        fun `mockeo el request con un determinado usuario al endpoint via get y responde correctamente`() {
            val jsonBody = """
{
	"name": "caceffo",
	"lastName": "juan",
	"email": "juanchi@gmail.com",
	"birthdate": "2003-02-01",
	"address": {
		"provincia": "Buenos Aires",
		"localidad": "San Martin",
		"calle": "Av. Rodríguez Peña",
		"altura": 3237,
		"ubiGeografica": {
			"x": -34.58424206690573,
			"y": -58.52112943577023
		}
	},
	"exchangeProximity": 5,
	"criteria": "Nacionalista"
}
    """
            mockMvc
                .perform(MockMvcRequestBuilders.get("/user/2/info-profile"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(jsonBody))
        }

    @Test
    fun `Envio request de edicion de usuario y luego via get responde con los datos que fueron cambiados correctamente`() {
        val jsonBody = """
{
	"name": "Pablo El Loquito Daniel",
	"lastName": "pablo",
	"email": "madescoses@gmail.com",
	"birthdate": "2000-02-01",
	"address": {
		"provincia": "Buenos Aires",
		"localidad": "San Martin",
		"calle": "matheu",
		"altura": 3568,
		"ubiGeografica": {
			"x": -34.57461948921918,
			"y": -58.5378840940197
		}
	},
	"exchangeProximity": 5,
	"criteria": "Desprendido"
}
    """
        usuario.nombre = "Pablo El Loquito Daniel"
        val infoProfile = usuario.toInfoProfileDTO()
        mockMvc.perform(
            MockMvcRequestBuilders
                .patch("/user/1/info-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(infoProfile)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(jsonBody))
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/user/1/info-profile"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().json(jsonBody))
    }
}
