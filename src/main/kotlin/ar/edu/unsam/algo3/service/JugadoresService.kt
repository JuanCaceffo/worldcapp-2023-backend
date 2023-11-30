package ar.edu.unsam.algo3.service

import ar.edu.unsam.algo3.controller.BaseFilterParams
import ar.edu.unsam.algo3.domain.*
import ar.edu.unsam.algo3.dto.InfoCrearJugadorDTO
import ar.edu.unsam.algo3.dto.JugadorDTO
import ar.edu.unsam.algo3.dto.toDTO
import ar.edu.unsam.algo3.error.BussinesExpetion
import ar.edu.unsam.algo3.error.NotImplementedError
import ar.edu.unsam.algo3.repository.FiguritasRepository
import ar.edu.unsam.algo3.repository.JugadorRepository
import ar.edu.unsam.algo3.repository.SeleccionesRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeParseException

const val MENSAJE_ERROR_POSICION_INEXISTENTE = "La posicion que a seleccionado no existe"
const val MESNAJE_ERROR_SELECCION_INEXISTENTE= "La seleccion que a seleccionado no existe"
const val MENSAJE_ERROR_DATA_INCOMPLETA = "Necesita ingresar mas campos"
const val MENSAJE_ERROR_JUGADOR_UTILIZADO = "El jugador se encuentra utilizado actualmente"

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
        throw BussinesExpetion(MENSAJE_ERROR_POSICION_INEXISTENTE)
    }

    fun stirngASeleccion(strSeleccion:String): Seleccion {
        return seleccionesRepo.getAll().find { seleccion -> seleccion.pais == strSeleccion} ?: throw BussinesExpetion(MESNAJE_ERROR_SELECCION_INEXISTENTE)
    }

    fun fechaParser(anio: String): LocalDate{
        try {
            return LocalDate.parse(anio)
        }catch (e: DateTimeParseException){
            throw NotImplementedError("Error al parsear la fecha: " + e.message)
        }
    }

    fun crearJugador(infoJugador: InfoCrearJugadorDTO) {
        validarDataJugador(infoJugador)

        val nuevoJugador = Jugador(
            nombre = infoJugador.nombre,
            apellido = infoJugador.apellido,
            altura = infoJugador.altura,
            peso = infoJugador.peso,
            nroCamiseta = infoJugador.camiseta,
            fechaNacimiento = fechaParser(infoJugador.nacimiento),
            anioDeDebut = fechaParser(infoJugador.debut).year,
            cotizacion = infoJugador.cotizacion,
            esLider = infoJugador.esLider,
            posicion = stringAPosicion(infoJugador.posicion,infoJugador.posiciones),
            seleccionPerteneciente = stirngASeleccion(infoJugador.seleccion)
        )
        jugadoresRepo.create(nuevoJugador)
    }

    fun modificarJugador(infoJugador: InfoCrearJugadorDTO, idJugador: Int) {
        validarDataJugador(infoJugador)

        val jugador = jugadoresRepo.getById(idJugador)

        with(jugador) {
            fechaNacimiento = fechaParser(infoJugador.nacimiento)
            altura = infoJugador.altura
            apellido = infoJugador.apellido
            nombre = infoJugador.nombre
            anioDeDebut = fechaParser(infoJugador.debut).year
            cotizacion = infoJugador.cotizacion
            esLider = infoJugador.esLider
            peso = infoJugador.peso
            nroCamiseta = infoJugador.camiseta
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
            throw BussinesExpetion(MENSAJE_ERROR_JUGADOR_UTILIZADO)
        }
    }

    fun validarDataJugador(infoJugador: InfoCrearJugadorDTO){
        with(infoJugador){
            if(nombre.isEmpty() || apellido.isEmpty() || nacimiento.isEmpty() || seleccion.isEmpty() || debut.isEmpty() || posicion.isEmpty()) {
                throw BussinesExpetion(MENSAJE_ERROR_DATA_INCOMPLETA)
            }
        }
    }
}
