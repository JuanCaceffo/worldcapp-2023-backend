package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.infoJugadorDTO
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.NotImplementedError
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeParseException

val MENSAJE_ERROR_POSICION_INEXISTENTE = "La posicion que a seleccionado no existe"
val MESNAJE_ERROR_SELECCION_INEXISTENTE= "La seleccion que a seleccionado no existe"

@Service
class JugadoresService(
    val jugadoresRepo: JugadorRepository,
    val seleccionesRepo: SeleccionesRepository
) {

    fun stringAPosicion(keyPosicion: String, keysPosiciones: List<String>? = null): Posicion {
        val stirngPosicionAObjeto = mapOf<String, Posicion>(
            "Arquero" to Arquero,
            "Delantero" to Delantero,
            "Defensor" to Defensor,
            "Mediocampista" to Mediocampista
            )
        if(stirngPosicionAObjeto.containsKey(keyPosicion)){
            return stirngPosicionAObjeto[keyPosicion]!!
        }
        if("Polivalente" == keyPosicion && keysPosiciones != null){
            val posiciones = keysPosiciones.map { key -> stringAPosicion(key) }
            return Polivalente(posiciones)
        }
        throw BussinesExpetion(MENSAJE_ERROR_POSICION_INEXISTENTE)
    }

    fun stirngASeleccion(strSeleccion:String): Seleccion {
        return seleccionesRepo.getAll().find { seleccion -> seleccion.pais == strSeleccion} ?: throw BussinesExpetion(MESNAJE_ERROR_SELECCION_INEXISTENTE)
    }
    fun crearJugador(infoJugador: infoJugadorDTO) {
        var anioDebut = 0
        var fechaNacimiento: LocalDate

        try {
            anioDebut = LocalDate.parse(infoJugador.debut).year
            fechaNacimiento = LocalDate.parse(infoJugador.nacimiento)
        }catch (e: DateTimeParseException){
            throw NotImplementedError("Error al parsear la fecha: " + e.message)
        }
        val nuevoJugador = Jugador(
            nombre = infoJugador.nombre,
            apellido = infoJugador.apellido,
            altura = infoJugador.altura,
            peso = infoJugador.peso,
            nroCamiseta = infoJugador.camiseta,
            fechaNacimiento = fechaNacimiento,
            anioDeDebut = anioDebut,
            cotizacion = infoJugador.cotizacion,
            esLider = infoJugador.esLider,
            posicion = stringAPosicion(infoJugador.posicion,infoJugador.posiciones),
            seleccionPerteneciente = stirngASeleccion(infoJugador.seleccion)
        )
        jugadoresRepo.create(nuevoJugador)
    }

}
