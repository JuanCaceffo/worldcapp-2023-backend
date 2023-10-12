package ar.edu.unsam.algo3.repository

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import org.uqbar.geodds.Point
import java.time.LocalDate

////////////////////////// TESTS ////////////////////////////
class ProcesosSpec: DescribeSpec  ({

    isolationMode = IsolationMode.InstancePerTest

    describe("TEST PROCESOS"){
        val stubMailSender = StubMailSender()
        val admin = Admin()

        //Cambios de nombres a variables para hacer los tests mas descriptivos y faciles de entender
        val userActivo1 = sol.copy()
        val userActivo2 = alejo.copy()
        val userInactivo = pablo.copy()
        val negocioActivo = Kioscos(
            nombre = "almacencito",
            direccion = Direccion(
                provincia = "Buenos Aires",
                localidad = "Tigre",
                calle = "Chacabuco",
                altura = 485,
                ubiGeografica = Point(-34.42573246103574, -58.57456173787252)
            ),
            stockSobres = 0,
            hayEmpleados = false
        )
        val negocioInactivo = Kioscos(
            nombre = "almacenJose",
            direccion = Direccion(
                provincia = "Buenos Aires",
                localidad = "Tigre",
                calle = "daen Funes",
                altura = 3701,
                ubiGeografica = Point(-34.40080775469729, -58.69824375796062)
            ),
            stockSobres = 0,
            hayEmpleados = true
        )

        val repositorioUsers = RepositorioUsuarios().apply {
            create(userActivo1)
            create(userActivo2)
            create(userInactivo)
        }

        val repositorioFigus = RepositorioFiguritas().apply {
            create(figu1Emi)
            create(figu2Messi)
            create(figu3Mbappe)
        }

        val repositorioPuntosVenta = RepositorioPuntosDeVenta().apply {
            create(negocioActivo)
            create(negocioInactivo)
        }

        val repositorioSelecciones = RepositorioSelecciones().apply {
            create(Seleccion(pais="Argentina", confederacion=CONMEBOL, copasDelMundo = 2, copasConfederacion = 18))
            create(seleccionBrasil)
            create(seleccionAlemania)
            create(seleccionUruguay)
            create(seleccionFrancia)
            create(seleccionChile)
        }

        val pedido1 = Pedido(20,LocalDate.now().plusDays(69))
        val pedido2 = Pedido(10,LocalDate.now().plusDays(108))
        val pedido3 = Pedido(40,LocalDate.now().plusDays(40))

        it("Un proceso vacio no puede ejecutarse"){
            shouldThrow<BusinessException>{admin.run()}
        }

        describe("Proceso - Modificar OnFire") {
            it("Un proceso sobre una repositorio de Figus, que modifica una lista especifica a OnFire") {
                val nrosAModificar = mutableListOf(9)

                admin.addProcess(CambiarAOnFire(repositorioFigus, nrosAModificar, stubMailSender))
                admin.run()
                repositorioFigus.search("9").first().onFire.shouldBe(true)
                repositorioFigus.search("13").first().onFire.shouldBe(false)
                stubMailSender.mailsEnviados.size.shouldBe(1)
            }
        }

        describe("Proceso - Update con Service Selecciones") {
            it("Un proceso sobre una repositorio de Selecciones, que se actualiza segun el ServiceSelecciones") {

                val service = ServicioSeleccionCreaJson().getSelecciones()

                admin.addProcess(UpdateSelecciones(repositorioSelecciones,service,stubMailSender))
                admin.run()

                repositorioSelecciones.search("Argentina").first().copasDelMundo.shouldBe(3)
            }
        }

        describe("Proceso - Eliminar usuarios inactivos") {
            it("Un proceso sobre una repositorio de Usuario que elimina a los usuarios inactivos") {
                userActivo1.addFiguritaFaltante(figuritaBase)
                userActivo2.recibirFigurita(figuritaBase)
                userActivo2.recibirFigurita(figuritaBase)

                admin.addProcess(BorrarUserInactivo(repositorioUsers,stubMailSender))

                admin.run()
                repositorioUsers.elementos.values.shouldContain(userActivo1)
                repositorioUsers.elementos.values.shouldContain(userActivo2)
                repositorioUsers.elementos.values.shouldNotContain(userInactivo)
                stubMailSender.mailsEnviados.size.shouldBe(1)
            }
        }

        describe("Proceso - Eliminar puntos de venta inactivos") {
            it("Un proceso sobre una repositorio de Puntos de Venta que elimina a los Puntos inactivos") {
                negocioActivo.addPedidosPendientes(pedido1)
                negocioInactivo.addPedidosPendientes(pedido2)

                admin.addProcess(BorrarPuntosVentaInactivo(repositorioPuntosVenta,stubMailSender))
                admin.run()
                repositorioPuntosVenta.elementos.values.shouldContain(negocioActivo)
                repositorioPuntosVenta.elementos.values.shouldNotContain(negocioInactivo)
                stubMailSender.mailsEnviados.size.shouldBe(1)

            }
        }

        describe("Proceso - Actualizar pedidos recibidos") {
            it("Un proceso sobre un Punto de Venta que actualiza de sus pendientes los pedidos que ya se recibieron") {

                negocioActivo.addPedidosPendientes(pedido1)
                negocioActivo.addPedidosPendientes(pedido2)
                negocioInactivo.addPedidosPendientes(pedido3)
                val pedidosRecibidos = mutableListOf(pedido2, pedido3)

                admin.addProcess(UpdateStockPuntosVenta(repositorioPuntosVenta, pedidosRecibidos,stubMailSender))
                admin.run()

                negocioActivo.pedidosPendientes.shouldContain(pedido1)
                negocioActivo.pedidosPendientes.shouldNotContain(pedido2)
                negocioInactivo.pedidosPendientes.shouldNotContain(pedido2)
                stubMailSender.mailsEnviados.size.shouldBe(1)
            }
        }

        describe ("TEST PROCESOS COMPUESTOS"){
            it ("Un proceso con todos juntos"){
                val pedidosRecibidos = mutableListOf(pedido2, pedido3)
                val nrosAModificar = mutableListOf(9)
                val service = ServicioSeleccionCreaJson().getSelecciones()

                userActivo1.addFiguritaFaltante(figuritaBase)
                userActivo2.recibirFigurita(figuritaBase)
                userActivo2.recibirFigurita(figuritaBase)

                negocioActivo.addPedidosPendientes(pedido1)
                negocioActivo.addPedidosPendientes(pedido2)
                negocioInactivo.addPedidosPendientes(pedido2)
                negocioInactivo.addPedidosPendientes(pedido3)

                admin.addProcess(UpdateSelecciones(repositorioSelecciones,service,stubMailSender))
                admin.addProcess(UpdateStockPuntosVenta(repositorioPuntosVenta, pedidosRecibidos,stubMailSender))
                admin.addProcess(CambiarAOnFire(repositorioFigus,nrosAModificar,stubMailSender))
                admin.addProcess(BorrarPuntosVentaInactivo(repositorioPuntosVenta,stubMailSender))
                admin.addProcess(BorrarUserInactivo(repositorioUsers,stubMailSender))

                admin.run()

                repositorioUsers.elementos.values.shouldContain(userActivo1)
                repositorioUsers.elementos.values.shouldNotContain(userInactivo)

                repositorioPuntosVenta.elementos.values.shouldContain(negocioActivo)
                repositorioPuntosVenta.elementos.values.shouldNotContain(negocioInactivo)

                repositorioFigus.search("9").first().onFire.shouldBe(true)
                repositorioFigus.search("13").first().onFire.shouldBe(false)

                negocioActivo.pedidosPendientes.shouldContain(pedido1)
                negocioActivo.pedidosPendientes.shouldNotContain(pedido2)
                negocioInactivo.pedidosPendientes.shouldNotContain(pedido2)

                stubMailSender.mailsEnviados.size.shouldBe(5)
                stubMailSender.mailsEnviados.last().content.shouldBe("Se realizó el proceso: BorrarUserInactivo")
            }
        }
    }
})