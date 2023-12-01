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
@DisplayName("Dado un controller del Dashboard")
class DashboardControllerSpec(@Autowired val mockMvc: MockMvc){
  @Test
  fun `puedo mockear una llamada al endpoint via get y me responde correctamente`() {
    val dashboardStatics = """
 [
	{
		"quantity": 30,
		"name": "Figuritas Ofrecidas"
	},
	{
		"quantity": 11,
		"name": "Figuritas Faltantes"
	},
	{
		"quantity": 16,
		"name": "Puntos de Ventas"
	},
	{
		"quantity": 5,
		"name": "Usuarios Activos"
	},
	{
		"quantity": 11,
		"name": "Selecciones Activas"
	}
]
  """
    mockMvc
      .perform(MockMvcRequestBuilders.get("/dashboard"))
      .andExpect(MockMvcResultMatchers.status().isOk)
      .andExpect(MockMvcResultMatchers.content().json(dashboardStatics))
  }
}