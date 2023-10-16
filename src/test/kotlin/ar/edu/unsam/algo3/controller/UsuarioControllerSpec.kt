package ar.edu.unsam.algo3.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Dado un controller de usuario")
class UsuarioControllerSpec(@Autowired val mockMvc: MockMvc) {

    @Test
    fun 'puedo mockear una llamada a el meotod post que logea el usuario y funciona correctamente'() {

    }
}