package ar.edu.unsam.algo3.domain

abstract class CondicionesParaDar(val user: Usuario) {
    abstract fun puedeDar(figurita:Figurita):Boolean
    fun criterioParaCambio() = this::class.simpleName.toString()
}

class Desprendido(user: Usuario) : CondicionesParaDar(user) {
    override fun puedeDar(figurita:Figurita): Boolean = true
}

class Par(user: Usuario) : CondicionesParaDar(user) {
    override fun puedeDar(figurita:Figurita): Boolean = !HelperNumerosEnteros.esPar(figurita.numero) && !HelperNumerosEnteros.esPar(figurita.jugador.nroCamiseta) && !HelperNumerosEnteros.esPar(figurita.jugador.seleccionPerteneciente.copasDelMundo)
}
class Nacionalista(user: Usuario) : CondicionesParaDar(user) {
    override fun puedeDar(figurita: Figurita): Boolean = !user.seleccionesFavoritas.contains(figurita.jugador.seleccionPerteneciente)
}

class Conservador(user: Usuario) : CondicionesParaDar(user) {
    override fun puedeDar(figurita: Figurita): Boolean = (figurita.cantidadImpresa == impresionAlta) && (user.figuritasFaltantes.isEmpty())
}

class Apostador(user: Usuario) : CondicionesParaDar(user)  {
    override fun puedeDar(figurita: Figurita): Boolean =  !figurita.onFire && !figurita.jugador.promesaDelFutbol()
}
class Interesado(user: Usuario) : CondicionesParaDar(user) {
    override fun puedeDar(figurita: Figurita): Boolean = !user.topCincoFiguritasRepetidas().contains(figurita)
}

class Cambiante(user: Usuario) : CondicionesParaDar(user){
    private val desprendido = Desprendido(user)
    private val conservador = Conservador(user)
    override fun puedeDar(figurita: Figurita): Boolean = (if(user.edad() < 25) desprendido else conservador).puedeDar(figurita)
}

class Fanatico(user: Usuario): CondicionesParaDar(user) {
    override fun puedeDar(figurita: Figurita): Boolean = (!user.jugadoresFavoritos.contains(figurita.jugador)) && (!figurita.jugador.esLeyenda())
}