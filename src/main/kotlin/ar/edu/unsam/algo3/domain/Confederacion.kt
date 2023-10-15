package ar.edu.unsam.algo3.domain
//TODO:ver si se puede pasarle la lista de selecciones a la confederacion por parametro a la hora de instanciarlas
class Confederacion(val nombre: String)

val AFC = Confederacion("AFC")
val CAF = Confederacion("CAF")
val CONCACAF = Confederacion("CONCACAF")
val CONMEBOL = Confederacion("CONMEBOL")
val OFC = Confederacion("OFC")
val UEFA = Confederacion("UEFA")

val listaConfederaciones = listOf(AFC, CAF, CONCACAF,CONMEBOL,OFC,UEFA)

