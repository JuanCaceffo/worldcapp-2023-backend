package ar.edu.unsam.algo2.worldcapp2023

import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.doubles.plusOrMinus
import java.time.LocalDate

//-------------------------JUGADORES-----------------------//

val emilianoMartinez = Jugador(
    nombre = "Emiliano",
    apellido = "Martinez",
    fechaNacimiento = LocalDate.of(1992, 9, 2),
    nroCamiseta = 23,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Arquero,
    anioDeDebut = 2004,
    altura = 1.80,
    peso = 72.0,
    esLider = true,
    cotizacion = 90000.0
)

val francoArmani = Jugador(
    nombre = "Franco",
    apellido = "Armani",
    fechaNacimiento = LocalDate.of(1986, 10, 16),
    nroCamiseta = 1,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Arquero,
    anioDeDebut = 2008,
    altura = 1.79,
    peso = 80.0,
    esLider = false,
    cotizacion = 2000.0
)

val nicolasOtamendi = Jugador(
    nombre = "Nicolás",
    apellido = "Otamendi",
    fechaNacimiento = LocalDate.of(1988, 2, 12),
    nroCamiseta = 19,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Defensor,
    anioDeDebut = 2015,
    altura = 1.83,
    peso = 81.0,
    esLider = true,
    cotizacion = 440000.0
)

val lisandroMartinez = Jugador(
    nombre = "Lisandro",
    apellido = "Martinez",
    fechaNacimiento = LocalDate.of(1998, 1, 18),
    nroCamiseta = 25,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Defensor,
    anioDeDebut = 2017,
    altura = 1.75,
    peso = 77.0,
    esLider = false,
    cotizacion = 33000.0
)

val enzoFernandez = Jugador(
    nombre = "Enzo",
    apellido = "Fernandez",
    fechaNacimiento = LocalDate.of(2001, 1, 17),
    nroCamiseta = 24,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Mediocampista,
    anioDeDebut = 2020,
    altura = 1.78,
    peso = 70.0,
    esLider = false,
    cotizacion = 770000.0
)

val alexisMacAllister = Jugador(
    nombre = "Alexis",
    apellido = "Mac Allister",
    fechaNacimiento = LocalDate.of(1998, 12, 24),
    nroCamiseta = 20,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Mediocampista,
    anioDeDebut = 2020,
    altura = 1.74,
    peso = 70.1,
    esLider = false,
    cotizacion = 50000.0
)

val lionelMessi = Jugador(
    nombre = "Lionel",
    apellido = "Messi",
    fechaNacimiento = LocalDate.of(1987, 6, 24),
    nroCamiseta = 10,
    seleccionPerteneciente = seleccionArgentina,
    posicion = Delantero,
    anioDeDebut = 2006,
    altura = 1.70,
    peso = 72.0,
    esLider = true,
    cotizacion = 100000.0
)

val alexisSanchez = Jugador(
    nombre = "Alexis",
    apellido = "Sanchez",
    fechaNacimiento = LocalDate.of(1987, 1, 2),
    nroCamiseta = 11,
    seleccionPerteneciente = seleccionChile,
    posicion = Delantero,
    anioDeDebut = 2008,
    altura = 1.79,
    peso = 76.0,
    esLider = true,
    cotizacion = 40000.0
)

val mbappe = Jugador(
    nombre = "Kylian",
    apellido = "Mbappe",
    fechaNacimiento = LocalDate.of(1998, 12, 20),
    nroCamiseta = 7,
    seleccionPerteneciente = seleccionFrancia,
    posicion = Delantero,
    anioDeDebut = 2008,
    altura = 1.79,
    peso = 76.0,
    esLider = true,
    cotizacion = 40000.0
)




class JugadorSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    describe (name= "Test validaciones Jugadores"){
        it("No se permite inicializar la variable de nro camsiseta menor a 1 o mayor a 99"){
            shouldThrow<IllegalArgumentException> {
                Jugador(
                    nombre = "Alberto",
                    apellido = "Fernandez",
                    fechaNacimiento = LocalDate.of(1998, 5, 5),
                    nroCamiseta = 0,
                    seleccionPerteneciente = seleccionBrasil,
                    posicion = Defensor,
                    anioDeDebut = 2018,
                    altura = 1.79,
                    peso = 76.0,
                    esLider = false,
                    cotizacion = 40000.0
                )
            }.message shouldBe MENSAJE_ERROR_NUM_CAMISETA
        }
        it("No se permite inicializar la variable con un numero negativo"){
            shouldThrow<IllegalArgumentException> {
                Jugador(
                    nombre = "Arturo",
                    apellido = "Vidal",
                    fechaNacimiento = LocalDate.of(1999, 3, 12),
                    nroCamiseta = 2,
                    seleccionPerteneciente = seleccionChile,
                    posicion = Mediocampista,
                    anioDeDebut = -2000,
                    altura = 1.91,
                    peso = 80.0,
                    esLider = true,
                    cotizacion = 100000.0
                )
            }.message shouldBe MENSAJE_ERROR_NUM_NEGATIVO

        }
    }

    describe(name= "Test Jugadores") {
        it("Jugador alto debe tener una valoración igual a la valoracion por puesto Arquero * su altura") {
            emilianoMartinez.valoracionJugador().shouldBe(180.0)
        }

        it("Jugador bajo debe tener una valoración igual a la valoracion por puesto Arquero omitiendo su altura") {
            francoArmani.valoracionJugador().shouldBe(100.0)
        }

        it("Jugador lider debe tener una valoración igual a la valoracion por puesto Defensor + (puntos por antiguedad * años jugados en la selección)") {
            nicolasOtamendi.valoracionJugador().shouldBe(130.0)
        }

        it("Jugador no lider debe tener una valoración igual a la valoracion por puesto Defensor omitiendo los años jugados en la selección") {
            lisandroMartinez.valoracionJugador().shouldBe(50.0)
        }

        it("Jugador ligero debe tener una valoración igual a la valoracion por puesto Mediocampista + su peso") {
            enzoFernandez.valoracionJugador().shouldBe(220.0)
        }

        it("Jugador no ligero debe tener una valoración igual a la valoracion por puesto Mediocampista omitiendo su peso") {
            alexisMacAllister.valoracionJugador().shouldBe(150)
        }

        it("Jugador campeón debe tener una valoración igual a la valoracion por puesto Delantero + (Puntos por Campeon del Mundo * N° de Camiseta)") {
            lionelMessi.valoracionJugador().shouldBe(300.0)
        }

        it("Jugador no campeón debe tener una valoración igual a la valoracion por puesto Delantero omitiendo el plus por Campeon del Mundo)") {
            alexisSanchez.valoracionJugador().shouldBe(200)
        }
    }
    describe("Test Jugador Polivalente"){
        val enzoPerez = Jugador(
            nombre = "Enzo",
            apellido = "Perez",
            fechaNacimiento = LocalDate.of(1990, 2, 20),
            nroCamiseta = 5,
            seleccionPerteneciente = seleccionArgentina,
            posicion = Polivalente(listOf(Mediocampista, Delantero)),
            anioDeDebut = 2015,
            altura = 1.79,
            peso = 78.0,
            esLider = false,
            cotizacion = 10000.0
        )
        val neymarDaSilva = Jugador(
            nombre = "Neymar",
            apellido = "Da Silva",
            fechaNacimiento = LocalDate.of(1993, 2, 5),
            nroCamiseta = 10,
            seleccionPerteneciente = seleccionBrasil,
            posicion = Polivalente(listOf(Mediocampista, Delantero)),
            anioDeDebut = 2008,
            altura = 1.79,
            peso = 70.0,
            esLider = true,
            cotizacion = 40000.0
        )
        val claudioEcheverri = Jugador(
            nombre = "Claudio",
            apellido = "Echeverri",
            fechaNacimiento = LocalDate.of(2006,1,2),
            nroCamiseta = 22,
            seleccionPerteneciente = seleccionArgentina,
            posicion = Polivalente(listOf(Defensor, Mediocampista)),
            anioDeDebut = 2023,
            altura = 1.71,
            peso = 68.0,
            esLider = false,
            cotizacion = 3400000.0
        )
        it("Jugador Polivalente Mediocampista y Delantero que no es ni promesa ni leyenda, su valoracion debera ser el promedio de los valores inciales por poscion "){

            (enzoPerez.posicion as Polivalente).agregarPosicion(Defensor)
            (enzoPerez.posicion as Polivalente).removerPosicion(Defensor)

            enzoPerez.valoracionJugador().shouldBe(175)
        }
        it("Jugador NeymarDaSilva es Leyenda"){
            neymarDaSilva.esLeyenda().shouldBeTrue()
        }
        it("Jugador Polivalente Mediocampista y Delantero leyenda, su valoracion debera ser la suma del promedio de los valores iniciales por posicion, sumado al promedio entre la valoraciones generales por posicion menos su edad"){
            neymarDaSilva.valoracionJugador().shouldBe(405)
        }
        it("Jugador Claudio Echeverri es promesa"){
            claudioEcheverri.promesaDelFutbol().shouldBeTrue()
        }
        it("Jugador Polivalente Defensor y Mediocampista, promesa , su valoracion se calcula de la misma manera"){
            //claudioEcheverri.valoracionJugador().shouldBe(345.666 plusOrMinus(0.001)) //TODO Validar
            claudioEcheverri.valoracionJugador().shouldBe(217.0 plusOrMinus(0.001))
        }
    }
})


