package ar.edu.unsam.algo3.domain

abstract class Posicion {
    open val valoracionBase: Double = 0.0
    abstract fun valor(jugador: Jugador): Double
    open fun plus(jugador: Jugador): Double = 0.0
}

object Arquero:Posicion() {
    override val valoracionBase = 100.0

    override fun valor(jugador: Jugador): Double = valoracionBase * plus(jugador)
    override fun plus(jugador: Jugador): Double = if(jugador.esAlto()) jugador.altura else 1.0
}

object Defensor:Posicion() {
    private const val PUNTOS_ANTIGUEDAD = 10.0
    override val valoracionBase = 50.0

    override fun valor(jugador: Jugador): Double = valoracionBase + plus(jugador)
    override fun plus(jugador: Jugador): Double = if(jugador.esLider) PUNTOS_ANTIGUEDAD * jugador.aniosJugados() else 0.0
}

//Los mediocampistas valen 150 puntos pero si son ligeros, es decir que pesan entre 65  y 70 kg (ambos inclusivos) se suma su peso como puntos extras.
object Mediocampista:Posicion() {
    override val valoracionBase = 150.0

    override fun valor(jugador: Jugador): Double = valoracionBase + plus(jugador)
    override fun plus(jugador: Jugador): Double = if(jugador.esLigero()) jugador.peso else 0.0
}

//Los delanteros valen 200 puntos pero si son de una selección campeona del mundo se suman como puntos extras su Nro de camiseta * 10
object Delantero:Posicion() {
    private const val PUNTOS_CAMPEON = 10.0
    override val valoracionBase = 200.0

    override fun valor(jugador: Jugador): Double = valoracionBase + plus(jugador)
    override fun plus(jugador: Jugador): Double = if (jugador.esDeSeleccionCampeonaDelMundo()) PUNTOS_CAMPEON * jugador.nroCamiseta else 0.0
}

class Polivalente(posiciones: List<Posicion>):Posicion(){
    private val posicionesEnLasQueJuega: MutableSet<Posicion> = mutableSetOf()

    init {
        posiciones.forEach { agregarPosicion(it) }
    }
    companion object {
        private val posicionesBasicas = listOf(Delantero, Mediocampista, Defensor, Arquero)
    }
    fun agregarPosicion(posicion: Posicion){
        validarPosicionesEnLasQueJuega(posicion)
        posicionesEnLasQueJuega.add(posicion)
    }
    fun removerPosicion(posicion: Posicion){
        posicionesEnLasQueJuega.remove(posicion)
    }
    fun validarPosicionesEnLasQueJuega(posicion: Posicion){
        if (!posicionesBasicas.contains(element = posicion)) {
            throw IllegalArgumentException("No se puede agregar esta posicion")
        }
    }

    private fun promedioValoracionBase(): Double = posicionesEnLasQueJuega.sumOf { it.valoracionBase } / posicionesEnLasQueJuega.size

    override fun plus(jugador: Jugador):Double = promedioValoracionBase() + (posicionesEnLasQueJuega.sumOf { it.valor(jugador) } / posicionesEnLasQueJuega.size ) - jugador.edad()
    override fun valor(jugador: Jugador): Double = if(jugador.esLeyenda() || jugador.promesaDelFutbol()) plus(jugador) else promedioValoracionBase()
}


