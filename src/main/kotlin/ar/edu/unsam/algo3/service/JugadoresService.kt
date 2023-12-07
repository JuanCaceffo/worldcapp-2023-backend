package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.BaseFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.InfoCrearModificarJugadorDTO
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.infoCrearModificarJugadorToDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.JugadorErrorMessages
import ar.edu.unsam.algo3.error.NotImplementedError
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeParseException

@Service
class JugadoresService(
    val jugadoresRepo: JugadorRepository,
    val seleccionesRepo: SeleccionesRepository,
    val figuritasRepository: FiguritasRepository
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
        throw BussinesExpetion(JugadorErrorMessages.POSICION_INEXISTENTE)
    }

    fun stirngASeleccion(strSeleccion:String): Seleccion {
        return seleccionesRepo.getAll().find { seleccion -> seleccion.pais == strSeleccion} ?: throw BussinesExpetion(JugadorErrorMessages.SELECCION_INEXISTENTE)
    }

    fun fechaParser(anio: String): LocalDate{
        try {
            return LocalDate.parse(anio)
        }catch (e: DateTimeParseException){
            throw NotImplementedError("Error al parsear la fecha: " + e.message)
        }
    }

    fun crearJugador(infoJugador: InfoCrearModificarJugadorDTO) {
        val nuevoJugador = Jugador(
            nombre = infoJugador.nombre,
            apellido = infoJugador.apellido,
            altura = infoJugador.altura,
            peso = infoJugador.peso,
            nroCamiseta = infoJugador.nroCamiseta,
            fechaNacimiento = fechaParser(infoJugador.fechaNacimiento),
            anioDeDebut = infoJugador.debut,
            cotizacion = infoJugador.cotizacion,
            esLider = infoJugador.esLider,
            posicion = stringAPosicion(infoJugador.posicion,infoJugador.posiciones),
            seleccionPerteneciente = stirngASeleccion(infoJugador.seleccion)
        )
        jugadoresRepo.create(nuevoJugador)
    }

    fun modificarJugador(infoJugador: InfoCrearModificarJugadorDTO, idJugador: Int) {
        val jugador = jugadoresRepo.getById(idJugador)

        with(jugador) {
            fechaNacimiento = fechaParser(infoJugador.fechaNacimiento)
            altura = infoJugador.altura
            apellido = infoJugador.apellido
            nombre = infoJugador.nombre
            anioDeDebut = infoJugador.debut
            cotizacion = infoJugador.cotizacion
            esLider = infoJugador.esLider
            peso = infoJugador.peso
            nroCamiseta = infoJugador.nroCamiseta
            posicion = stringAPosicion(infoJugador.posicion, infoJugador.posiciones)
            seleccionPerteneciente = stirngASeleccion(infoJugador.seleccion)
        }
    }

    fun getAll(params: BaseFilterParams): List<JugadorDTO> {
        val jugadores = jugadoresRepo.getAll()
        return filtrar(jugadores, params).map { it.toDTO() }
    }

    fun crearFiltroJugador(params: BaseFilterParams):Filtro<Jugador>{
        return Filtro<Jugador>().apply {
            addCondiconFiltrado(FiltroPalabraClaveJugador(params.palabraClave, jugadoresRepo))
        }
    }

    fun filtrar(figus: List<Jugador>, params: BaseFilterParams): List<Jugador>{
        val filtro = crearFiltroJugador(params)
        return figus.filter { figu -> filtro.cumpleCondiciones(figu) }
    }

    fun eliminarJugador(id: Int) {
        validarJugadorInutilizado(id)
        val jugador = jugadoresRepo.getById(id)
        jugadoresRepo.delete(jugador)
    }

    //VALIDACIONES
    fun validarJugadorInutilizado(id: Int){
        if (figuritasRepository.getAll().any { figu -> figu.jugador.id == id }){
            throw BussinesExpetion(JugadorErrorMessages.JUGADOR_UTILIZADO)
        }
    }

    fun obtenerJugador(id: Int): InfoCrearModificarJugadorDTO {
        return jugadoresRepo.getById(id).infoCrearModificarJugadorToDTO()

    }
}
