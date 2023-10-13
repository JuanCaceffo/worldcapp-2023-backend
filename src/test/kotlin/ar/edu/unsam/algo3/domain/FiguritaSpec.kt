package ar.edu.unsam.algo3.domain


import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

//-------------------------FIGURITAS-----------------------//
val figuritaBase = Figurita(numero = 9, onFire = false, cantidadImpresa = impresionBaja, jugador = emilianoMartinez)
val figuritaComun = Figurita(numero = 17, onFire = false, cantidadImpresa = impresionBaja, jugador = alexisMacAllister)
val figuritaOnfireImpresionMedia = Figurita(numero = 27, onFire = true, cantidadImpresa = impresionMedia, jugador = emilianoMartinez)
val figuritaNroCopasPar = Figurita(numero = 13, onFire = false, cantidadImpresa = impresionMedia, jugador = mbappe)
val figuritaChilena = Figurita(numero = 19, onFire = false, cantidadImpresa = impresionBaja, jugador = alexisSanchez)
val figuritaDevaluada = Figurita(numero = 35, onFire = false, cantidadImpresa = impresionAlta, jugador = francoArmani)
val figuritaValorMaximo = Figurita(numero = 10, onFire = true, cantidadImpresa = impresionBaja, jugador = lionelMessi)
val figuritaValorMedio = Figurita(numero = 27, onFire = true, cantidadImpresa = impresionMedia, jugador = nicolasOtamendi)
val figuritaMenorValoracion = Figurita(numero = 50, onFire = true, cantidadImpresa = impresionMedia, jugador = lisandroMartinez)

class FiguritaSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    describe("Test Figuritas") {
        it("no se puede instanciar una figurita negativa"){
            shouldThrow<IllegalArgumentException> {
                Figurita(numero = -2, onFire = false, cantidadImpresa = impresionBaja, jugador = emilianoMartinez)
            }.message shouldBe MENSAJE_ERROR_NUM_NEGATIVO
        }

        it("Una figurita impar, poco impresa y que no esta onfire su valor debe ser igual a el valor base") {
            figuritaBase.valoracionBase().shouldBe(100)
        }

        it("Una figurita impar, altamente impresa y que no esta onfire debe valer menos que su valor base") {
            figuritaDevaluada.valoracionBase().shouldBe(85)
        }

        it("figurita con N° par, onfire y con cantidad de impresiones bajas debe ser la figurita con el valor maximo") {
            figuritaValorMaximo.valoracionBase() shouldBe 132
        }

        it("Figurita NO PAR, que esta ONFIRE y que tiene una cantidad de impresiones MEDIA") {
            figuritaValorMedio.valoracionBase().shouldBe(102)
        }

        it("Valoracion figurita NO PAR, que esta ONFIRE y que tiene una cantidad de impresiones MEDIA") {
            figuritaOnfireImpresionMedia.valoracion().shouldBe(282)
        }
    }
})