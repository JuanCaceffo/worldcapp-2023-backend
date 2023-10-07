package ar.edu.unsam.algo2.worldcapp2023

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

        describe("Dada una coleccion de Figuritas"){
            val coleccionFiguritas = Coleccion<Figurita>()
            coleccionFiguritas.create(figu1Emi)
            coleccionFiguritas.create(figu2Messi)
            coleccionFiguritas.create(figu3Mbappe)

            it("Si se agregan x cant de figuritas a una coleccion vacia, el id de la ultima agregada deberia ser el size de la coleccion menos 1."){
                figu3Mbappe.id.shouldBe(coleccionFiguritas.elementos.size - 1)
            }

            it("Al borrar una figurita esta no debe pertenercer mas a la coleccion"){
                coleccionFiguritas.delete(figu2Messi)
                shouldThrow<IllegalArgumentException> {
                    coleccionFiguritas.getById(figu2Messi.id)
                }.message shouldBe MENSAJE_ERROR_ID_INEXISTENTE
            }

            describe("Dada una busqueda parcial"){
                it("La figurita debe ser parte de la lista de busqueda si coincide parte del texto en el nombre"){
                    coleccionFiguritas.search("emi").shouldContain(figu1Emi)
                }
                it("La figurita debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    coleccionFiguritas.search("mes").shouldContain(figu2Messi)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    coleccionFiguritas.search("#").shouldBeEmpty()
                }
            }

            describe("Dada una busqueda exacta"){
                it("La figurita debe ser parte de la lista de busqueda si coincide el numero"){
                    coleccionFiguritas.search("9").shouldContain(figu1Emi)
                }
                it("La figurita debe ser parte de la lista de busqueda si coincide el nombre del pais"){
                    coleccionFiguritas.search("argentina").shouldContainAll(figu1Emi, figu2Messi)
                }
            }

        }

        describe("Dada una coleccion de Usuarios"){
            val coleccionUsuarios = Coleccion<Usuario>()
            coleccionUsuarios.create(sol)
            coleccionUsuarios.create(pablo)
            coleccionUsuarios.create(juan)

            describe("Dada una busqueda parcial") {
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el nombre") {
                    coleccionUsuarios.search("pab").shouldContain(pablo)
                }
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    coleccionUsuarios.search("cac").shouldContain(juan)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    coleccionUsuarios.search("#").shouldBeEmpty()
                }
            }

            describe("Dada una busqueda exacta"){
                it("El usuario debe ser parte de la lista de busqueda si coincide el username"){
                    coleccionUsuarios.search("sol_lop").shouldContain(sol)
                }
            }
        }

        describe("Dada una coleccion de Jugadores"){
            val coleccionJugadores = Coleccion<Jugador>()
            coleccionJugadores.create(emilianoMartinez)
            coleccionJugadores.create(lionelMessi)

            describe("Dada una busqueda parcial") {
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el nombre") {
                    coleccionJugadores.search("emi").shouldContain(emilianoMartinez)
                }
                it("El usuario debe ser parte de la lista de busqueda si coincide parte del texto en el apellido"){
                    coleccionJugadores.search("mes").shouldContain(lionelMessi)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    coleccionJugadores.search("#").shouldBeEmpty()
                }
            }
        }

        describe("Dada una coleccion de Selecciones"){
            val coleccionSelecciones = Coleccion<Seleccion>()
            coleccionSelecciones.create(seleccionArgentina)
            coleccionSelecciones.create(seleccionBrasil)

            describe("Dada una busqueda exacta") {
                it("La Seleccion debe ser parte de la lista de busqueda si coincide el nombre de pais exacto") {
                    coleccionSelecciones.search("argentina").shouldContain(seleccionArgentina)
                    coleccionSelecciones.search("brasil").shouldContain(seleccionBrasil)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    coleccionSelecciones.search("#").shouldBeEmpty()
                }
            }
        }

        describe("Dada una coleccion de Puntos de Venta"){
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
            val coleccionPuntosDeVenta = Coleccion<PuntoDeVenta>()
            coleccionPuntosDeVenta.create(negocioRegular)
            coleccionPuntosDeVenta.create(negocioPedidoBajoUmbral)

            describe("Dada una busqueda exacta") {
                it("El punto de venta debe ser parte de la lista de busqueda si coincide el nombre  exacto") {
                    coleccionPuntosDeVenta.search("almacencito").shouldContain(negocioRegular)
                    coleccionPuntosDeVenta.search("heidi").shouldContain(negocioPedidoBajoUmbral)
                }
                it("El resultado del search debe ser vacio si no hay coincidencias") {
                    coleccionPuntosDeVenta.search("#").shouldBeEmpty()
                }
            }
        }
    }
})