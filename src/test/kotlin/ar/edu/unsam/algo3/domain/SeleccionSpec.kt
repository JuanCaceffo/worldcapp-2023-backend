package ar.edu.unsam.algo3.domain

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

//------------------------- SELECCIONES -----------------------//
val seleccionArgentina =
  Seleccion(pais = "Argentina", confederacion = Confederacion.CONMEBOL, copasDelMundo = 3, copasConfederacion = 10)
val seleccionBrasil =
  Seleccion(pais = "Brasil", confederacion = Confederacion.CONMEBOL, copasDelMundo = 5, copasConfederacion = 0)
val seleccionUruguay =
  Seleccion(pais = "Uruguay", confederacion = Confederacion.CONMEBOL, copasDelMundo = 2, copasConfederacion = 0)
val seleccionFrancia =
  Seleccion(pais = "Francia", confederacion = Confederacion.UEFA, copasDelMundo = 2, copasConfederacion = 6)
val seleccionChile =
  Seleccion(pais = "Chile", confederacion = Confederacion.CONMEBOL, copasDelMundo = 0, copasConfederacion = 0)
val seleccionAlemania =
  Seleccion(pais = "Alemania", confederacion = Confederacion.UEFA, copasDelMundo = 4, copasConfederacion = 0)

//------------------------- TESTS -----------------------//
class SeleccionSpec : DescribeSpec({
  isolationMode = IsolationMode.InstancePerTest

  describe(name = "Test Selección") {
    it("Seleccion es campeona del mundo si posee, por lo menos, una copa del mundo") {
      seleccionArgentina.esCampeonaDelMundo().shouldBeTrue()
    }
    it("Seleccion NO es campeona del mundo, sino posee ninguna copa del mundo") {
      seleccionChile.esCampeonaDelMundo().shouldBeFalse()
    }
    it("la cantidad de copas de la confederacion a la que pertenece francia es la suma de todas las copas de las selecciones pertenecinetse a dicha confederacion") {
      seleccionFrancia.copasConfederacion shouldBe 6
    }
    it("la cantidad de copas de la confederacion a la que pertenece Argentina es la suma de todas las copas de las selecciones pertenecinetse a dicha confederacion") {
      seleccionArgentina.copasConfederacion shouldBe 10
    }
  }
})