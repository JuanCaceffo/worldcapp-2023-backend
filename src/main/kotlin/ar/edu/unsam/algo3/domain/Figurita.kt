package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.error.IllegalArgumentException
import ar.edu.unsam.algo3.repository.RepositorioProps

class Figurita (
    var numero:Int,
    var onFire:Boolean,
    var cantidadImpresa: NivelImpresion,
    var jugador: Jugador,
    var urlImage: String? = null
): RepositorioProps(){
    init {

        validarNumero(numero)
    }

    val valorInicial = 100.0
    private fun multiplicadorOnFire():Double = if (onFire) 1.2 else 1.0
    private fun multiplicadorCartaPar():Double = if (HelperNumerosEnteros.esPar((numero))) 1.1 else 1.0
    fun valoracionBase() = valorInicial * multiplicadorOnFire() * multiplicadorCartaPar() * cantidadImpresa.afectaValorEn
    fun valoracion() = valoracionBase() + jugador.valoracionJugador()
    fun estaOnfire() = onFire
    fun cantidadCopasDelMundoDelJugador() = jugador.seleccionPerteneciente.copasDelMundo
    fun OnFire(status : Boolean) {
        onFire = status
    }

    fun cambiarNumero(nro: Int) {
        validarNumero(nro)
        numero = nro
    }

    //------------------------- VALIDACIONES -------------------------- //
    fun validarNumero(nro: Int){
        if (!HelperNumerosEnteros.esPositivo(nro)){
            throw IllegalArgumentException(MENSAJE_ERROR_NUM_NEGATIVO)
        }
    }

    override fun validSearchCondition(value: String) =  Comparar.parcial(value, listOf(jugador.nombre, jugador.apellido)) ||
                                                        Comparar.total(value, listOf(numero.toString())) ||
                                                        Comparar.total(value, listOf(jugador.seleccionPerteneciente.pais))
}