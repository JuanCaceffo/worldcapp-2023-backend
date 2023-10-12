package ar.edu.unsam.algo3.repository

abstract class CondicionesParaDar {
    abstract fun puedeDar(figurita:Figurita):Boolean
}

class Desprendido : CondicionesParaDar() {
    override fun puedeDar(figurita:Figurita): Boolean = true
}

class Par : CondicionesParaDar() {
    override fun puedeDar(figurita:Figurita): Boolean = !HelperNumerosEnteros.esPar(figurita.numero) && !HelperNumerosEnteros.esPar(figurita.jugador.nroCamiseta) && !HelperNumerosEnteros.esPar(figurita.jugador.seleccionPerteneciente.copasDelMundo)
}
class Nacionalista(private val user: Usuario) : CondicionesParaDar() {
    override fun puedeDar(figurita: Figurita): Boolean = !user.seleccionesFavoritas.contains(figurita.jugador.seleccionPerteneciente)
}

class Conservador(private val user: Usuario) : CondicionesParaDar() {
    override fun puedeDar(figurita: Figurita): Boolean = (figurita.cantidadImpresa == impresionAlta) && (user.figuritasFaltantes.isEmpty())
}

class Apostador : CondicionesParaDar()  {
    override fun puedeDar(figurita: Figurita): Boolean =  !figurita.onFire && !figurita.jugador.promesaDelFutbol()
}
class Interesado(private val user: Usuario) : CondicionesParaDar() {
    override fun puedeDar(figurita: Figurita): Boolean = !user.topCincoFiguritasRepetidas().contains(figurita)
}

class Cambiante(private val user: Usuario) : CondicionesParaDar(){
    private val desprendido = Desprendido()
    private val conservador = Conservador(user)
    override fun puedeDar(figurita: Figurita): Boolean = (if(user.edad() < 25) desprendido else conservador).puedeDar(figurita)
}

class Fanatico(private val user: Usuario) : CondicionesParaDar() {
    override fun puedeDar(figurita: Figurita): Boolean = (!user.jugadoresFavoritos.contains(figurita.jugador)) && (!figurita.jugador.esLeyenda())
}