package ar.edu.unsam.algo3.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ServicioSeleccionSpec: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe(name= "Test Servicio Externo Selección"){
        it("Devuelve 3 selecciones"){
            val seleccionesTraidas = StubServicioSeleccion.arraySeleccionesDesdeApi().map{Seleccion.crear(it)}
            seleccionesTraidas.size.shouldBe(3)
        }

        it("Se lanza excepción al pasar mal un nombre de Confederación"){
            shouldThrow<IllegalArgumentException> { StubServicioSeleccionConfederacionIncorrecta.arraySeleccionesDesdeApi().map{Seleccion.crear(it)} }
        }

        it("Recibe un array vacio"){
            val seleccionesTraidas = StubServicioSeleccionVacio.arraySeleccionesDesdeApi().map{Seleccion.crear(it)}
            seleccionesTraidas.size.shouldBe(0)
        }
    }
})

interface ServiceSeleccionDevuelveArray: ServiceSelecciones {
    fun arraySeleccionesDesdeApi(): List<SeleccionDataExterna> = Gson().fromJson(this.getSelecciones(), object : TypeToken<List<SeleccionDataExterna>>() {}.type)
}
object StubServicioSeleccion: ServiceSeleccionDevuelveArray {
    override fun getSelecciones() = """
[
  {
    "id": 1,
    "pais": "Argentina",
    "confederacion": "CONMEBOL",
    "copasDelMundo": 3,
    "copasConfederacion": 15
  },
  {
    "id": 2,
    "pais": "Brasil",
    "confederacion": "CONMEBOL",
    "copasDelMundo": 5,
    "copasConfederacion": 9
  },
  {
    "pais": "Alemania",
    "confederacion": "UEFA",
    "copasDelMundo": 4,
    "copasConfederacion": 3
  }
]   
"""
}
object StubServicioSeleccionConfederacionIncorrecta: ServiceSeleccionDevuelveArray {
    override fun getSelecciones() = """
[
  {
    "id": 2,
    "pais": "Brasil",
    "confederacion": "COIMABOL",
    "copasDelMundo": 5,
    "copasConfederacion": 9
  }
]   
"""
}

object StubServicioSeleccionVacio: ServiceSelecciones {
    override fun getSelecciones() = "[]"
    fun arraySeleccionesDesdeApi(): List<SeleccionDataExterna> = Gson().fromJson(this.getSelecciones(), object : TypeToken<List<SeleccionDataExterna>>() {}.type)
}
