package ar.edu.unsam.algo2.worldcapp2023

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkStatic
import org.uqbar.geodds.Point
import java.time.LocalDate


//---------------------- TESTS ----------------------//
class PuntosDeVentasSpec: DescribeSpec({


//---------------------- PUNTOS DE VENTA ----------------------//
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
    val puntoDeVentaCercano = Kioscos(
        nombre = "almacenManolo",
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

    val puntoDeVentalejano = Kioscos(
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

    val libreriaPedidosSobreUmbral = Librerias(
        nombre = "heidi",
        stockSobres = 6,
        direccion = Direccion(
            provincia = "Buenos Aires",
            localidad = "General Pacheco",
            calle = "sarmiento",
            altura = 31,
            ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
        )
    )

    val supermercadoSinDescuento = Supermercados(
        nombre = "chinoCentral",
        stockSobres = 2,
        direccion = Direccion(
            provincia = "Buenos Aires",
            localidad = "General Pacheco",
            calle = "sarmiento",
            altura = 31,
            ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
        ),
        descuento = SinDescuento()
    )

    isolationMode = IsolationMode.InstancePerTest
    mockkStatic(LocalDate::class)
    describe(name = "test metodos generales de puntos de venta"){

        it(name= "un negocio sin stock no tiene disponiblidad"){
            negocioRegular.disponibilidad().shouldBeFalse()
        }

        it(name = "un negocio puede agregar un pedido si la fecha de entrega del mismo es la misma que la actual o superior"){
            every { LocalDate.now() } returns LocalDate.of(2023,5,11)
            negocioRegular.addPedidosPendientes(Pedido(100,LocalDate.of(2023,5,11)))
            println(negocioRegular.pedidosPendientes)
            negocioRegular.pedidosPendientes.size shouldBe 1
        }

        it(name = "debe dar error si un negocio hace un pedido y la fecha de entrega del mismo es anterior a la actual"){
            shouldThrow<IllegalArgumentException>{
                negocioRegular.addPedidosPendientes(Pedido(100,LocalDate.of(2023,5,10)))
            }.message shouldBe MENSAJE_ERROR_PV_FECHA_PEDIDO_ANTERIOR_FECHA_ACUTAL
        }
    }

    describe(name = "Test importes a cobrar de Kioskos"){
        val usuarioCercano = sol
        val usuarioLejano = sol

        it(name = "el importe a cobrar por un kiosko atenidod por los dueños con un enivo a un usuario de la zona y con un pedido chico de sobres debe ser bajo"){
           puntoDeVentaCercano.importeACobrar(usuario = usuarioCercano,cantSobres = 2).shouldBe(1474.0 plusOrMinus(0.01))
        }

        it(name = "el importe a cobrar por un kiosko con empelados y un enivo a un usuario que excede el limite de la zona x2 y con un pedido chico de sobres debe ser medio"){
            puntoDeVentalejano.importeACobrar(usuario = usuarioLejano,cantSobres = 2).shouldBe(2050.0 plusOrMinus(0.01) )
        }
    }

    describe(name = "Test importes a cobrar de Librerias"){
        val fecha_fija = LocalDate.of(2023,5,9)
        val usuarioCercano = alejo

        every { LocalDate.now() } returns fecha_fija
        it (name = "importe a cobrar de una libreria con pedidos a fabrica menor al umbral de dias, con una cantidad de sobres chica y enviado a un usuario de la zona debe ser bajo"){
            negocioPedidoBajoUmbral.apply {
                addPedidosPendientes(Pedido(cantSobres = 10, fechaEntrega = fecha_fija.plusDays(8)))
                addPedidosPendientes(Pedido(cantSobres = 10, fechaEntrega = fecha_fija.plusDays(4)))
            }
            negocioPedidoBajoUmbral.importeACobrar(usuarioCercano,2).shouldBe(1407.0 plusOrMinus(0.01))
        }
        it (name = "importe a cobrar de una libreria con pedidos a fabrica mayor al umbral de dias, con una cantidad de sobres media y enviado a un usuario de la zona debe ser medio"){

            libreriaPedidosSobreUmbral.apply {
                addPedidosPendientes(Pedido(cantSobres = 10, fechaEntrega = fecha_fija.plusDays(10)))
                addPedidosPendientes(Pedido(cantSobres = 10, fechaEntrega = fecha_fija.plusDays(3)))
            }
            libreriaPedidosSobreUmbral.importeACobrar(usuarioCercano,5).shouldBe(2035.0 plusOrMinus(0.01))
        }
    }

    describe(name = "Test Supermercados"){
        describe(name = "Test importes a cobrar de Supermercados") {
            val usuarioCercano = alejo

            it(name = "importe a cobrar de un supermercado que no implementa descuentos, con una cantidad de sobres chica y enviado a un usuario de la zona debe ser bajo") {
                supermercadoSinDescuento.importeACobrar(usuarioCercano, 2).shouldBe(1340.0 plusOrMinus(0.01) )
            }

            describe(name = "Test importe a cobrar supermercados con descuentos por fecha") {
                it(name = "importe a cobrar de un supermercado con desucento los jueves, con una cantidad de sobres chica y enviado a un usuario de la zona debe ser bajo si el dia de la semana no es el del descuento y mas bajo si lo es") {
                    val supermercadoDescJueves = Supermercados(
                        nombre = "chinoSur",
                        stockSobres = 2,
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "General Pacheco",
                            calle = "sarmiento",
                            altura = 31,
                            ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                        ),
                        descuento = DescDiaSemanaX()
                    )
                    every { LocalDate.now() } returns LocalDate.of(2023,5,11)
                    supermercadoDescJueves.importeACobrar(usuarioCercano, 2).shouldBe(1206.0 plusOrMinus(0.01))
                    every { LocalDate.now() } returns LocalDate.of(2023,5,9)
                    supermercadoDescJueves.importeACobrar(usuarioCercano, 2).shouldBe(1340.0 plusOrMinus(0.01))
                }
                it(name = "importe a cobrar de un supermercado con desucento en un rango de dias del mes, con una cantidad de sobres chica y enviado a un usuario de la zona debe ser bajo  y si la compra se hace dentro del rango de dias mas bajo aún") {
                    val supermercadoDescJueves = Supermercados(
                        nombre = "carrefour",
                        stockSobres = 2,
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "General Pacheco",
                            calle = "sarmiento",
                            altura = 31,
                            ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                        ),
                        descuento = DescXDiasMes()
                    )
                    every { LocalDate.now() } returns LocalDate.of(2023,5,11)
                    supermercadoDescJueves.importeACobrar(usuarioCercano, 2).shouldBe(1340.0 plusOrMinus(0.01))
                    every { LocalDate.now() } returns LocalDate.of(2023,5,9)
                    supermercadoDescJueves.importeACobrar(usuarioCercano, 2).shouldBe(1273.0 plusOrMinus(0.01))
                }
            }
            it(name = "importe a cobrar de un supermercado con descuento por cantidad de sobres comprados, con una cantidad de sobres alata accediendo al desucneto y enviado a un usuario de la zona debe ser medio") {
                val supermercadoDescJueves = Supermercados(
                    nombre = "carrefour",
                    stockSobres = 2,
                    direccion = Direccion(
                        provincia = "Buenos Aires",
                        localidad = "General Pacheco",
                        calle = "sarmiento",
                        altura = 31,
                        ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                    ),
                    descuento = DescuentoCantPaquetes()
                )
                supermercadoDescJueves.importeACobrar(usuarioCercano, 201).shouldBe(19343.5 plusOrMinus(0.01))
            }
            it(name = "importe a cobrar de un supermercado con descuento por cantidad de sobres comprados, con una cantida de sobres alta pero NO suficiente para acceder al desucento y enviado a un usuario de la zona debe ser alto") {
                val supermercadoDescJueves = Supermercados(
                    nombre = "Dia",
                    stockSobres = 2,
                    direccion = Direccion(
                        provincia = "Buenos Aires",
                        localidad = "General Pacheco",
                        calle = "sarmiento",
                        altura = 31,
                        ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                    ),
                    descuento = DescuentoCantPaquetes()
                )
                supermercadoDescJueves.importeACobrar(usuarioCercano, 200).shouldBe(35000.0 plusOrMinus(0.01))
            }
            it(name = "importe a cobrar de un supermercado combinando (descuento x dia, desucuneto x rango dias mes y descuento sobres, con una cantida de sobres alta y suficiente para acceder al desucneto por cant sobres, enviado a un usuario de la zona, el dia de descuento y dentro del rango del mes con desucneto debe ser medio") {
                val supermercadoDescJueves = Supermercados(
                    nombre = "Dia",
                    stockSobres = 2,
                    direccion = Direccion(
                        provincia = "Buenos Aires",
                        localidad = "General Pacheco",
                        calle = "sarmiento",
                        altura = 31,
                        ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                    ),
                    descuento = DescuentosCombi(mutableSetOf(DescXDiasMes(),DescDiaSemanaX(),DescuentoCantPaquetes()) )
                )
                every { LocalDate.now() } returns LocalDate.of(2023,5,4)
                supermercadoDescJueves.importeACobrar(usuarioCercano, 201).shouldBe(17585.0 plusOrMinus(0.01))
            }
            it(name = "importe a cobrar de un supermercado combinando (descuento x dia, desucuneto x rango dias mes, con una cantida de sobres baja, enviado a un usuario de la zona, el dia de descuento y dentro del rango del mes con desucneto debe ser bajo") {
                val supermercadoDescJueves = Supermercados(
                    nombre = "Dia",
                    stockSobres = 2,
                    direccion = Direccion(
                        provincia = "Buenos Aires",
                        localidad = "General Pacheco",
                        calle = "sarmiento",
                        altura = 31,
                        ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                    ),
                    descuento = DescuentosCombi(mutableSetOf(DescXDiasMes(),DescDiaSemanaX()))
                )
                every { LocalDate.now() } returns LocalDate.of(2023,5,4)
                supermercadoDescJueves.importeACobrar(usuarioCercano, 2).shouldBe(1139.0 plusOrMinus(0.01))
            }
            it(name = "un supermercado con desucneto combinados no puede tener en la lista de descuentos otro desucneto combinado"){
                shouldThrow<IllegalArgumentException> {
                    Supermercados(
                        nombre = "Dia",
                        stockSobres = 2,
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "General Pacheco",
                            calle = "sarmiento",
                            altura = 31,
                            ubiGeografica = Point(-34.46074170561798, -58.63461192003287)
                        ),
                        descuento = DescuentosCombi(mutableSetOf(DescXDiasMes(),DescDiaSemanaX(),DescuentosCombi(mutableSetOf(DescXDiasMes(),SinDescuento()))))
                    )
                }.message shouldBe  MENSAJE_ERROR_DS_COMBI_ANIDADA
            }
        }
    }
})

