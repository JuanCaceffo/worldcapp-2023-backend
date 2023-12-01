package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.dto.FiguritaCreateModifyDTO
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
@DisplayName("Dado un controller de figuritas")
class FiguritaControllerSpec(@Autowired val mockMvc: MockMvc){
  @Test
  fun `puedo mockear una llamada al endpoint via get con parametro de palabraClave y me responde correctamente`() {
    val figuritaPepe = """
  [
    {
      "id": 2,
      "idUsuario": 1,
      "onFire": true,
      "numero": 3,
      "valorBase": 100,
      "nivelImpresion": "media",
      "promesa": false,
      "peso": 69,
      "nombre": "Pepe",
      "apellido": "Argento",
      "nroCamiseta": 5,
      "fechaNac": "1993-02-12",
      "edad": 30,
      "seleccion": "Argentina",
      "posicion": "Arquero",
      "cotizacion": 1700000,
      "anioDebut": 2012,
      "copasDelMundo": 3,
      "confederacion": "CONMEBOL",
      "confederacionCopas": 22,
      "esLider": true,
      "valoracion": 100,
      "altura": 1.69,
      "duenio": "madescoses"
    }
  ]
  """
    mockMvc
      .perform(MockMvcRequestBuilders.get("/figuritas/intercambiar/0")
        .param("palabraClave","Pepe"))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().json(figuritaPepe))
  }

  @Test
  fun `puedo hacer una llamada al endpoint via get con un ID inexistente y obtengo error 404`() {
    mockMvc
      .perform(MockMvcRequestBuilders.get("/figuritas/intercambiar/-1"))
      .andExpect(MockMvcResultMatchers.status().is4xxClientError)
  }
  @Test
  fun `puedo obtener una figurita por su ID`() {
    mockMvc
      .perform(MockMvcRequestBuilders.get("/figurita/1"))
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `puedo eliminar una figurita por su ID`() {
    mockMvc
      .perform(MockMvcRequestBuilders.delete("/figurita/eliminar/1"))
      .andExpect(MockMvcResultMatchers.status().isOk)

  }
}