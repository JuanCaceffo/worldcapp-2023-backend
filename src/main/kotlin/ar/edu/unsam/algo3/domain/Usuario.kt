package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.repository.RepositorioProps
import java.time.LocalDate

const val MENSAJE_ERROR_INGRESAR_NOMBRE_USUARIO = "Debe ingresar un nombre de usuario"
const val MENSAJE_ERROR_INGRESAR_EMAIL = "Debe ingresar un email"
const val MENSAJE_ERROR_DESACTIVAR_ACCION = "No puede desactivar una accion que nunca fue activada"
const val MENSAJE_ERROR_FIGURITA_INACCESIBLE = "El usuario no puede otrogar la figurita solicitada"
const val MENSAJE_ERROR_USUARIO_LEJANO = "El usuario al que le intenta solicitar la figurita esta demasiado lejos"

data class Usuario(
    val nombre: String,
    val apellido: String,
    val nombreUsuario: String,
    val contrasenia: String = "",
    var fechaNacimiento: LocalDate,
    val email: String,
    val direccion: Direccion
) : RepositorioProps() {
    //Lista de acciones que el usuario puede activar o desactivar segun sus necesidades de negocio
    val acciones = mutableSetOf<AccionesUsuarios>()
    var condicionParaDar: CondicionesParaDar = Desprendido()
    val seleccionesFavoritas = mutableSetOf<Seleccion>()
    val jugadoresFavoritos = mutableSetOf<Jugador>()
    val figuritasFaltantes = mutableSetOf<Figurita>()
    val figuritas = mutableListOf<Figurita>()
    var distanciaMaximaCercania: Int = 5

    init {
        validadorStrings.errorStringVacio(nombre, errorMessage = MENSAJE_ERROR_INGRESAR_NOMBRE)
        validadorStrings.errorStringVacio(apellido, errorMessage = MENSAJE_ERROR_INGRESAR_APELLIDO)
        validadorStrings.errorStringVacio(nombreUsuario, errorMessage = MENSAJE_ERROR_INGRESAR_NOMBRE_USUARIO)
        validadorStrings.errorStringVacio(email, errorMessage = MENSAJE_ERROR_INGRESAR_EMAIL)
    }

    fun addJugadorFavorito(jugador: Jugador) {
        jugadoresFavoritos.add(jugador)
    }

    fun addSeleccionFavoritas(seleccion: Seleccion) {
        seleccionesFavoritas.add(seleccion)
    }

    fun nuevaDistanciaMaximaCercania(nuevaDistancia: Int) {
        distanciaMaximaCercania = nuevaDistancia
    }

    fun edad(): Int = calculadoraEdad.calcularEdad(fechaNacimiento)
    fun puedoDar(figurita: Figurita): Boolean = estaRepetida(figurita) && condicionParaDar.puedeDar(figurita)

    //Retorna una lista inmutable de figus repetidas que el usuario puede regalar
    fun listaFiguritasARegalar(): List<Figurita> = figuritasRepetidas().filter { puedoDar(it) }
    fun darFigurita(figurita: Figurita, solicitante: Usuario) {
        validarEntregaDeFigu(figurita)
        removeFiguritaRepetida(figurita)
        solicitante.recibirFigurita(figurita)
    }

    // Proceso habitual de solicitud de una figurita a otro usuario
    fun pedirFigurita(figurita: Figurita, usuario: Usuario) {
        validarDistanciaPedidoDeFigu(usuario)
        usuario.darFigurita(figurita, this)
        //Ejecuta la acción ConvertirUsuarioEnDesprendido
        acciones.forEach { accion -> accion.ejecutarAccion(this, figurita) }
    }

    //TODO: Preguntar si la implementación es correcta, hay alguna forma más eficiente?
    fun activarAccion(accion: AccionesUsuarios) {
        //TODO: Conviene que sea un error para el usuario?
        if (acciones.none { it.CODIGO_ACCION == accion.CODIGO_ACCION }) {
            acciones.add(accion)
        }
    }

    //TODO: Preguntar si la implementación es correcta, hay alguna forma más eficiente?
    fun desactivarAccion(accion: AccionesUsuarios) {
        this.validarDesactivarAccion(accion)
        //Si las instancias tienen el mismo codigo de acción, la remueve de la lista
        acciones.removeIf { it.CODIGO_ACCION == accion.CODIGO_ACCION }
    }

    fun modificarComportamientoIntercambio(comportamiento: CondicionesParaDar) {
        condicionParaDar = comportamiento
    }

    fun topCincoFiguritasRepetidas() = figuritasRepetidas().sortedBy { it.valoracion() }.takeLast(5).toSet()
    fun estaRepetida(figurita: Figurita) = figuritasRepetidas().contains(figurita)
    fun figuritasRepetidas() = figuritas.distinct().filter { x -> figuritas.count { it == x } > 1 }.toSet()
    override fun validSearchCondition(value: String) = Comparar.parcial(value, listOf(nombre, apellido)) ||
            Comparar.total(value, listOf(nombreUsuario))

    fun addFiguritaFaltante(figurita: Figurita) {
        validarFaltanteExistente(figurita)
        validarFiguritaExistente(figurita)
        figuritasFaltantes.add(figurita)
    }

    fun recibirFigurita(figurita: Figurita) {
        removeFiguritaFaltante(figurita)
        figuritas.add(figurita)
    }

    fun estaCerca(otroUsuario: Usuario): Boolean {
        return direccion.distanciaConPoint(point = otroUsuario.direccion.ubiGeografica) <= distanciaMaximaCercania
    }

    private fun removeFiguritaFaltante(figurita: Figurita) {
        if (buscadorFaltanteExistente(figurita)) {
            figuritasFaltantes.remove(figurita)
        }
    }

    private fun removeFiguritaRepetida(figurita: Figurita) {
        figuritas.remove(figurita)
    }

    private fun buscadorFaltanteExistente(figurita: Figurita): Boolean =
        figuritasFaltantes.map { it.numero }.contains(figurita.numero)

    //---------------------- VALIDACIONES -------------------------//
    private fun validarFiguritaExistente(figurita: Figurita) {
        if (figuritas.contains(figurita)) {
            throw IllegalArgumentException(MENSAJE_ERROR_FIGURITA_EXISTENTE)
        }
    }

    private fun validarFaltanteExistente(figurita: Figurita) {
        if (figuritasFaltantes.contains(figurita)) {
            throw IllegalArgumentException(MENSAJE_ERROR_FIGURITA_EXISTENTE)
        }
    }

    private fun validarDesactivarAccion(accion: AccionesUsuarios) {
        if (acciones.none { it.CODIGO_ACCION == accion.CODIGO_ACCION }) {
            throw BussinesExpetion(MENSAJE_ERROR_DESACTIVAR_ACCION)
        }
    }

    private fun validarEntregaDeFigu(figurita: Figurita) {
        if (!puedoDar(figurita)) throw BussinesExpetion(MENSAJE_ERROR_FIGURITA_INACCESIBLE)
    }

    private fun validarDistanciaPedidoDeFigu(usuario: Usuario) {
        if (!this.estaCerca(usuario)) throw BussinesExpetion(MENSAJE_ERROR_USUARIO_LEJANO)
    }
}