package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.error.BussinesExpetion
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import org.uqbar.geodds.Point
import java.time.LocalDate

//-------------------------USUARIOS-----------------------//
val sol = Usuario(
    apellido = "lopez",
    nombre = "sol",
    nombreUsuario = "sol_lop",
    fechaNacimiento = LocalDate.of(2001, 2, 15),
    email = "lopezSol@gmail.com",
    imagenPath = "/src/assets/images/card-img-10.jpg",
    direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "Tigre",
        calle = "av.Cazon",
        altura = 130,
        ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
    )
)
val pablo = Usuario(
    apellido = "pablo",
    nombre = "foglia",
    nombreUsuario = "madescoses",
    fechaNacimiento = LocalDate.of(2000, 2, 1),
    email = "madescoses@gmail.com",
    imagenPath = "/src/assets/images/card-img-10.jpg",
    direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "San Martin",
        calle = "matheu",
        altura = 3568,
        ubiGeografica = Point(-34.57461948921918, -58.5378840940197)
    )
)
val juan = Usuario(
    apellido = "juan",
    nombre = "caceffo",
    nombreUsuario = "juanceto01",
    fechaNacimiento = LocalDate.of(2003, 2, 1),
    email = "juanchi@gmail.com",
    imagenPath = "/src/assets/images/card-img-10.jpg",
    direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "San Martin",
        calle = "Av. Rodríguez Peña",
        altura = 3237,
        ubiGeografica = Point(-34.58424206690573, -58.52112943577023)
    )
)

val robertito = Usuario(
    apellido = "Gomez",
    nombre = "Roberto",
    nombreUsuario = "roberto_gomez",
    fechaNacimiento = LocalDate.of(1990, 12, 22),
    email = "robertoGomez@gmail.com",
    imagenPath = "/src/assets/images/card-img-10.jpg",
    direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "Tigre",
        calle = "av.Cazon",
        altura = 130,
        ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
    )
)

val alejo = Usuario(
    apellido = "alejo",
    nombre = "menini",
    nombreUsuario = "nestorKishner",
    fechaNacimiento = LocalDate.of(2001, 5, 15),
    email = "alete@gmail.com",
    imagenPath = "/src/assets/images/card-img-10.jpg",
    direccion = Direccion(
        provincia = "Buenos Aires",
        localidad = "General Pacheco",
        calle = "chaco",
        altura = 130,
        ubiGeografica = Point(-34.46067347399887, -58.63219996237826)
    )
)
val usuarioConFigus = sol.copy()

class UsuarioSpec: DescribeSpec ({
    isolationMode = IsolationMode.InstancePerTest

    val usuarioConFigus = sol.copy()

    describe("Test Usuarios") {

        usuarioConFigus.apply {
            addFiguritaFaltante(figuritaBase)
            addFiguritaFaltante(figuritaValorMaximo)
            addFiguritaFaltante(figuritaDevaluada)
            addFiguritaFaltante(figuritaValorMedio)
            addFiguritaFaltante(figuritaChilena)
            addFiguritaFaltante(figuritaMenorValoracion)
        }

        describe("Edad respecto a la fecha de nacimiento de un usuario") {
            it("Usuario nacido el 22/12/1990 debe tener (año actual - año nac.), restando 1 si en el año actual no cumplió años") {
                //TODO: Hacer más generica la salida
                robertito.edad() shouldBe 32
            }
        }

        describe("Distancia entre los usuarios"){
            it("un usuario se encuentra cerca de otro si no sobrepasa el limite de lejania") {
                pablo.estaCerca(juan).shouldBeTrue()
            }

            it("un usuario se encuentra alejado de otro si sobrepasa el limite de lejania") {
                robertito.estaCerca(juan).shouldBeFalse()
            }
        }

        describe("test usuario metodos de agregado de figuritas"){
            val figu = Figurita(numero = 9, onFire = false, cantidadImpresa = impresionBaja, jugador = emilianoMartinez)
            it("no se puede reingresar una figurita faltante que ya esta faltantes") {
                robertito.addFiguritaFaltante(figu)
                shouldThrow<BussinesExpetion>{
                    robertito.addFiguritaFaltante(figu)
                }.message shouldBe MENSJAE_ERROR_FALTANTE_YA_AGREGADA
            }

            it("no se puede ingresar una figurita faltante que tengo repetida"){
                robertito.addFiguritaRepetida(figu)
                shouldThrow<BussinesExpetion>{
                    robertito.addFiguritaFaltante(figu)
                }.message shouldBe MENSAJE_ERROR_FIGURITA_ENFALTANTES
            }

            it ( "cuando se agrega una figurita no debe aparecer en figuritas faltantes") {
                robertito.addFiguritaFaltante(figu)
                robertito.recibirFigurita(figu)
                robertito.figuritasFaltantes.contains(figu).shouldBeFalse()
            }
        }

        describe("test validacion de campos de usuarios"){
            it ("no se puede crear un usuario sin nombre"){
                shouldThrow<IllegalArgumentException> {
                    Usuario(
                        apellido = "Bolaños",
                        nombre = "",
                        nombreUsuario = "el_loquito_bolaños",
                        fechaNacimiento = LocalDate.of(1967, 12, 22),
                        email = "bolaños@gmail.com",
                        imagenPath = "/src/assets/images/card-img-10.jpg",
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "Tigre",
                            calle = "av.Cazon",
                            altura = 130,
                            ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
                        )
                    )
                }.message shouldBe MENSAJE_ERROR_INGRESAR_NOMBRE
            }

            it ("no se puede crear un usuario sin apellido"){
                shouldThrow<IllegalArgumentException> {
                    Usuario(
                        apellido = "",
                        nombre = "pedro",
                        nombreUsuario = "pedrito",
                        fechaNacimiento = LocalDate.of(1990, 1, 22),
                        email = "peterelpicante@gmail.com",
                        imagenPath = "/src/assets/images/card-img-10.jpg",
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "Tigre",
                            calle = "av.Cazon",
                            altura = 130,
                            ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
                        )
                    )
                }.message shouldBe MENSAJE_ERROR_INGRESAR_APELLIDO
            }
            it ("no se puede crear un usuario sin nombre de usuario"){
                shouldThrow<IllegalArgumentException> {
                    Usuario(
                        apellido = "Gomez",
                        nombre = "pedro",
                        nombreUsuario = "",
                        fechaNacimiento = LocalDate.of(1990, 12, 22),
                        email = "robertoGomez@gmail.com",
                        imagenPath = "/src/assets/images/card-img-10.jpg",
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "Tigre",
                            calle = "av.Cazon",
                            altura = 130,
                            ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
                        )
                    )
                }.message shouldBe MENSAJE_ERROR_INGRESAR_NOMBRE_USUARIO
            }
            it ("no se puede crear un usuario sin email"){
                shouldThrow<IllegalArgumentException> {
                    Usuario(
                        apellido = "Gomez",
                        nombre = "galati",
                        nombreUsuario = "roberto_gomez",
                        fechaNacimiento = LocalDate.of(1990, 12, 22),
                        email = "",
                        imagenPath = "/src/assets/images/card-img-10.jpg",
                        direccion = Direccion(
                            provincia = "Buenos Aires",
                            localidad = "Tigre",
                            calle = "av.Cazon",
                            altura = 130,
                            ubiGeografica = Point(-34.43359068891678, -58.56781331824527)
                        )
                    )
                }.message shouldBe MENSAJE_ERROR_INGRESAR_EMAIL
            }
        }

        describe("Un usuario puede cambiar de comportamiento al dar repetidas") {
            it("Un usuario desprendido cambia a interesado") {
                val interesado = Interesado(usuarioConFigus)
                usuarioConFigus.modificarComportamientoIntercambio(interesado)
                usuarioConFigus.condicionParaDar.shouldBe(interesado)
                usuarioConFigus.modificarComportamientoIntercambio(Desprendido(usuarioConFigus))
            }
        }

        describe("Comportamiento de un Usuario Desprendido") {
            it("Un usuario desprendido puede dar cualquier tipo de figurita repetida") {
                usuarioConFigus.apply {
                    recibirFigurita(figuritaBase)
                    addFiguritaRepetida(figuritaBase)
                    recibirFigurita(figuritaDevaluada)
                }
                usuarioConFigus.puedoDar(figuritaBase).shouldBeTrue()
                usuarioConFigus.puedoDar(figuritaDevaluada).shouldBeFalse()
            }
        }

        describe("Comportamiento de un Usuario Par") {
            it("Un usuario par no regala ninguna figurita que tenga caracteristicas de numeros pares") {
                val figuritaNroPar = figuritaValorMaximo
                val figuritaNroCamisetaPar = figuritaComun

                usuarioConFigus.modificarComportamientoIntercambio(Par(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaNroPar)
                    recibirFigurita(figuritaNroCamisetaPar)
                    recibirFigurita(figuritaNroCopasPar)
                    addFiguritaRepetida(figuritaNroPar)
                    addFiguritaRepetida(figuritaNroCamisetaPar)
                    addFiguritaRepetida(figuritaNroCopasPar)
                }
                usuarioConFigus.puedoDar(figuritaNroPar).shouldBeFalse()
                usuarioConFigus.puedoDar(figuritaNroCamisetaPar).shouldBeFalse()
                usuarioConFigus.puedoDar(figuritaNroCopasPar).shouldBeFalse()
            }
        }

        describe("Comportamiento de un Usuario Nacionalista") {
            it("Un usuario nacionalista no da las figuritas de sus selecciones favoritas aunque las tenga repetidas") {
                val figuritaArgentina = figuritaBase
                usuarioConFigus.modificarComportamientoIntercambio(Nacionalista(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaArgentina)
                    recibirFigurita(figuritaChilena)
                    addFiguritaRepetida(figuritaArgentina)
                    addFiguritaRepetida(figuritaChilena)
                    addSeleccionFavoritas(seleccionArgentina)
                    addSeleccionFavoritas(seleccionBrasil)
                }
                usuarioConFigus.puedoDar(figuritaArgentina).shouldBeFalse()
                usuarioConFigus.puedoDar(figuritaChilena).shouldBeTrue()
            }
        }

        describe("Comportamiento de un Usuario Conservador") {
            it("Un usuario conservador solo da figuritas cuando completo el album y son de impresion alta") {
                val figuritaImpresionAlta = figuritaDevaluada
                usuarioConFigus.modificarComportamientoIntercambio(Conservador(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaImpresionAlta)
                    addFiguritaRepetida(figuritaImpresionAlta)
                    figuritasFaltantes.removeAll(figuritasFaltantes)//Simulo llenar el ALBUM
                }
                usuarioConFigus.puedoDar(figuritaImpresionAlta).shouldBeTrue()
                usuarioConFigus.puedoDar(figuritaBase).shouldBeFalse()
            }
        }

        describe("Comportamiento de un Usuario Fanatico") {
            it("Un usuario fanatico no da las figus que son leyendas ni de sus jugadores favs") {
                val figuritaMessiLeyenda = figuritaValorMaximo
                val figuritaDibu = figuritaBase
                usuarioConFigus.modificarComportamientoIntercambio(Fanatico(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaDibu)
                    addFiguritaRepetida(figuritaDibu)
                    recibirFigurita(figuritaMessiLeyenda)
                    addFiguritaRepetida(figuritaMessiLeyenda)
                    addJugadorFavorito(lionelMessi)
                }
                usuarioConFigus.puedoDar(figuritaDibu).shouldBeTrue()
                usuarioConFigus.puedoDar(figuritaMessiLeyenda).shouldBeFalse()
            }
        }

        describe("Comportamiento de un Usuario Apostador") {
            it("Un usuario apostdor no figuritas OnFire ni leyendas del Futbol") {
                val figuritaMessiLeyenda = figuritaValorMaximo
                val figuritaOnFire = figuritaValorMedio
                usuarioConFigus.modificarComportamientoIntercambio(Apostador(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaOnFire)
                    addFiguritaRepetida(figuritaOnFire)
                    recibirFigurita(figuritaComun)
                    addFiguritaRepetida(figuritaComun)
                    recibirFigurita(figuritaMessiLeyenda)
                    addFiguritaRepetida(figuritaMessiLeyenda)
                }
                usuarioConFigus.puedoDar(figuritaOnFire).shouldBeFalse()
                usuarioConFigus.puedoDar(figuritaMessiLeyenda).shouldBeFalse()
                usuarioConFigus.puedoDar(figuritaComun).shouldBeTrue()
            }
        }

        describe("Comportamiento de un Usuario Interesado") {
            it("Un usuario Interesado solo da figuritas que no esten en su top 5") {
                usuarioConFigus.modificarComportamientoIntercambio(Interesado(usuarioConFigus))
                usuarioConFigus.apply {
                    recibirFigurita(figuritaBase)
                    addFiguritaRepetida(figuritaBase)
                    recibirFigurita(figuritaComun)
                    addFiguritaRepetida(figuritaComun)
                    recibirFigurita(figuritaValorMedio)
                    addFiguritaRepetida(figuritaValorMedio)
                    recibirFigurita(figuritaValorMaximo)
                    addFiguritaRepetida(figuritaValorMaximo)
                    recibirFigurita(figuritaMenorValoracion)
                    addFiguritaRepetida(figuritaMenorValoracion)
                }
                usuarioConFigus.puedoDar(figuritaMenorValoracion).shouldBeFalse()

                usuarioConFigus.apply {
                    recibirFigurita(figuritaChilena)
                    addFiguritaRepetida(figuritaChilena)
                }
                usuarioConFigus.puedoDar(figuritaMenorValoracion).shouldBeTrue()
                usuarioConFigus.puedoDar(figuritaValorMaximo).shouldBeFalse()
            }
        }

        describe("Comportamiento de un Usuario Cambiante") {
            val cambiante = Cambiante(usuarioConFigus)
            val figuritaImpresionAlta = figuritaDevaluada
            usuarioConFigus.modificarComportamientoIntercambio(cambiante)
            usuarioConFigus.apply {
                recibirFigurita(figuritaBase)
                addFiguritaRepetida(figuritaBase)
                recibirFigurita(figuritaImpresionAlta)
                addFiguritaRepetida(figuritaImpresionAlta)
            }
            //Usuario Cambiante con albun NO LLENO menor a 25 puede dar una figu de impresion alta
            it("prueba de Cambiante menor a 25 anios") {
                usuarioConFigus.puedoDar(figuritaImpresionAlta).shouldBeTrue()
            }
            usuarioConFigus.fechaNacimiento = LocalDate.of(1990, 2, 15)

            //Cuando el usuario cumple mas de 25 cambia su comportamiento a conservador y la figu de impresion Alta no la da xq no completo el Album
            it("prueba de Cambiante mayor a 25 anios") {
                usuarioConFigus.puedoDar(figuritaImpresionAlta).shouldBeFalse()
            }

            //Con el album ahora si lleno, el usuario se puede desprender de la figu de Impresion Alta
            usuarioConFigus.figuritasFaltantes.removeAll(usuarioConFigus.figuritasFaltantes)//Simulo llenar el ALBUM

            it("prueba de Cambiante, nuevamente mayor a 25 anios") {
                usuarioConFigus.puedoDar(figuritaImpresionAlta).shouldBeTrue()
            }
        }
    }
})