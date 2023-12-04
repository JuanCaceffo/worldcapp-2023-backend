package ar.edu.unsam.algo3.controller

import ar.edu.unsam.algo3.domain.Figurita
import ar.edu.unsam.algo3.dto.FiguritaBaseDTO
import ar.edu.unsam.algo3.dto.FiguritaCreateModifyDTO
import ar.edu.unsam.algo3.service.FiguritaService
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class FiguritaControllerTest {

  private val figuritaService = mockk<FiguritaService>()

  private val mockMvc: MockMvc = setupMockMvc()

  // Instancia de FiguritaBaseDTO accesible para todos los tests
  private val figuritaBaseDTO = FiguritaBaseDTO(
    id = 1,
    numero = 1,
    onFire = false,
    nombre = "NombreEjemplo",
    apellido = "ApellidoEjemplo",
    nivelImpresion = "alta",
    valoracion = 95.5
  )

  private fun setupMockMvc(): MockMvc {
    // Configurar el servicio simulado
    every { figuritaService.crearFigurita(any()) } returns Unit
    every { figuritaService.delete(any()) } returns Unit
    every { figuritaService.delete(any()) } returns Unit
    every { figuritaService.modificarFigurita(any(), any()) } returns Unit

    val controller = FiguritaController(figuritaService)
    return MockMvcBuilders.standaloneSetup(controller).build()
  }

  private val mapper = ObjectMapper()

  @Test
  fun `getById debería devolver una figurita por ID`() {
    val figuritaId = 1

    every { figuritaService.getById(figuritaId) } returns figuritaBaseDTO

    mockMvc.perform(MockMvcRequestBuilders.get("/figurita/$figuritaId"))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(figuritaBaseDTO)))
  }

  @Test
  fun `delete debería eliminar una figurita por ID`() {
    val figuritaId = 1

    mockMvc.perform(MockMvcRequestBuilders.delete("/figurita/eliminar/$figuritaId"))
      .andExpect(MockMvcResultMatchers.status().isOk)
  }

  @Test
  fun `crearFigurita debería crear una nueva figurita`() {
    val infoFigurita = FiguritaCreateModifyDTO(
      numero = 2,
      nombre = "Nicolas Otamendi",
      onFire = true,
      nivelImpresion = "media"
    )

    mockMvc.perform(
      MockMvcRequestBuilders.post("/figurita/crear")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(infoFigurita))
    )
      .andExpect(MockMvcResultMatchers.status().isOk)

  }

  @Test
  fun `modificarFigurita debería modificar una figurita existente`() {
    val figuritaId = 1
    val infoFigurita = FiguritaCreateModifyDTO(
      numero = 1,
      nombre = "Lionel Messi",
      onFire = false,
      nivelImpresion = "baja"
    )

    mockMvc.perform(
      MockMvcRequestBuilders.patch("/figurita/modificar/$figuritaId")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(infoFigurita))
    )
      .andExpect(MockMvcResultMatchers.status().isOk)
  }
}





