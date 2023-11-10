package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.RepositorioProps

class Figurita (val numero:Int, var onFire:Boolean, val cantidadImpresa: NivelImpresion, val jugador: Jugador): RepositorioProps(){
    init {
        validarNumero()
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

    //------------------------- VALIDACIONES -------------------------- //
    fun validarNumero(){
        if (!HelperNumerosEnteros.esPositivo(numero)){
            throw IllegalArgumentException(MENSAJE_ERROR_NUM_NEGATIVO)
        }
    }

    override fun validSearchCondition(value: String) =  Comparar.parcial(value, listOf(jugador.nombre, jugador.apellido)) ||
                                                        Comparar.total(value, listOf(numero.toString())) ||
                                                        Comparar.total(value, listOf(jugador.seleccionPerteneciente.pais))
}