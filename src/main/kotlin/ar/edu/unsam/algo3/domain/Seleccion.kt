package ar.edu.unsam.algo3.domain

import ar.edu.unsam.algo3.repository.RepositorioProps

class Seleccion(
    var pais: String,
    var confederacion: Confederacion,
    var copasDelMundo: Int,
    var copasConfederacion: Int
): RepositorioProps() {
    companion object {
        fun crear(dataExterna: SeleccionDataExterna): Seleccion {
            //Si el nombre de la confederacion recibida por el servicio externo es igual lo setea si no, excepción
            val confederacion = Confederacion.values().find { it.nombre == dataExterna.confederacion }?: throw IllegalArgumentException(MENSAJE_ERROR_NOMBRE_CONFEDERACION)
            val seleccion = Seleccion(dataExterna.pais, confederacion, dataExterna.copasDelMundo, dataExterna.copasConfederacion)
            seleccion.id(dataExterna.id)
            return seleccion
        }
    }
    fun esCampeonaDelMundo() = copasDelMundo > 0
    override fun validSearchCondition(value: String) =  Comparar.total(value, listOf(pais))
}

//Data class para la ayuda de creación de selecciones con servicio externo
data class SeleccionDataExterna(
    val id: Int,
    val pais: String,
    val confederacion: String,
    val copasDelMundo: Int,
    val copasConfederacion: Int
)
