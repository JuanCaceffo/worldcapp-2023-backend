package ar.edu.unsam.algo3.repository

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.uqbar.geodds.Point

//---------------------- ELEMENTOS ----------------------//
val figu1Emi = figuritaBase
val figu2Messi = figuritaValorMaximo
val figu3Mbappe = figuritaNroCopasPar

//---------------------- TESTS ----------------------//
class RepositorioSpec: DescribeSpec({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test Colecciones") {

        describe("Dada una repositorio de Figuritas"){
            val repositorioFiguritas = Repositorio<Figurita>()
            repositorioFiguritas.create(figu1Emi)
            repositorioFiguritas.create(figu2Messi)
            repositorioFiguritas.create(figu3Mbappe)

            it("Si se agregan x cant de figuritas a una repositorio vacia, el id de la ultima agregada deberia ser el size de la repositorio menos 1."){
                figu3Mbappe.id.shouldBe(repositorioFiguritas.elementos.size - 1)
            }

            it("Al borrar una figurita esta no debe pertenercer mas a la repositorio"){
                repositorioFiguritas.delete(figu2Messi)
                shouldThrow<IllegalArgumentException> {
                    repositorioFiguritas.getById(figu2Messi.id)
                }.message shouldBe MENSAJE_ERROR_ID_INEXISTENTE
            }

            describe("Dada una busqueda parcial"){
                it("La figurita debe ser parte de la lista de busqueda si coincide parte del texto en el nombre"){
                    repositorioFiguritas.search("emi").shouldContain(figu1Emi)
                }
                it("La figurita debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    repositorioFiguritas.search("mes").shouldContain(figu2Messi)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    repositorioFiguritas.search("#").shouldBeEmpty()
                }
            }

            describe("Dada una busqueda exacta"){
                it("La figurita debe ser parte de la lista de busqueda si coincide el numero"){
                    repositorioFiguritas.search("9").shouldContain(figu1Emi)
                }
                it("La figurita debe ser parte de la lista de busqueda si coincide el nombre del pais"){
                    repositorioFiguritas.search("argentina").shouldContainAll(figu1Emi, figu2Messi)
                }
            }

        }

        describe("Dada una repositorio de Usuarios"){
            val repositorioUsuarios = Repositorio<Usuario>()
            repositorioUsuarios.create(sol)
            repositorioUsuarios.create(pablo)
            repositorioUsuarios.create(juan)

            describe("Dada una busqueda parcial") {
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el nombre") {
                    repositorioUsuarios.search("pab").shouldContain(pablo)
                }
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    repositorioUsuarios.search("cac").shouldContain(juan)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    repositorioUsuarios.search("#").shouldBeEmpty()
                }
            }

            describe("Dada una busqueda exacta"){
                it("El usuario debe ser parte de la lista de busqueda si coincide el username"){
                    repositorioUsuarios.search("sol_lop").shouldContain(sol)
                }
            }
        }

        describe("Dada una repositorio de Jugadores"){
            val repositorioJugadores = Repositorio<Jugador>()
            repositorioJugadores.create(emilianoMartinez)
            repositorioJugadores.create(lionelMessi)

            describe("Dada una busqueda parcial") {
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el nombre") {
                    repositorioJugadores.search("emi").shouldContain(emilianoMartinez)
                }
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    repositorioJugadores.search("mes").shouldContain(lionelMessi)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    repositorioJugadores.search("#").shouldBeEmpty()
                }
            }
        }

        describe("Dada una repositorio de Selecciones"){
            val repositorioSelecciones = Repositorio<Seleccion>()
            repositorioSelecciones.create(seleccionArgentina)
            repositorioSelecciones.create(seleccionBrasil)

            describe("Dada una busqueda exacta") {
                it("La Seleccion debe ser parte de la lista de busqueda si coincide el nombre de pais exacto") {
                    repositorioSelecciones.search("argentina").shouldContain(seleccionArgentina)
                    repositorioSelecciones.search("brasil").shouldContain(seleccionBrasil)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    repositorioSelecciones.search("#").shouldBeEmpty()
                }
            }
        }

        describe("Dada una repositorio de Puntos de Venta"){
            val negocioRegular = Kioscos(
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

            val negocioPedidoBajoUmbral = Librerias(
                nombre = "heidi",
                stockSobres = 2,
                direccion = Direccion(
                    provincia = "Buenos Aires",
                    localidad = "General Pacheco",
                    calle = "sarmiento",
                    altura = 31,
                    ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                )
            )
            val repositorioPuntosDeVenta = Repositorio<PuntoDeVenta>()
            repositorioPuntosDeVenta.create(negocioRegular)
            repositorioPuntosDeVenta.create(negocioPedidoBajoUmbral)

            describe("Dada una busqueda exacta") {
                it("El punto de venta debe ser parte de la lista de busqueda si coincide el nombre  exacto") {
                    repositorioPuntosDeVenta.search("almacencito").shouldContain(negocioRegular)
                    repositorioPuntosDeVenta.search("heidi").shouldContain(negocioPedidoBajoUmbral)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    repositorioPuntosDeVenta.search("#").shouldBeEmpty()
                }
            }
        }
    }
})